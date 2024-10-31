package com.luxre.relux.banner.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "banner")
@Getter
@Setter
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="ImagePath", columnDefinition = "TEXT")
    private String imagePath;
    
    private String title;
    private String linkurl;
    
    @Column(name="displayOrder")
    private int displayOrder;

    @CreationTimestamp
    private LocalDateTime createdAt;
    
}
