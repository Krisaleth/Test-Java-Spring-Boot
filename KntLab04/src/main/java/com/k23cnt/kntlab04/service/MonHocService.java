package com.k23cnt.kntlab04.service;

import com.k23cnt.kntlab04.dto.MonHocDTO;
import com.k23cnt.kntlab04.entity.MonHoc;
import com.k23cnt.kntlab04.repository.MonHocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class MonHocService {
    private final MonHocRepository monHocRepository;

    @Autowired
    public MonHocService(MonHocRepository monHocRepository) {
        this.monHocRepository = monHocRepository;
    }

    public List<MonHoc> findAll() {
        return monHocRepository.findAll();
    }

    public MonHoc findById(String id) {
        return monHocRepository.findById(id).orElse(null);
    }

    public MonHoc save(MonHocDTO monHocDTO) {
        MonHoc monHoc = convertToEntity(monHocDTO);
        return monHocRepository.save(monHoc);
    }

    public MonHoc convertToEntity (MonHocDTO monHocDTO) {
        MonHoc monHoc = new MonHoc();
        monHoc.setMamh(monHocDTO.getMamh());
        monHoc.setTenmh(monHocDTO.getTenmh());
        monHoc.setSotiet(monHocDTO.getSotiet());
        return monHoc;
    }

    public void delete (String id) {
        monHocRepository.deleteById(id);
    }
}
