package com.salesforce.tooling.json.deserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.salesforce.tooling.entity.GeoLocation;

/**
 * Deserializer for the geolocation property.
 */
public class CustomGeoLocationPropertyDeserializer extends StdDeserializer<GeoLocation> {

    /**
     * 
     */
    private static final long serialVersionUID = -3897426066077971925L;

    public CustomGeoLocationPropertyDeserializer() {
        super(GeoLocation.class);
    }

    /**
     * Converts from comma separated string to Geolocation.
     * 
     * @see com.fasterxml.jackson.databind.JsonDeserializer#deserialize(com.fasterxml.jackson.core.JsonParser,
     *      com.fasterxml.jackson.databind.DeserializationContext)
     */
    @Override
    public GeoLocation deserialize(JsonParser p, DeserializationContext ctxt)
        throws IOException, JsonProcessingException {
        String valueAsString = p.getValueAsString();
        String[] split = valueAsString.split(",");

        return new GeoLocation(Double.valueOf(split[0]), Double.valueOf(split[1]));
    }
}