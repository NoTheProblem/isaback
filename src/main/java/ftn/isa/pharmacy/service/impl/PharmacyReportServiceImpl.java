package ftn.isa.pharmacy.service.impl;

import ftn.isa.pharmacy.dto.MedQuanReportDTO;
import ftn.isa.pharmacy.dto.MedicineReportDTO;
import ftn.isa.pharmacy.dto.PharmacyReportDTO;
import ftn.isa.pharmacy.exception.ResourceConflictException;
import ftn.isa.pharmacy.model.*;
import ftn.isa.pharmacy.repository.*;
import ftn.isa.pharmacy.service.PharmacyReportService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.*;

@Service
public class PharmacyReportServiceImpl implements PharmacyReportService {

    private final PharmacyAdminRepository pharmacyAdminRepository;
    private final ExaminationRepository examinationRepository;
    private final PriceMediceListRepository priceMediceListRepository;
    private final ReservationRepository reservationRepository;
    private final MedicineQuantityReservationRepository medicineQuantityReservationRepository;
    private final PersListRepository persListRepository;

    public PharmacyReportServiceImpl(PharmacyAdminRepository pharmacyAdminRepository, ExaminationRepository examinationRepository, PriceMediceListRepository priceMediceListRepository, ReservationRepository reservationRepository, MedicineQuantityReservationRepository medicineQuantityReservationRepository, PersListRepository persListRepository) {
        this.pharmacyAdminRepository = pharmacyAdminRepository;
        this.examinationRepository = examinationRepository;
        this.priceMediceListRepository = priceMediceListRepository;
        this.reservationRepository = reservationRepository;
        this.medicineQuantityReservationRepository = medicineQuantityReservationRepository;
        this.persListRepository = persListRepository;
    }

    @Override
    public PharmacyReportDTO incomeReport(String startDate, String endDate) {
        Pharmacy pharmacy = getPharmacyAdmin().getPharmacy();
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date startDateDate = Date.from(start.atStartOfDay(defaultZoneId).toInstant());
        Date endDateDate = Date.from(end.atStartOfDay(defaultZoneId).toInstant());
        List<Examination> examinations = examinationRepository.findAllByPharmacyAndDateBetweenAndIsFree(pharmacy,startDateDate,endDateDate,false);
        PharmacyReportDTO pharmacyReportDTO = new PharmacyReportDTO();
        List<Float> data  = new Stack<>();
        List<String> categoris = new Stack<>();
        while (!start.isAfter(end)) {
            categoris.add(start.toString());
            data.add((float) 0);
            start = start.plusDays(1);
        }
        long date1InMs = startDateDate.getTime();
        for (Examination examination: examinations){
            long date2InMs = examination.getDate().getTime();
            Long timeDiff = date2InMs - date1InMs;
            int days = (int) (timeDiff / (1000 * 60 * 60* 24));
            data.set(days,data.get(days) + examination.getPrice());
        }
        List<Reservation> reservations = reservationRepository.findAllByPharmacyAndPickUpDateBetweenAndPickedUpIsTrue(pharmacy,startDateDate,endDateDate);
        for(Reservation reservation: reservations){
            long date2InMs = reservation.getPickUpDate().getTime();
            Long timeDiff = date2InMs - date1InMs;
            int days = (int) (timeDiff / (1000 * 60 * 60* 24));
            data.set(days,data.get(days) + reservation.getPrice());
        }
        List<PersList> perscriptions = persListRepository.findAllByPharmacyAndDateOfPurchaseBetween(pharmacy,startDateDate,endDateDate);
        for(PersList perscription: perscriptions){
            long date2InMs = perscription.getDateOfPurchase().getTime();
            Long timeDiff = date2InMs - date1InMs;
            int days = (int) (timeDiff / (1000 * 60 * 60* 24));
            data.set(days,data.get(days) + perscription.getPrice());
        }

        pharmacyReportDTO.setCategories(categoris);
        pharmacyReportDTO.setData(data);
        return pharmacyReportDTO;
    }


