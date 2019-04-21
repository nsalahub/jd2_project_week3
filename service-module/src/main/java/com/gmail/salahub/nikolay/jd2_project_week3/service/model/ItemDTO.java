package com.gmail.salahub.nikolay.jd2_project_week3.service.model;

import com.gmail.salahub.nikolay.jd2_project_week3.repository.model.Status;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ItemDTO {

    Long id;

    @NotNull
    @Size(min = 2, max = 30)
    String name;
    Status status;

    public ItemDTO(){};
    public ItemDTO(Long id, String name, Status status) {
        this.id = id;
        this.name = name;
        this.status = status;
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
