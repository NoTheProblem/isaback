package ftn.isa.pharmacy.controller;

import ftn.isa.pharmacy.dto.BidDTO;
import ftn.isa.pharmacy.dto.PurchaseOrderDTO;
import ftn.isa.pharmacy.mapper.impl.PurchaseOrderMapperImpl;
import ftn.isa.pharmacy.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final PurchaseOrderMapperImpl orderMapper;

    public OrderController(OrderService orderService, PurchaseOrderMapperImpl orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<Collection<PurchaseOrderDTO>> getAll() {
        Collection<PurchaseOrderDTO> purchaseOrderDTOS = orderMapper.entity2Bean(orderService.getAll());
        return ResponseEntity.ok(purchaseOrderDTOS);
    }

    @PostMapping("/addPurchaseOrder")
    public void addPurchaseOrder(@RequestBody PurchaseOrderDTO purchaseOrderDTO) {
        orderService.addPurchaseOrder(purchaseOrderDTO);
    }

    @GetMapping(value = "/getAllActive")
    public ResponseEntity<Collection<PurchaseOrderDTO>> getAllActive() {
        Collection<PurchaseOrderDTO> purchaseOrderDTOS = orderService.getAllActive();
        return ResponseEntity.ok(purchaseOrderDTOS);
    }

    @GetMapping(value = "/getOrder/{id}")
    public ResponseEntity<PurchaseOrderDTO> getOrder(@PathVariable Long id) {
        PurchaseOrderDTO purchaseOrderDTO = orderService.getOrderByID(id);
        return ResponseEntity.ok(purchaseOrderDTO);
    }

    @GetMapping(value = "/getBidsForOrder/{id}")
    public ResponseEntity<Collection<BidDTO>> getBidsForOrder(@PathVariable Long id) {
        Collection<BidDTO> BidDTOS = orderService.getBidsForOrder(id);
        return ResponseEntity.ok(BidDTOS);
    }

    @PostMapping("/confirmBid/{id}")
    public void confirmBid(@RequestBody BidDTO bidDTO,@PathVariable Long id) {
        orderService.confirmBid(bidDTO,id);
    }

    @PostMapping("/deleteOrder")
    public void deleteOrder(@RequestBody PurchaseOrderDTO purchaseOrderDTO) {
        orderService.deleteOrder(purchaseOrderDTO);
    }

    @PostMapping("/updateOrder")
    public void updateOrder(@RequestBody PurchaseOrderDTO purchaseOrderDTO) {
        orderService.updateOrder(purchaseOrderDTO);
    }

}
