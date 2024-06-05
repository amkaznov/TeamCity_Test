package org.example.api.enums;

public enum Role {
    SYSTEM_ADMIN("SYSTEM_ADMIN"),
    PROJECT_VIEWER("PROJECT_VIEWER"),
    PROJECT_DEVELOPER("PROJECT_DEVELOPER"),
    PROJECT_ADMIN("PROJECT_ADMIN"),
    AGENT_MANAGER("AGENT_MANAGER");

    private String text;

    public String getText() {
        return text;
    }

    Role(String text) {
        this.text = text;
    }
}