    @Override
    public MedicineReportDTO quartallyMedicine(String year, String quartal) {
        Pharmacy pharmacy = getPharmacyAdmin().getPharmacy();
        Date startDate = new Date();
        Date endDate = new Date();
        int quartalEnd = Integer.parseInt(quartal) * 3 - 1;
        int quartalStart = quartalEnd - 2;
        startDate.setYear(Integer.parseInt(year)-1900);
        startDate.setMonth(quartalStart);
        startDate.setDate(1);
        endDate.setYear(Integer.parseInt(year)-1900);
        endDate.setMonth(quartalEnd);
        endDate.setDate(31);
        List<Reservation> reservations = reservationRepository.findAllByPharmacyAndPickUpDateBetweenAndPickedUpIsTrue(pharmacy,startDate,endDate);
        List<PersList> perscriptions = persListRepository.findAllByPharmacyAndDateOfPurchaseBetween(pharmacy,startDate,endDate);
        List<String> categories = new Stack<>();
        List<MedQuanReportDTO> data = new Stack<>();
        List<String> names = new Stack<>();
        List<Integer> helpInteger = new Stack<>();
        for(int i = quartalStart; i<=quartalEnd; i++){
            categories.add(String.valueOf(i));
            helpInteger.add(0);
        }
        for(Reservation reservation: reservations){
            List<MedicineQuantityReservation> medicineQuantityReservations = medicineQuantityReservationRepository.findAllByReservation(reservation);
            for(MedicineQuantityReservation medQuan: medicineQuantityReservations){
                if(names.contains(medQuan.getMedicine().getName())){
                    int index = names.indexOf(medQuan.getMedicine().getName());
                    MedQuanReportDTO medQuanReportDTO = data.get(index);
                    List<Integer> qunList = medQuanReportDTO.getMedQuan();
                    qunList.set(reservation.getPickUpDate().getMonth()-quartalStart, qunList.get(reservation.getPickUpDate().getMonth()-1) + medQuan.getQuantity());
                    medQuanReportDTO.setMedQuan(qunList);
                    data.set(index,medQuanReportDTO);
                }
                else {
                    int index = names.size();
                    names.add(medQuan.getMedicine().getName());
                    MedQuanReportDTO medQuanReportDTO = new MedQuanReportDTO();
                    medQuanReportDTO.setMedName(medQuan.getMedicine().getName());
                    List<Integer> qunList = helpInteger;
                    qunList.set(reservation.getPickUpDate().getMonth()-quartalStart,medQuan.getQuantity());
                    medQuanReportDTO.setMedQuan(qunList);
                    data.add(medQuanReportDTO);
                }
            }
        }
        for(PersList perscription: perscriptions){
            if(names.contains(perscription.getMedicine().getName())){
                int index = names.indexOf(perscription.getMedicine().getName());
                MedQuanReportDTO medQuanReportDTO = data.get(index);
                List<Integer> qunList = medQuanReportDTO.getMedQuan();
                qunList.set(perscription.getDateOfPurchase().getMonth()-quartalStart, qunList.get(perscription.getDateOfPurchase().getMonth()-1) + perscription.getQuantity());
                medQuanReportDTO.setMedQuan(qunList);
                data.set(index,medQuanReportDTO);
            }
            else{
                int index = names.size();
                names.add(perscription.getMedicine().getName());
                MedQuanReportDTO medQuanReportDTO = new MedQuanReportDTO();
                medQuanReportDTO.setMedName(perscription.getMedicine().getName());
                List<Integer> qunList = helpInteger;
                qunList.set(perscription.getDateOfPurchase().getMonth(),perscription.getQuantity());
                medQuanReportDTO.setMedQuan(qunList);
                data.add(medQuanReportDTO);
            }
        }
        MedicineReportDTO medicineReportDTO = new MedicineReportDTO();
        medicineReportDTO.setCategories(categories);
        medicineReportDTO.setData(data);
        return medicineReportDTO;

    }

