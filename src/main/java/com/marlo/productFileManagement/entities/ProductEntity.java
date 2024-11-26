package com.marlo.productFileManagement.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity // Marks the class as a JPA Entity
@Table(name = "product") // Maps this entity to the "product" table in the database
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "year_product", nullable = true)
    private Integer year_product;

    @Lob
    @Column(name = "image", columnDefinition = "MEDIUMBLOB", nullable = true)
    private byte[] image;
}


