package com.k23cnt2.kntlab0506.service;

import com.k23cnt2.kntlab0506.dto.CustomerDTO;
import com.k23cnt2.kntlab0506.entity.Customer;
import com.k23cnt2.kntlab0506.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@Transactional
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }
    public Customer toCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setUsername(customerDTO.getUsername());
        customer.setPassword(customerDTO.getPassword());
        customer.setFullname(customerDTO.getFullname());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setBirthday(customerDTO.getBirthday());
        customer.setAddress(customerDTO.getAddress());
        customer.setEmail(customerDTO.getEmail());
        customer.setActive(customerDTO.getActive() != null ? customerDTO.getActive() : true);
        return customer;
    }

    public Customer save(@Valid @RequestBody CustomerDTO customerDTO) {
        Customer customer = toCustomer(customerDTO);
        return customerRepository.save(customer);
    }

    public Customer update(Long id, @Valid @RequestBody CustomerDTO customerDTO) {
        Customer existingCustomer = customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer is not exist!"));

        // Gán giá trị từ DTO vào existingCustomer
        existingCustomer.setUsername(customerDTO.getUsername());
        existingCustomer.setPassword(customerDTO.getPassword());
        existingCustomer.setFullname(customerDTO.getFullname());
        existingCustomer.setPhoneNumber(customerDTO.getPhoneNumber());
        existingCustomer.setBirthday(customerDTO.getBirthday());
        existingCustomer.setAddress(customerDTO.getAddress());
        existingCustomer.setEmail(customerDTO.getEmail());
        existingCustomer.setActive(customerDTO.getActive() != null ? customerDTO.getActive() : true);

        // Lưu lại
        return customerRepository.save(existingCustomer);
    }


    public void delete(Long id) {
        customerRepository.deleteById(id);
    }
}
