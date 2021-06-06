package ftn.isa.pharmacy.service.impl;

import ftn.isa.pharmacy.dto.BidDTO;
import ftn.isa.pharmacy.dto.PurchaseOrderDTO;
import ftn.isa.pharmacy.exception.ResourceConflictException;
import ftn.isa.pharmacy.mapper.impl.*;
import ftn.isa.pharmacy.model.*;
import ftn.isa.pharmacy.repository.*;
import ftn.isa.pharmacy.service.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final PurchaseOrderMapperImpl purchaseOrderMapper;
    private final PharmacyAdminRepository pharmacyAdminRepository;
    private final QuantityMapperImpl quantityMapper;
    private final BidRepository bidRepository;
    private final BidMapperImpl bidMapper;
    private final SupplierMapperImpl supplierMapper;
    private final MailServiceImpl mailService;
    private final SupplierRepository supplierRepository;
    private final MedicineQuantityOrderRepository medicineQuantityOrderRepository;
    private final MedicineQuantityPharmacyRepository medicineQuantityPharmacyRepository;
    private final MedicineRepository medicineRepository;

    public OrderServiceImpl(PurchaseOrderRepository purchaseOrderRepository, PurchaseOrderMapperImpl purchaseOrderMapper, PharmacyAdminRepository pharmacyAdminRepository,  QuantityMapperImpl quantityMapper, BidRepository bidRepository, BidMapperImpl bidMapper, SupplierMapperImpl supplierMapper, MailServiceImpl mailService, SupplierRepository supplierRepository, MedicineQuantityOrderRepository medicineQuantityOrderRepository, MedicineQuantityPharmacyRepository medicineQuantityPharmacyRepository, MedicineRepository medicineRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.purchaseOrderMapper = purchaseOrderMapper;
        this.pharmacyAdminRepository = pharmacyAdminRepository;
        this.quantityMapper = quantityMapper;
        this.bidRepository = bidRepository;
        this.bidMapper = bidMapper;
        this.supplierMapper = supplierMapper;
        this.mailService = mailService;
        this.supplierRepository = supplierRepository;
        this.medicineQuantityOrderRepository = medicineQuantityOrderRepository;
        this.medicineQuantityPharmacyRepository = medicineQuantityPharmacyRepository;
        this.medicineRepository = medicineRepository;
    }

    @Override
    public List<PurchaseOrder> getAll() {
        return purchaseOrderRepository.findAll();
    }

    @Override
    public void addPurchaseOrder(PurchaseOrderDTO purchaseOrderDTO) {
        PharmacyAdmin pharmacyAdmin = getPharmacyAdmin();
        Date today = new Date();
        if(purchaseOrderDTO.getEndDate().before(today)){
            throw new ResourceConflictException(1L,"Greska!");

        }
        Pharmacy pharmacy = pharmacyAdmin.getPharmacy();
        PurchaseOrder purchaseOrder = purchaseOrderMapper.bean2Entity(purchaseOrderDTO);
        Set<MedicineQuantityOrder> orderMedicines = quantityMapper.quantityOrderDTOtoQuantityOrder(purchaseOrderDTO.getMedQuan(),purchaseOrder);
        purchaseOrder.setPharmacyAdmin(pharmacyAdmin);
        purchaseOrder.setStatus("created");
        purchaseOrder.setCreateDate(today);
        purchaseOrder.setOrderMedicines(orderMedicines);
        purchaseOrderRepository.save(purchaseOrder);
        for (MedicineQuantityOrder medicineQuan: orderMedicines) {
            Medicine medicine =  medicineRepository.getOne(medicineQuan.getMedicine());
            if(!medicineQuantityPharmacyRepository.existsByPharmacyAndMedicine(pharmacy,medicine)){
                MedicineQuantityPharmacy medicineQuantityPharmacy = new MedicineQuantityPharmacy();
                medicineQuantityPharmacy.setMedicine(medicine);
                medicineQuantityPharmacy.setQuantity(0);
                medicineQuantityPharmacy.setPharmacy(pharmacy);
                medicineQuantityPharmacyRepository.save(medicineQuantityPharmacy);
            }
        }
    }

    @Override
    public Collection<PurchaseOrderDTO> getAllActive() {
        List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.findAllByOrderByEndDateAsc();
        return purchaseOrderMapper.entity2Bean(purchaseOrders);
        }

    @Override
    public PurchaseOrderDTO getOrderByID(Long id) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.getOne(id);
        return purchaseOrderMapper.entity2Bean(purchaseOrder);
    }

    @Override
    public Collection<BidDTO> getBidsForOrder(Long id) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.getOne(id);
        Collection<Bid> bids = bidRepository.findAllByPurchaseOrder(purchaseOrder);
        Collection<BidDTO> bidDTOS = new HashSet<>();
        for (Bid b:bids) {
            Supplier s = b.getSupplier();
            BidDTO bidDTO = bidMapper.entity2Bean(b);
            bidDTO.setSupplier(supplierMapper.entity2Bean(s));
            bidDTOS.add(bidDTO);
        }
        return bidDTOS;
    }

    @Override
    public void confirmBid(BidDTO bidDTO, Long id) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.getOne(id);
        if(purchaseOrder.getStatus().equals("obradjen")){
            throw new ResourceConflictException(1L,"Vec obradjena porudzbenica");

        }
        if(purchaseOrder.getPharmacyAdmin().getId() != getPharmacyAdmin().getId()){
            throw new ResourceConflictException(1L,"Niste vi napravili porudzbenicu");

        }
        Date today = new Date();
        if(purchaseOrder.getEndDate().before(today)){
            throw new ResourceConflictException(1L,"Greska!");
        }
        Bid bid = bidMapper.bean2Entity(bidDTO);
        Long suppID = bidDTO.getSupplier().getId();
        Supplier supplier = supplierRepository.getOne(suppID);
        purchaseOrder.setStatus("obradjen");
        purchaseOrder.setPrice(bid.getPrice());
        // Set<Bid> allBids = purchaseOrder.getBids();
        List<Bid> allBids = bidRepository.findAllByPurchaseOrder(purchaseOrder);
        String subj = "Status porudzbenice";
        String text = "Obavestavamo Vas da Vasa ponuda za porudzbenicu broj #" + purchaseOrder.getId() + " odbijena ";
        System.out.println(allBids.size());
        for (Bid b:allBids) {
            if(b.getId() == bid.getId()){
                b.setStatus("odobrena");
                mailService.purchaseOrderNotification(b.getSupplier().getEmail(),subj, "Obavestavamo Vas da Vasa ponuda za porudzbenicu broj #" + purchaseOrder.getId() + " prihvacena ");
                bidRepository.save(b);
            }
            else {
                b.setStatus("odbijena");
                mailService.purchaseOrderNotification(b.getSupplier().getEmail(), subj, text);
                bidRepository.save(b);
            }
        }
        Long phaID = purchaseOrder.getPharmacyAdmin().getPharmacy().getId();
        purchaseOrderRepository.save(purchaseOrder);
        supplierRepository.save(supplier);
        List<MedicineQuantityOrder> medicineQuantityOrders =  medicineQuantityOrderRepository.findAllByPurchaseOrder(purchaseOrder);
        for (MedicineQuantityOrder mQ: medicineQuantityOrders) {
            medicineQuantityPharmacyRepository.updateQuan(phaID, mQ.getMedicine(), mQ.getQuantity());
        }
    }

    @Override
    public void deleteOrder(PurchaseOrderDTO purchaseOrderDTO) {
        PharmacyAdmin pharmacyAdmin = getPharmacyAdmin();
        PurchaseOrder purchaseOrder = purchaseOrderMapper.bean2Entity(purchaseOrderDTO);
        if(bidRepository.findAllByPurchaseOrder(purchaseOrder).size() != 0){
            throw new ResourceConflictException(1L,"Postoje ponude!");
        }
        purchaseOrderRepository.deleteById(purchaseOrder.getId());
    }

    @Override
    public void updateOrder(PurchaseOrderDTO purchaseOrderDTO) {
        PharmacyAdmin pharmacyAdmin = getPharmacyAdmin();
        PurchaseOrder purchaseOrder = purchaseOrderMapper.bean2Entity(purchaseOrderDTO);
        if(bidRepository.findAllByPurchaseOrder(purchaseOrder).size() != 0){
            throw new ResourceConflictException(1L,"Postoje ponude");
        }
        purchaseOrderRepository.save(purchaseOrder);

    }

    private PharmacyAdmin getPharmacyAdmin(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<PharmacyAdmin> pharmacyAdminOptional = pharmacyAdminRepository.findById(((User) authentication.getPrincipal()).getId());
        if(pharmacyAdminOptional.isPresent()) {
            PharmacyAdmin pharmacyAdmin = pharmacyAdminOptional.get();
            return pharmacyAdmin;
        }
        throw new ResourceConflictException(1L,"Ne postoji admin apotek");
    }
}
