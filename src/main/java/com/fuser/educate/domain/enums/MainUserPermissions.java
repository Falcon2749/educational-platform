package com.fuser.educate.domain.enums;

public enum MainUserPermissions {
    COURCE_READ("cource:read"),
    COURCE_WRITE("cource:write"),
    USER_READ("user:read"),
    USER_WRITE("user:write");

    private final String permission;

    MainUserPermissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
