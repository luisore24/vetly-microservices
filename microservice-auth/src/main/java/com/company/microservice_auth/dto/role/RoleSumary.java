package com.company.microservice_auth.dto.role;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RoleSumary {

    private Long id;
    private String description;
    private String comment;

}
