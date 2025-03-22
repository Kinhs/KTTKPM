package com.example.demo.repository;

import com.example.demo.model.Bill;
import com.example.demo.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findByRoom(Room room);
    List<Bill> findByIsPaid(Boolean isPaid);
    Optional<Bill> findByRoomAndBillMonth(Room room, YearMonth billMonth);
    List<Bill> findByBillMonth(YearMonth billMonth);
}

