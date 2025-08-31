package com.company.microservice_auth.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "menus")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "menu", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RoleMenu> roleMenus;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "icon")
    private String icon;

    @Column(name = "route", nullable = false)
    private String route;

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
