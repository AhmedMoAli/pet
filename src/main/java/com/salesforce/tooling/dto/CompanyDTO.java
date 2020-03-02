package com.salesforce.tooling.dto;

/**
 * A DTO class representing company.
 */
public class CompanyDTO {

    private String name;
    private String address;
    private ContactDTO contact;

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public ContactDTO getContact() {
        return this.contact;
    }

    public void setContact(final ContactDTO contact) {
        this.contact = contact;
    }
}