    @Override
    public MedicineReportDTO yearlyMedicine(String year) {
        Pharmacy pharmacy = getPharmacyAdmin().getPharmacy();
        Date startDate = new Date();
        Date endDate = new Date();
        startDate.setYear(Integer.parseInt(year)-1900);
        startDate.setMonth(0);
        startDate.setDate(1);
        endDate.setYear(Integer.parseInt(year)-1900);
        endDate.setMonth(11);
        endDate.setDate(31);
        List<Reservation> reservations = reservationRepository.findAllByPharmacyAndPickUpDateBetweenAndPickedUpIsTrue(pharmacy,startDate,endDate);
        List<PersList> perscriptions = persListRepository.findAllByPharmacyAndDateOfPurchaseBetween(pharmacy,startDate,endDate);
        List<String> categories = new Stack<>();
        List<MedQuanReportDTO> data = new Stack<>();
        List<String> names = new Stack<>();
        List<Integer> helpInteger = new Stack<>();
        for(int i = 1; i<=12; i++){
            categories.add(String.valueOf(i));
            helpInteger.add(0);
        }
        for(Reservation reservation: reservations){
            List<MedicineQuantityReservation> medicineQuantityReservations = medicineQuantityReservationRepository.findAllByReservation(reservation);
            for(MedicineQuantityReservation medQuan: medicineQuantityReservations){
                if(names.contains(medQuan.getMedicine().getName())){
                    int index = names.indexOf(medQuan.getMedicine().getName());
                    MedQuanReportDTO medQuanReportDTO = data.get(index);
                    List<Integer> qunList = medQuanReportDTO.getMedQuan();
                    qunList.set(reservation.getPickUpDate().getMonth(), qunList.get(reservation.getPickUpDate().getMonth()-1) + medQuan.getQuantity());
                    medQuanReportDTO.setMedQuan(qunList);
                    data.set(index,medQuanReportDTO);
                }
                else {
                    int index = names.size();
                    names.add(medQuan.getMedicine().getName());
                    MedQuanReportDTO medQuanReportDTO = new MedQuanReportDTO();
                    medQuanReportDTO.setMedName(medQuan.getMedicine().getName());
                    List<Integer> qunList = helpInteger;
                    qunList.set(reservation.getPickUpDate().getMonth(),medQuan.getQuantity());
                    medQuanReportDTO.setMedQuan(qunList);
                    data.add(medQuanReportDTO);
                }
            }
        }
        for(PersList perscription: perscriptions){
            if(names.contains(perscription.getMedicine().getName())){
                int index = names.indexOf(perscription.getMedicine().getName());
                MedQuanReportDTO medQuanReportDTO = data.get(index);
                List<Integer> qunList = medQuanReportDTO.getMedQuan();
                qunList.set(perscription.getDateOfPurchase().getMonth(), qunList.get(perscription.getDateOfPurchase().getMonth()-1) + perscription.getQuantity());
                medQuanReportDTO.setMedQuan(qunList);
                data.set(index,medQuanReportDTO);
            }
            else{
                int index = names.size();
                names.add(perscription.getMedicine().getName());
                MedQuanReportDTO medQuanReportDTO = new MedQuanReportDTO();
                medQuanReportDTO.setMedName(perscription.getMedicine().getName());
                List<Integer> qunList = helpInteger;
                qunList.set(perscription.getDateOfPurchase().getMonth(),perscription.getQuantity());
                medQuanReportDTO.setMedQuan(qunList);
                data.add(medQuanReportDTO);
            }
        }
        MedicineReportDTO medicineReportDTO = new MedicineReportDTO();
        medicineReportDTO.setCategories(categories);
        medicineReportDTO.setData(data);
        return medicineReportDTO;
    }

