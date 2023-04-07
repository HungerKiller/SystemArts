package com.system.arts.entity;

public enum Role {
    ADMIN("ROLE_ADMIN"), USER("ROLE_USER");

    private String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
