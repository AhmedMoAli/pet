package com.salesforce.tooling.facades;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesforce.tooling.entity.Company;
import com.salesforce.tooling.entity.Opportunity;
import com.salesforce.tooling.entity.Representative;
import com.salesforce.tooling.services.CompanyService;
import com.salesforce.tooling.services.RepresentativeService;

@SpringBootTest
public class OppertunitiesFacadeTest {

    @Autowired
    private OppertunitiesFacade oppertunitiesFacade;
    @MockBean
    private CompanyService companyService;
    @MockBean
    private RepresentativeService RepService;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() throws JsonParseException, JsonMappingException, IOException {

        MockitoAnnotations.initMocks(this);

        Mockito.when(companyService.getCompanies()).thenReturn(loadCompaniesFromJSON());
        Mockito.when(RepService.getRepresentatives()).thenReturn(loadRepresentativesFromJSON());
    }

    @Test
    public void testOpportunitiesListSize() {
        // we have 3 companies but only 2 are valid as the third resides in the north pole >100 KM
        List<Opportunity> oppertunities = oppertunitiesFacade.getAll();
        assertThat(oppertunities, hasSize(2));
    }

    @Test
    public void testOpportunitiesListContainsCorrectData() {
        // we have 3 companies but only 2 are valid as the third resides in the north pole >100 KM
        List<Opportunity> oppertunities = oppertunitiesFacade.getAll();

        Optional<Opportunity> albertsonsOpportiunity = oppertunities.stream()
            .filter(opportunity -> opportunity.getCompany().getName().equals("ALBERTSONS COS.")).findFirst();

        assertTrue(albertsonsOpportiunity.isPresent());
        assertThat(albertsonsOpportiunity.get().getRepresentative().getName(), equalTo("Rep 6"));
    }

    private List<Representative> loadRepresentativesFromJSON()
        throws JsonParseException, JsonMappingException, IOException {

        return objectMapper.readValue(new File(this.getClass().getResource("/reps.json").getFile()),
            new TypeReference<List<Representative>>() {
            });
    }

    private List<Company> loadCompaniesFromJSON() throws JsonParseException, JsonMappingException, IOException {
        return objectMapper.readValue(new File(this.getClass().getResource("/companies.json").getFile()),
            new TypeReference<List<Company>>() {
            });
    }
}
