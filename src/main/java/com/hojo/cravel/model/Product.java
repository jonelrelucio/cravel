package com.hojo.cravel.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PRODUCT_TABL")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @Positive
    @NotNull( message = "Code can't be null")
    private Long id;

    @NotBlank( message = "Name can't be blank")
    private String name;
    private int quantity;
    private double price;

}
