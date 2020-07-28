package com.hjy.system.entity;

import lombok.Data;

@Data
public class ReUserRole {
    private String pk_userRole_id;
    private String fk_user_id;
    private String fk_role_id;

}
