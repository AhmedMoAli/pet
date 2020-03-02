package com.salesforce.tooling.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

/**
 * Entity that represents the company.
 */
public class Company {

    private String name;
    @JsonUnwrapped
    private GeoLocation location;
    private Contact contact;
    private String address;

    @JsonCreator
    private GeoLocation createGeoLocation(final @JsonProperty("latitude") double latitude,
        final @JsonProperty("longitude") double longitude) {
        return new GeoLocation(latitude, longitude);
    }

    public String getName() {
        return this.name;
    }

    public GeoLocation getLocation() {
        return this.location;
    }

    public Contact getContact() {
        return this.contact;
    }

    public String getAddress() {
        return this.address;
    }
}
