package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.model.Room;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RoomRepository roomRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer getCustomerByIdentityCard(String identityCard) {
        return customerRepository.findByIdentityCard(identityCard);
    }

    public List<Customer> getCustomersByRoom(Long roomId) {
        Optional<Room> room = roomRepository.findById(roomId);
        return room.map(value -> customerRepository.findByRoom(value)).orElse(null);
    }

    public List<Customer> getCurrentCustomers() {
        return customerRepository.findByCheckOutDateIsNull();
    }

    public Customer saveCustomer(Customer customer) {
        // Cập nhật trạng thái phòng khi có khách mới thuê
        if (customer.getRoom() != null && customer.getCheckOutDate() == null) {
            Room room = customer.getRoom();
            room.setIsOccupied(true);
            roomRepository.save(room);
        }
        return customerRepository.save(customer);
    }

    public Customer checkOutCustomer(Long customerId) {
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            customer.setCheckOutDate(LocalDate.now());

            // Kiểm tra nếu không còn ai thuê phòng này nữa
            Room room = customer.getRoom();
            List<Customer> roomCustomers = customerRepository.findByRoom(room);
            boolean stillOccupied = roomCustomers.stream()
                    .anyMatch(c -> !c.getId().equals(customerId) && c.getCheckOutDate() == null);

            if (!stillOccupied) {
                room.setIsOccupied(false);
                roomRepository.save(room);
            }

            return customerRepository.save(customer);
        }
        return null;
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}

