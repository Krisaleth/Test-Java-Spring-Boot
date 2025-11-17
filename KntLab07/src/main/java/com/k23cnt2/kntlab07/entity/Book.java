package com.k23cnt2.kntlab07.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "book")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Long id;

    @Column(name = "name")
    String name;

    @Column(name = "img_url")
    String imgUrl;

    @Column(name = "quantity")
    int quantity;

    @Column(name = "price")
    double price;

    @Column(name = "year")
    int year;

    @Column(name = "author")
    String author;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "book_status", nullable = false)
    BookStatus status;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    Category category;

}

