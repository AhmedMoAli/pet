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
import com.salesforce.tooling.entity.Company;
import com.salesforce.tooling.services.CompanyService;

/**
 * A company service concrete class for handling the company endpoint actions.
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${companies.rest.endpoint.url:http://www.mocky.io/v2/5df8fc57300000d45688a10e}")
    private String companiesEndpointURL;

    @Override
    public List<Company> getCompanies() {

        List<Company> companies;
        try {
            companies = objectMapper.readValue(new URL(companiesEndpointURL), new TypeReference<List<Company>>() {
            });
        } catch (NullPointerException npe) {
            LoggerFactory.getLogger(this.getClass()).error("Failed to parse results.", npe);
            throw new BadRequestException(
                "Couldn't complete request successfully for endpoint, " + companiesEndpointURL, npe);

        } catch (IOException e) {
            throw new BadRequestException(
                "Couldn't complete request successfully for endpoint, " + companiesEndpointURL, e);
        }
        return companies;
    }
}