    @Override
    public MedicineReportDTO monthlyMedicine(String year, String month) {
        Pharmacy pharmacy = getPharmacyAdmin().getPharmacy();
        YearMonth yearMonthObject = YearMonth.of(Integer.parseInt(year) , Integer.parseInt(month));
        int daysInMonth = yearMonthObject.lengthOfMonth();
        Date startDate = new Date();
        Date endDate = new Date();
        startDate.setYear(Integer.parseInt(year)-1900);
        startDate.setMonth(Integer.parseInt(month)-1);
        startDate.setDate(1);
        endDate.setYear(Integer.parseInt(year)-1900);
        endDate.setMonth(Integer.parseInt(month)-1);
        endDate.setDate(daysInMonth);
        List<Reservation> reservations = reservationRepository.findAllByPharmacyAndPickUpDateBetweenAndPickedUpIsTrue(pharmacy,startDate,endDate);
        List<PersList> perscriptions = persListRepository.findAllByPharmacyAndDateOfPurchaseBetween(pharmacy,startDate,endDate);
        MedicineReportDTO report = new MedicineReportDTO();
        List<String> categories = new Stack<>();
        List<MedQuanReportDTO> data = new Stack<>();
        List<String> names = new Stack<>();
        List<Integer> helpInteger = new Stack<>();
        for(int i = 1; i<=daysInMonth; i++){
            categories.add(String.valueOf(i));
            helpInteger.add(0);
        }
        for(Reservation reservation: reservations){
            List<MedicineQuantityReservation> medicineQuantityReservations = medicineQuantityReservationRepository.findAllByReservation(reservation);
            for(MedicineQuantityReservation medQuan: medicineQuantityReservations){
                if(names.contains(medQuan.getMedicine().getName())){
                    int index = names.indexOf(medQuan.getMedicine().getName());
                    MedQuanReportDTO medQuanReportDTO = data.get(index);
                    List<Integer> qunList = medQuanReportDTO.getMedQuan();
                    qunList.set(reservation.getPickUpDate().getDate()-1, qunList.get(reservation.getPickUpDate().getDate()-1) + medQuan.getQuantity());
                    medQuanReportDTO.setMedQuan(qunList);
                    data.set(index,medQuanReportDTO);
                }
                else {
                    int index = names.size();
                    names.add(medQuan.getMedicine().getName());
                    MedQuanReportDTO medQuanReportDTO = new MedQuanReportDTO();
                    medQuanReportDTO.setMedName(medQuan.getMedicine().getName());
                    List<Integer> qunList = helpInteger;
                    qunList.set(reservation.getPickUpDate().getDate()-1,medQuan.getQuantity());
                    medQuanReportDTO.setMedQuan(qunList);
                    data.add(medQuanReportDTO);
                }
            }
        }
        for(PersList perscription: perscriptions){
            if(names.contains(perscription.getMedicine().getName())){
                int index = names.indexOf(perscription.getMedicine().getName());
                MedQuanReportDTO medQuanReportDTO = data.get(index);
                List<Integer> qunList = medQuanReportDTO.getMedQuan();
                qunList.set(perscription.getDateOfPurchase().getDate()-1, qunList.get(perscription.getDateOfPurchase().getDate()-1) + perscription.getQuantity());
                medQuanReportDTO.setMedQuan(qunList);
                data.set(index,medQuanReportDTO);
            }
            else{
                int index = names.size();
                names.add(perscription.getMedicine().getName());
                MedQuanReportDTO medQuanReportDTO = new MedQuanReportDTO();
                medQuanReportDTO.setMedName(perscription.getMedicine().getName());
                List<Integer> qunList = helpInteger;
                qunList.set(perscription.getDateOfPurchase().getDate()-1,perscription.getQuantity());
                medQuanReportDTO.setMedQuan(qunList);
                data.add(medQuanReportDTO);
            }
        }
        MedicineReportDTO medicineReportDTO = new MedicineReportDTO();
        medicineReportDTO.setCategories(categories);
        medicineReportDTO.setData(data);
        return medicineReportDTO;
    }

