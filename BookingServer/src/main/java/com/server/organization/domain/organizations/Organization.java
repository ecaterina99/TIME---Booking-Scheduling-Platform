package com.server.organization.domain.organizations;

import lombok.Getter;
import org.springframework.util.Assert;

@Getter
public class Organization {
    private int id;
    private String name;
    private OrganizationAddress address;
    private String city;

    public Organization(int id, String name, OrganizationAddress address, String city) {
        Assert.notNull(name, "Organization name must not be empty");
        Assert.notNull(address, "Address must not be null");
        Assert.notNull(city, "City must not be null");
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
    }

    public void changeName(String name) {
        Assert.notNull(name, "Full name must not be null");
        this.name = name;
    }

    public void changeAddress(OrganizationAddress address) {
        Assert.notNull(address, "Address must not be null");
        this.address = address;
    }
}
