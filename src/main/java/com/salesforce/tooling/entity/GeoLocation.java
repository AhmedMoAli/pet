package com.salesforce.tooling.entity;

/**
 * Entity that represents the geolocation.
 */
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoLocation {

    private double latitude;
    private double longitude;

    @JsonCreator
    public GeoLocation(final @JsonProperty("latitude") double latitude,
        final @JsonProperty("longitude") double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }
}
