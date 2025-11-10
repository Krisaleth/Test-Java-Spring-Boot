package com.k23cnt.kntlab04.controller;

import com.k23cnt.kntlab04.dto.MonHocDTO;
import com.k23cnt.kntlab04.entity.MonHoc;
import com.k23cnt.kntlab04.service.MonHocService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/monhoc")
public class MonHocController {
    private final MonHocService monHocService;

    @Autowired
    public MonHocController(MonHocService monHocService) {
        this.monHocService = monHocService;
    }

    @GetMapping
    public List<MonHoc> findAll() {
        return monHocService.findAll();
    }

    @PostMapping
    public ResponseEntity<String> create(MonHocDTO monHocDTO) {
        monHocService.save(monHocDTO);
        return ResponseEntity.ok("Monhoc created successfully!");
    }

    @GetMapping("/{id}")
    public MonHoc findById(@PathVariable String id) {
        return monHocService.findById(id);
    }
    @PostMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable String id, @Valid MonHocDTO monHocDTO) {
        if (monHocService.findById(id) != null && id.equals(monHocDTO.getMamh())) {
            monHocService.save(monHocDTO);
            return ResponseEntity.ok("Monhoc with id " + id + " updated successfully!");
        }
        return ResponseEntity.badRequest().body("Id in body is not correct!");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(String id) {
        monHocService.delete(id);
        return ResponseEntity.ok("Monhoc with id " + id + " deleted successfully");
    }
}
