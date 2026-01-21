package com.server.service.domain;

import lombok.Getter;
import org.springframework.util.Assert;

@Getter
public class Service {
    private int id;
    private ServiceName name;
    private Integer organizationId;
    private String description;
    private ServiceDuration durationMinutes;
    private ServicePrice price;


    public Service(int id, ServiceName name, Integer organizationId, String description, ServiceDuration durationMinutes, ServicePrice price) {
        if (name == null) throw new IllegalArgumentException("name is required");
        if (description == null || description.isBlank()) throw new IllegalArgumentException("description is required");
        if (durationMinutes == null) throw new IllegalArgumentException("durationMinutes is required");
        if (price == null) throw new IllegalArgumentException("price is required");
        if (organizationId == null) throw new IllegalArgumentException("organizationId is required");
        this.id = id;
        this.name = name;
        this.organizationId = organizationId;
        this.description = description;
        this.durationMinutes = durationMinutes;
        this.price = price;
    }

    public void changeName(ServiceName name) {
        if (name == null) throw new IllegalArgumentException("name is required");
        this.name = name;
    }

    public void changeDuration(ServiceDuration durationMinutes) {
        if (durationMinutes == null) throw new IllegalArgumentException("duration in minutes is required");
        this.durationMinutes = durationMinutes;
    }

    public void changePrice(ServicePrice price) {
        if (price == null) throw new IllegalArgumentException("price is required");
        this.price = price;
    }

    public void changeDescription(String description) {
        Assert.notNull(description, "Description is required");
        this.description = description;
    }

    public void changeOrganizationId(Integer organizationId) {
        Assert.notNull(organizationId, "organization Id is required");
        this.organizationId = organizationId;
    }

}
