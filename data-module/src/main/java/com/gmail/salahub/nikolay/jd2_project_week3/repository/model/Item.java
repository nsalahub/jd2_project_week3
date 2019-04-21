package com.gmail.salahub.nikolay.jd2_project_week3.repository.model;

public class Item {
    Long id;
    String name;
    Status status;

    public Item(Long id, String name, Status status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Item() {
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
