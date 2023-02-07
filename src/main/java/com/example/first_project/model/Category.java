package com.example.first_project.model;

public class Category {
    private Long id;
    private String name;
    private Boolean status;

    public Category(String name) {
        this.name = name;
        this.status = true;
    }

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
        this.status = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
