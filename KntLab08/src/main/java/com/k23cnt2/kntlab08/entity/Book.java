package com.k23cnt2.kntlab08.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book")
@Builder
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "code", length = 50, nullable = false)
    private String code;

    @Column(name = "name", length = 150, nullable = false, unique = true)
    private String name;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "quantity", length = 11)
    private int quantity;

    @Column(name = "price", precision = 12, scale = 0, nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20, nullable = false)
    private BookStatus status;

    @ManyToMany
    @JoinTable(name = "book_author", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    @Builder.Default
    private Set<Author> registeredAuthors = new HashSet<>();
}

