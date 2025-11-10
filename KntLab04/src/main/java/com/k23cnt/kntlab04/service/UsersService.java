package com.k23cnt.kntlab04.service;

import com.k23cnt.kntlab04.dto.UsersDTO;
import com.k23cnt.kntlab04.entity.Users;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UsersService {
    List<Users> users = new ArrayList<>();

    public UsersService() {
        users.add(new Users(101L, "user101", "Pass@123", "Nguyễn Văn A", LocalDate.of(1990, 5, 20), "nguyenvana@example.com", "+84901234567", 35, true));
        users.add(new Users(102L, "user102", "Pass@456", "Trần Thị B", LocalDate.of(1985, 3, 15), "tranthib@example.com", "+84987654321", 40, false));
        users.add(new Users(103L, "user103", "Pass@789", "Lê Văn C", LocalDate.of(1995, 8, 10), "levanc@example.com", "+84933445566", 28, true));
        users.add(new Users(104L, "user104", "Pass@321", "Phạm Thị D", LocalDate.of(1992, 12, 5), "phamthid@example.com", "+84922334455", 31, true));
        users.add(new Users(105L, "user105", "Pass@654", "Hoàng Văn E", LocalDate.of(1988, 10, 25), "hoangvane@example.com", "+84999887766", 34, false));
    }

    public List<Users> findAll() {
        return users;
    }

    public Boolean create(UsersDTO usersDTO) {
        try {
            Users user = new Users();
            user.setUsername(usersDTO.getUsername());
            user.setPassword(usersDTO.getPassword());
            user.setEmail(usersDTO.getEmail());
            user.setFullname(usersDTO.getFullname());
            user.setPhone(usersDTO.getPhone());
            user.setBirthday(usersDTO.getBirthday());
            user.setAge(usersDTO.getAge());
            user.setStatus(usersDTO.getStatus());
            users.add(user);
            return true;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
