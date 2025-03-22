package com.example.demo.service;

import com.example.demo.model.Bill;
import com.example.demo.model.Customer;
import com.example.demo.model.Payment;
import com.example.demo.repository.BillRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }

    public List<Payment> getPaymentsByCustomer(Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        return customer.map(value -> paymentRepository.findByCustomer(value)).orElse(null);
    }

    public Payment getPaymentByBill(Long billId) {
        Optional<Bill> bill = billRepository.findById(billId);
        return bill.map(value -> paymentRepository.findByBill(value)).orElse(null);
    }

    public List<Payment> getPaymentsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return paymentRepository.findByPaymentDateBetween(startDate, endDate);
    }

    public Payment recordPayment(Payment payment) {
        if (payment.getPaymentDate() == null) {
            payment.setPaymentDate(LocalDateTime.now());
        }
        
        if (payment.getBill() != null) {
            Bill bill = payment.getBill();
            bill.setIsPaid(true);
            billRepository.save(bill);
        }

        return paymentRepository.save(payment);
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
}

