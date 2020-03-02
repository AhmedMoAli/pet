package com.salesforce.tooling.facades.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.salesforce.tooling.entity.Company;
import com.salesforce.tooling.entity.Opportunity;
import com.salesforce.tooling.entity.Representative;
import com.salesforce.tooling.facades.OppertunitiesFacade;
import com.salesforce.tooling.services.CompanyService;
import com.salesforce.tooling.services.RepresentativeService;
import com.salesforce.tooling.utils.GeoLocationUtil;

/**
 * Facade concrete implementation.
 */
@Component
public class OppertunitiesFacadeImpl implements OppertunitiesFacade {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private RepresentativeService representativeService;
    @Value("${rep.max.distance:100}")
    private int maxAllowedDistance;

    @Override
    public List<Opportunity> getAll() {

        List<Company> companies = companyService.getCompanies();
        List<Representative> representatives = representativeService.getRepresentatives();
        List<Representative> allocatedRepresentative = new ArrayList<Representative>();

        List<Opportunity> oppertunities = companies.stream().map(company -> {

            List<Representative> closestReps = representatives.stream()
                .filter(rep -> !allocatedRepresentative.contains(rep))
                .filter(rep -> filterRepresentativeByDistance(company, rep)).sorted(sortByDistance(company))
                .collect(Collectors.toList());
            
            Optional<Representative> rep = closestReps.stream().findFirst();

            if (rep.isPresent()) {
                Opportunity oppertunity = new Opportunity();
                oppertunity.setCompany(company);
                oppertunity.setRepresentative(rep.get());
                allocatedRepresentative.add(rep.get());
                return oppertunity;
            }
            return null;

        }).filter(Objects::nonNull).collect(Collectors.toList());

        return oppertunities;
    }

    /**
     * Filters representative by distance from company.
     * 
     * @param company
     *            the company to fetch nearest rep to.
     * @param representative
     *            the rep to check his/her distance to the company.
     * @return boolean if we should filter out this rep or no based on distance.
     */
    private boolean filterRepresentativeByDistance(final Company company, final Representative representative) {
        double distance = GeoLocationUtil.haversine(
            company.getLocation().getLatitude(),
            company.getLocation().getLongitude(), 
            representative.getLocation().getLatitude(),
            representative.getLocation().getLongitude());

        return 0 <= distance && maxAllowedDistance >= distance;
    }

    /**
     * Sorts reps by distance to specific company.
     * 
     * @param company
     *            the company to sort all reps againest.
     * @return comparator of reps.
     */
    private Comparator<Representative> sortByDistance(final Company company) {
        return Comparator.comparingDouble(rep -> 
                GeoLocationUtil.haversine(
                    company.getLocation().getLatitude(),
                    company.getLocation().getLongitude(), 
                    rep.getLocation().getLatitude(), 
                    rep.getLocation().getLongitude()));
    }
}