package com.salesforce.tooling.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.salesforce.tooling.json.deserializer.CustomGeoLocationPropertyDeserializer;

/**
 * Entity that represents the representative.
 */
public class Representative {

    private String name;
    private String email;
    private String phone;
    @JsonDeserialize(using = CustomGeoLocationPropertyDeserializer.class)
    private GeoLocation location;

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPhone() {
        return this.phone;
    }

    public GeoLocation getLocation() {
        return this.location;
    }
}
