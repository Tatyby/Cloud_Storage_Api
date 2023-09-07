package com.example.cloudstorageapi.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@ToString
@Table(name = "fil")
public class FileCloud implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name="size")
    private Long size;

    @Column(name = "create_date")
    private LocalDate createDate;

    @Column(name = "content")
    private byte [] data;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private UserCloud user;
}
