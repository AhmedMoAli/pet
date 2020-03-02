package com.salesforce.tooling.services;

import java.util.List;

import com.salesforce.tooling.entity.Company;

/**
 * A company service interface which holds actions carried against company service endpoint.
 */
public interface CompanyService {

    /**
     * Gets all companies.
     * 
     * @return list of {@link Company}.
     */
    List<Company> getCompanies();
}