    @Override
    public PharmacyReportDTO quartallyExamination(String year, String quartal) {
        Pharmacy pharmacy = getPharmacyAdmin().getPharmacy();
        Date startDate = new Date();
        Date endDate = new Date();
        int quartalEnd = Integer.parseInt(quartal) * 3 - 1;
        int quartalStart = quartalEnd - 2;
        startDate.setYear(Integer.parseInt(year)-1900);
        startDate.setMonth(quartalStart);
        startDate.setDate(1);
        endDate.setYear(Integer.parseInt(year)-1900);
        endDate.setMonth(quartalEnd);
        endDate.setDate(31);
        List<Examination> examinations = examinationRepository.findAllByPharmacyAndDateBetween(pharmacy,startDate,endDate);
        PharmacyReportDTO pharmacyReportDTO = new PharmacyReportDTO();
        List<Float> data  = new Stack<>();
        List<String> categoris = new Stack<>();
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        for(int i = quartalStart; i<=quartalEnd; i++){
            categoris.add(months[i]);
            data.add((float) 0);
        }
        for (Examination examination: examinations){
            if(!examination.getFree()){
                int day = examination.getDate().getMonth()-quartalStart;
                data.set(day,data.get(day) + 1);
            }
        }
        pharmacyReportDTO.setCategories(categoris);
        pharmacyReportDTO.setData(data);
        return pharmacyReportDTO;
    }

    @Override
    public PharmacyReportDTO yearlyExamination(String year) {
        Pharmacy pharmacy = getPharmacyAdmin().getPharmacy();
        Date startDate = new Date();
        Date endDate = new Date();
        startDate.setYear(Integer.parseInt(year)-1900);
        startDate.setMonth(0);
        startDate.setDate(1);
        endDate.setYear(Integer.parseInt(year)-1900);
        endDate.setMonth(11);
        endDate.setDate(31);
        List<Examination> examinations = examinationRepository.findAllByPharmacyAndDateBetween(pharmacy,startDate,endDate);
        PharmacyReportDTO pharmacyReportDTO = new PharmacyReportDTO();
        List<Float> data  = new Stack<>();
        List<String> categoris = new Stack<>();
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        for(int i = 0; i<=11; i++){
            categoris.add(months[i]);
            data.add((float) 0);
        }
        for (Examination examination: examinations){
            if(!examination.getFree()){
                int day = examination.getDate().getMonth();
                data.set(day,data.get(day) + 1);
            }
        }
        pharmacyReportDTO.setCategories(categoris);
        pharmacyReportDTO.setData(data);
        return pharmacyReportDTO;
    }

    @Override
    public PharmacyReportDTO monthlyExamination(String year, String month) {
        Pharmacy pharmacy = getPharmacyAdmin().getPharmacy();
        YearMonth yearMonthObject = YearMonth.of(Integer.parseInt(year) , Integer.parseInt(month));
        int daysInMonth = yearMonthObject.lengthOfMonth();
        Date startDate = new Date();
        Date endDate = new Date();
        startDate.setYear(Integer.parseInt(year)-1900);
        startDate.setMonth(Integer.parseInt(month)-1);
        startDate.setDate(1);
        endDate.setYear(Integer.parseInt(year)-1900);
        endDate.setMonth(Integer.parseInt(month)-1);
        endDate.setDate(daysInMonth);
        List<Examination> examinations = examinationRepository.findAllByPharmacyAndDateBetween(pharmacy,startDate,endDate);
        PharmacyReportDTO pharmacyReportDTO = new PharmacyReportDTO();
        List<Float> data  = new Stack<>();
        List<String> categoris = new Stack<>();
        for(int i = 1; i<=daysInMonth; i++){
            categoris.add(String.valueOf(i));
            data.add((float) 0);
        }
        for (Examination examination: examinations){
            if(!examination.getFree()){
                int day = examination.getDate().getDate();
                data.set(day-1,data.get(day-1) + 1);
            }
        }
        pharmacyReportDTO.setCategories(categoris);
        pharmacyReportDTO.setData(data);
        return pharmacyReportDTO;
    }



    private PharmacyAdmin getPharmacyAdmin(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<PharmacyAdmin> pharmacyAdminOptional = pharmacyAdminRepository.findById(((User) authentication.getPrincipal()).getId());
        if(pharmacyAdminOptional.isPresent()) {
            PharmacyAdmin pharmacyAdmin = pharmacyAdminOptional.get();
            return pharmacyAdmin;
        }
        throw new ResourceConflictException(1l,"Ne postoji administrator apoteke!");
    }



}
