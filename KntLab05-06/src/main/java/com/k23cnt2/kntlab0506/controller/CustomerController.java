package com.k23cnt2.kntlab0506.controller;

import com.k23cnt2.kntlab0506.dto.CustomerDTO;
import com.k23cnt2.kntlab0506.entity.Customer;
import com.k23cnt2.kntlab0506.service.CustomerService;
import org.springframework.validation.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public String listCustomers(Model model) {
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "customer/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/create";
    }

    @PostMapping("/save")
    public String saveCustomer(@Valid @ModelAttribute("customer") CustomerDTO customerDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("customer", customerDTO);
            return "customer/create";
        }

        try {
            Customer savedCustomer = customerService.save(customerDTO);
            model.addAttribute("successMessage", "Tạo khách hàng thành công!");
            return "redirect:/customer/list"; // view hiển thị thông báo
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi lưu khách hàng: " + e.getMessage());
            return "customer/create";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Customer customer = customerService.findById(id);
        if (customer == null) {
            return "redirect:/customer/list";
        }
        model.addAttribute("customer", customer);
        return "customer/edit";
    }

    @PostMapping("/update")
    public String updateCustomer(@Valid @ModelAttribute("customer") CustomerDTO customerDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("customer", customerDTO); // 👈 Gán vào model để view có thể dùng th:field
            return "customer/edit";
        }

        try {
            Customer updatedCustomer = customerService.update(customerDTO.getId(), customerDTO);
            model.addAttribute("successMessage", "Cập nhật khách hàng thành công!");
            return "redirect:/customer/list";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Lỗi khi cập nhật: " + e.getMessage());
            return "customer/edit";
        }
    }



    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        try {
            customerService.delete(id);
            return "redirect:/customer/list";
        } catch (Exception e) {
            return "redirect:/customer/list?error=delete_failed";
        }
    }
}

