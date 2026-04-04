package com.dipanshu.product_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Inventory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
    @Id
    private Long productId;
    @Version
    private Long version;
    @Column(nullable = false)
    private int availableStock;
    @Column(nullable = false)
    private int reservedStock;
}
