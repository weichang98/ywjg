package com.hjy.system.entity;

import lombok.Data;

@Data
public class ReRolePerms {
    private String pk_rolePerms_id;
    private String fk_role_id;
    private String fk_perms_id;

}
