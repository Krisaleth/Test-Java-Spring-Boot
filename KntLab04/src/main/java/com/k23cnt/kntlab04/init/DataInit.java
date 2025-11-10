package com.k23cnt.kntlab04.init;

import com.k23cnt.kntlab04.entity.Khoa;
import com.k23cnt.kntlab04.entity.MonHoc;
import com.k23cnt.kntlab04.repository.KhoaRepository;
import com.k23cnt.kntlab04.repository.MonHocRepository;
import com.k23cnt.kntlab04.service.MonHocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DataInit implements CommandLineRunner {
    private final MonHocRepository monHocRepository;
    private final KhoaRepository khoaRepository;

    public DataInit(MonHocRepository monHocRepository, KhoaRepository khoaRepository) {
        this.khoaRepository = khoaRepository;
        this.monHocRepository = monHocRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (monHocRepository.count() == 0) {
            monHocRepository.save(MonHoc.builder().Mamh("MH01").Tenmh("Toán Cao Cấp").Sotiet(45).build());
            monHocRepository.save(MonHoc.builder().Mamh("MH02").Tenmh("Lập Trình Java").Sotiet(60).build());
            monHocRepository.save(MonHoc.builder().Mamh("MH03").Tenmh("Cơ Sở Dữ Liệu").Sotiet(45).build());
            monHocRepository.save(MonHoc.builder().Mamh("MH04").Tenmh("Mạng Máy Tính").Sotiet(30).build());
            monHocRepository.save(MonHoc.builder().Mamh("MH05").Tenmh("Hệ Điều Hành").Sotiet(40).build());
        }
        if (khoaRepository.count() == 0) {
            khoaRepository.save(Khoa.builder().Makh("KH01").Tenkh("Khoa Công nghệ Thông tin").build());
            khoaRepository.save(Khoa.builder().Makh("KH02").Tenkh("Khoa Kinh tế").build());
            khoaRepository.save(Khoa.builder().Makh("KH03").Tenkh("Khoa Quản trị Kinh doanh").build());
            khoaRepository.save(Khoa.builder().Makh("KH04").Tenkh("Khoa Điện tử Viễn thông").build());
            khoaRepository.save(Khoa.builder().Makh("KH05").Tenkh("Khoa Ngoại ngữ").build());
        }

    }
}
