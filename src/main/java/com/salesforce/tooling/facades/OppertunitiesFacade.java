package com.salesforce.tooling.facades;

import java.util.List;

import com.salesforce.tooling.entity.Opportunity;

/**
 * Facade interface for opportunities.
 */
public interface OppertunitiesFacade {

    /**
     * Retrieves all available opportunities.
     * @return list of {@link Opportunity}}
     */
    List<Opportunity> getAll();
}
