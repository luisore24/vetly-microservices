package com.company.microservice_auth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "status")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "comment")
    private String comment;

    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;

    @Column(name = "create_by", nullable = false)
    private String createBy;

    @Column(name = "update_at", nullable = false)
    private LocalDateTime updateAt;

    @Column(name = "update_by", nullable = false)
    private String updateBy;
}
