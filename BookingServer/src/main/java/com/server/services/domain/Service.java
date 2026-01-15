package com.server.services.domain;

import lombok.Getter;
import org.springframework.util.Assert;

@Getter
public class Service {
    private int id;
    private String name;
    private int organizationId;
    private String description;
    private int durationMinutes;


    public Service(int id, String name, int organizationId, String description, int durationMinutes) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name is required");
        if (description == null || description.isBlank()) throw new IllegalArgumentException("description is required");
        if (durationMinutes <= 0) throw new IllegalArgumentException("duration must be > 0");
        this.id = id;
        this.name = name;
        this.organizationId = organizationId;
        this.description = description;
        this.durationMinutes = durationMinutes;
    }

    public void changeName(String name) {
        Assert.notNull(name, "Full name is required");
        this.name = name;
    }

    public void changeDuration(int durationMinutes) {
        if (durationMinutes <= 0) throw new IllegalArgumentException("duration must be > 0");
        this.durationMinutes = durationMinutes;
    }

    public void changeDescription(String description) {
        Assert.notNull(description, "Description is required");
        this.description = description;
    }


}
