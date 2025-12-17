package com.recruiterproof.codegenerator.entity;

import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "generated_code")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneratedCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String className;

    @Column(nullable = false)
    private String namespaceName;

    @Lob
    @Column(nullable = false)
    private String content;

    private OffsetDateTime createdAt;
}
