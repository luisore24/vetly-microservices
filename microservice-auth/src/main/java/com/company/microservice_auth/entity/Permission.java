package com.company.microservice_auth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "permissions")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Permission {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "permission", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RolePermission> rolePermissions;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "comment")
    private String comment;

    @ManyToOne()
    @JoinColumn(name = "status_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_status"))
    private Status status;

    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;

    @Column(name = "create_by", nullable = false)
    private String createBy;

    @Column(name = "update_at", nullable = false)
    private LocalDateTime updateAt;

    @Column(name = "update_by", nullable = false)
    private String updateBy;

}
