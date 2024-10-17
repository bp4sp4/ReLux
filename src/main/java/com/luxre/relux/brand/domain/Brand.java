package com.luxre.relux.brand.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="brand")
@Builder
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;                  
    
    private String brandname;           
    
    @Column(name="brandDescription")
    private String brandDescription;
    
    @Column(name="brandImagePath")
    private String brandImagePath;
    
    @Column(name="brandPrice")
    private int brandPrice;
    
    @Column(name="brandAgeGroup")
    private int brandAgeGroup;  
    
    @Column(name="productName")
    private int productName;   
    
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
