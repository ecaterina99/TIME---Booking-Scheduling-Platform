package com.server.organization.domain.organizations;

import lombok.Getter;
import org.springframework.util.Assert;

@Getter
public class Organization {
    private int id;
    private String name;
    private OrganizationAddress address;
    private String city;
    private OrganizationEmail email;
    private OrganizationPhone phone;

    public Organization(int id, String name, OrganizationAddress address, String city, OrganizationEmail email, OrganizationPhone phone) {
        Assert.notNull(name, "Organization name must not be empty");
        Assert.notNull(address, "Address must not be null");
        Assert.notNull(city, "City must not be null");
        Assert.notNull(email, "Email must not be null");
        Assert.notNull(phone, "Phone must not be null");
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.email = email;
        this.phone = phone;
    }

    public void changeName(String name) {
        Assert.notNull(name, "Full name must not be null");
        this.name = name;
    }

    public void changeAddress(OrganizationAddress address) {
        Assert.notNull(address, "Address must not be null");
        this.address = address;
    }

    public void changePhone(OrganizationPhone phone) {
        Assert.notNull(phone, "Phone must not be null");
        this.phone = phone;
    }

    public void changeCity(String city) {
        Assert.notNull(city, "City must not be null");
        this.city = city;
    }

    public void changeEmail(OrganizationEmail email) {
        Assert.notNull(email, "Email must not be null");
        this.email = email;
    }

}
