package com.k23cnt2.kntlab07.entity;

import jakarta.persistence.*;
import lombok.*;

import lombok.experimental.FieldDefaults;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Long id;

    @Column(name = "name")
    String name;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "category_status")
    Status status;

}
