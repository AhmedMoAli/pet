package com.salesforce.tooling.entity;

/**
 * Entity that represents the opportunity.
 */
public class Opportunity {

    private Representative representative;
    private Company company;

    public Representative getRepresentative() {
        return this.representative;
    }

    public void setRepresentative(Representative representative) {
        this.representative = representative;
    }

    public Company getCompany() {
        return this.company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
