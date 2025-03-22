package com.example.demo.repository;

import com.example.demo.model.Customer;
import com.example.demo.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByRoom(Room room);
    Customer findByIdentityCard(String identityCard);
    List<Customer> findByCheckOutDateIsNull();
}
