package com.salesforce.tooling.dto;

/**
 * A DTO class representing Opportunity.
 */
public class OpportunityDTO {

    private RepresentativeDTO representative;
    private CompanyDTO company;

    public RepresentativeDTO getRepresentative() {
        return this.representative;
    }

    public void setRepresentative(final RepresentativeDTO representative) {
        this.representative = representative;
    }

    public CompanyDTO getCompany() {
        return this.company;
    }

    public void setCompany(final CompanyDTO company) {
        this.company = company;
    }

}
