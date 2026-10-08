package ru.itis.model;

public class User {
    private final String name;
    private final String role;
    private final boolean active;

    public User(String name, String role, boolean active) {
        this.name = name;
        this.role = role;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public boolean isActive() {
        return active;
    }
}
