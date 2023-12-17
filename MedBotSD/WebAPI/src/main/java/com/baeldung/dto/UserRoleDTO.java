package com.baeldung.dto;

public class UserRoleDTO {

    private Boolean role;

    public UserRoleDTO(Boolean role) {
        this.role = role;
    }

    public Boolean getRole() {
        return role;
    }

    public void setRole(Boolean role) {
        this.role = role;
    }
}
