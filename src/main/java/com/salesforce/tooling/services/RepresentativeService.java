package com.salesforce.tooling.services;

import java.util.List;

import com.salesforce.tooling.entity.Representative;

/**
 * A representative service interface which holds actions carried against representative service endpoint.
 */
public interface RepresentativeService {

    /**
     * Gets all representatives.
     * 
     * @return list of {@link Representative}.
     */
    List<Representative> getRepresentatives();
}
