package com.k23cnt.kntlab04.controller;

import com.k23cnt.kntlab04.dto.KhoaDTO;
import com.k23cnt.kntlab04.entity.Khoa;
import com.k23cnt.kntlab04.service.KhoaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/khoa")
public class KhoaController {
    @Autowired
    KhoaService khoaService;
    @GetMapping
    public List<Khoa> getAllKhoa() {
        return khoaService.findAll();
    }

    @GetMapping("/{id}")
    public Khoa getKhoaDetail(@PathVariable String id) {
        return khoaService.findById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<String> addKhoa(@Valid @RequestBody KhoaDTO khoaDTO) {
        khoaService.save(khoaDTO);
        return ResponseEntity.ok("Khoa created successfully");
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<String> updateKhoa(@Valid @RequestBody KhoaDTO khoaDTO, @PathVariable String id) {
        Khoa khoa = khoaService.findById(id);
        khoa = khoaService.save(khoaDTO);
        return ResponseEntity.ok("Khoa with id " + id + " updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteKhoa(@PathVariable String id) {
        khoaService.delete(id);
        return ResponseEntity.ok("Khoa with id " + id + " deleted successfully");
    }
}
