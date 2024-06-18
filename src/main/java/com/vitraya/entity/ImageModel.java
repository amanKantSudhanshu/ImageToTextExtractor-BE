package com.vitraya.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "image_model")
@Data
public class ImageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Lob
    @Column(length = 50000000)
    private String image_extracted;

    @Lob
    @Column(length = 50000000)
    private String textExtracted;

    @Lob
    @Column(length = 50000000)
    private String boldText;
    
    
}
