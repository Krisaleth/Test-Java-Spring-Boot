package com.k23cnt.kntlab04.service;

import com.k23cnt.kntlab04.dto.KhoaDTO;
import com.k23cnt.kntlab04.entity.Khoa;
import com.k23cnt.kntlab04.repository.KhoaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KhoaService {
    @Autowired
    private final KhoaRepository khoaRepository;

    public KhoaService(KhoaRepository khoaRepository) {
        this.khoaRepository = khoaRepository;
    }

    public List<Khoa> findAll() {
        return khoaRepository.findAll();
    }
    public Khoa save(@Valid KhoaDTO khoaDTO) {
        Khoa khoaEntity = convertToEntity(khoaDTO);
        return khoaRepository.save(khoaEntity);
    }

    private Khoa convertToEntity(KhoaDTO dto) {
        Khoa khoa = new Khoa();
        khoa.setMakh(dto.getMakh());
        khoa.setTenkh(dto.getTenkh());
        return khoa;
    }


    public Khoa findById(String makh){
        return khoaRepository.findById(makh).orElse(null);
    }

    public void delete(String makh) {
        khoaRepository.deleteById(makh);
    }
}
