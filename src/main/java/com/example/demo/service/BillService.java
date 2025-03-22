package com.example.demo.service;

import com.example.demo.model.Bill;
import com.example.demo.model.Room;
import com.example.demo.repository.BillRepository;
import com.example.demo.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private RoomRepository roomRepository;

    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    public Optional<Bill> getBillById(Long id) {
        return billRepository.findById(id);
    }

    public List<Bill> getBillsByRoom(Long roomId) {
        Optional<Room> room = roomRepository.findById(roomId);
        return room.map(value -> billRepository.findByRoom(value)).orElse(null);
    }

    public Optional<Bill> getBillByRoomAndMonth(Long roomId, YearMonth billMonth) {
        Optional<Room> room = roomRepository.findById(roomId);
        if (room.isPresent()) {
            return billRepository.findByRoomAndBillMonth(room.get(), billMonth);
        }
        return Optional.empty();
    }

    public List<Bill> getBillsByMonth(YearMonth billMonth) {
        return billRepository.findByBillMonth(billMonth);
    }

    public List<Bill> getUnpaidBills() {
        return billRepository.findByIsPaid(false);
    }

    public Bill createBill(Bill bill) {
        if (bill.getBillDate() == null) {
            bill.setBillDate(LocalDate.now());
        }

        if (bill.getBillMonth() == null) {
            bill.setBillMonth(YearMonth.now());
        }

        // Tính toán số điện tiêu thụ và số tiền
        if (bill.getCurrentElectricReading() != null && bill.getPreviousElectricReading() != null
                && bill.getElectricPrice() != null) {
            int electricUsage = bill.getCurrentElectricReading() - bill.getPreviousElectricReading();
            bill.setElectricAmount(electricUsage * bill.getElectricPrice());
        }

        // Tính toán số nước tiêu thụ và số tiền
        if (bill.getCurrentWaterReading() != null && bill.getPreviousWaterReading() != null
                && bill.getWaterPrice() != null) {
            int waterUsage = bill.getCurrentWaterReading() - bill.getPreviousWaterReading();
            bill.setWaterAmount(waterUsage * bill.getWaterPrice());
        }

        // Tính tổng hóa đơn
        double total = bill.getRentAmount();
        if (bill.getElectricAmount() != null) total += bill.getElectricAmount();
        if (bill.getWaterAmount() != null) total += bill.getWaterAmount();
        if (bill.getOtherAmount() != null) total += bill.getOtherAmount();

        bill.setTotalAmount(total);

        return billRepository.save(bill);
    }

    public void markAsPaid(Long billId) {
        Optional<Bill> billOpt = billRepository.findById(billId);
        if (billOpt.isPresent()) {
            Bill bill = billOpt.get();
            bill.setIsPaid(true);
            billRepository.save(bill);
        }
    }

    public void deleteBill(Long id) {
        billRepository.deleteById(id);
    }
}
