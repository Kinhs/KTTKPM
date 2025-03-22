package com.example.demo.controller;

import com.example.demo.model.Bill;
import com.example.demo.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bills")
public class BillController {

    @Autowired
    private BillService billService;

    @GetMapping
    public ResponseEntity<List<Bill>> getAllBills() {
        return ResponseEntity.ok(billService.getAllBills());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bill> getBillById(@PathVariable Long id) {
        Optional<Bill> bill = billService.getBillById(id);
        return bill.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<Bill>> getBillsByRoom(@PathVariable Long roomId) {
        List<Bill> bills = billService.getBillsByRoom(roomId);
        return bills != null ? ResponseEntity.ok(bills) : ResponseEntity.notFound().build();
    }

    @GetMapping("/room/{roomId}/month/{yearMonth}")
    public ResponseEntity<Bill> getBillByRoomAndMonth(
            @PathVariable Long roomId,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM") YearMonth yearMonth) {
        Optional<Bill> bill = billService.getBillByRoomAndMonth(roomId, yearMonth);
        return bill.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/month/{yearMonth}")
    public ResponseEntity<List<Bill>> getBillsByMonth(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM") YearMonth yearMonth) {
        return ResponseEntity.ok(billService.getBillsByMonth(yearMonth));
    }

    @GetMapping("/unpaid")
    public ResponseEntity<List<Bill>> getUnpaidBills() {
        return ResponseEntity.ok(billService.getUnpaidBills());
    }

    @PostMapping
    public ResponseEntity<Bill> createBill(@RequestBody Bill bill) {
        return new ResponseEntity<>(billService.createBill(bill), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bill> updateBill(@PathVariable Long id, @RequestBody Bill bill) {
        Optional<Bill> existingBill = billService.getBillById(id);
        if (existingBill.isPresent()) {
            bill.setId(id);
            return ResponseEntity.ok(billService.createBill(bill));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/mark-paid")
    public ResponseEntity<Void> markBillAsPaid(@PathVariable Long id) {
        Optional<Bill> existingBill = billService.getBillById(id);
        if (existingBill.isPresent()) {
            billService.markAsPaid(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBill(@PathVariable Long id) {
        Optional<Bill> existingBill = billService.getBillById(id);
        if (existingBill.isPresent()) {
            billService.deleteBill(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
