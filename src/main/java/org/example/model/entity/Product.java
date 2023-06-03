package org.example.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column( name ="product_id")
    private Long id;

    private String name;

    private String brand;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private Category category;

    private Integer price;

    @Column(name = "properties")
    @OneToMany(mappedBy="property")
    private Set<Property> properties;
}
