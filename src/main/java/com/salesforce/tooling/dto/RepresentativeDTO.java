package com.salesforce.tooling.dto;

/**
 * A DTO class representing representative.
 */
public class RepresentativeDTO {

    private String name;
    private String email;
    private String phone;

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }
}
