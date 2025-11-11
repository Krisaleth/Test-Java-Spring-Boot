package com.k23cnt2.kntlab0506.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Long id;

    @Column(name = "username")
    String username;

    @Column(name = "password")
    String password;

    @Column(name = "fullname")
    String fullname;

    @Column(name = "address")
    String address;

    @Column(name = "phone")
    String phoneNumber;

    @Column(name = "email")
    String email;

    @Column(name = "birthday")
    LocalDate birthday;

    @Column(name = "is_active")
    boolean active;
}
