package com.gym_website.util.enums;


public enum EAppRoles {
    ADMIN("admin"),
    USER("user"),
    MEMBER("MEMBER");



    public final String label;

    EAppRoles(String label) {
        this.label = label;
    }
}
