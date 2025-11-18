package com.k23cnt2.kntlab08.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@Table(name = "author")
@ToString(exclude = "registeredBooks")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone", length = 20)
    private String phone;

    @Lob
    @Column(name = "address")
    private String address;

    @Column(name = "status", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private AuthorStatus status;

    @ManyToMany(mappedBy = "registeredAuthors")
    private Set<Book> books = new HashSet<>();
}
