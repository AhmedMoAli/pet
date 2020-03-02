package com.salesforce.tooling.services.impl;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesforce.tooling.controller.errorhandling.BadRequestException;
import com.salesforce.tooling.entity.Representative;
import com.salesforce.tooling.services.RepresentativeService;

/**
 * A representative service concrete class for handling the representative endpoint actions.
 */
@Service
public class RepresentativeServiceImpl implements RepresentativeService {

    @Value("${representatives.rest.endpoint.url:http://www.mocky.io/v2/5df917f5300000d45688a1b4}")
    private String repEndpointURL;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<Representative> getRepresentatives() {

        List<Representative> representativeList = null;
        try {
            representativeList = objectMapper.readValue(new URL(repEndpointURL),
                new TypeReference<List<Representative>>() {
                });
        } catch (NullPointerException npe) {
            LoggerFactory.getLogger(this.getClass()).error("Failed to parse results.", npe);
            throw new BadRequestException("Couldn't complete request successfully for endpoint, " + repEndpointURL,
                npe);

        } catch (IOException e) {
            throw new BadRequestException("Couldn't complete request successfully for endpoint, " + repEndpointURL, e);
        }
        return representativeList;
    }
}
