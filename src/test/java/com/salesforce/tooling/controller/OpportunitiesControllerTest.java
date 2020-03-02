package com.salesforce.tooling.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesforce.tooling.entity.Company;
import com.salesforce.tooling.entity.Representative;
import com.salesforce.tooling.services.CompanyService;
import com.salesforce.tooling.services.RepresentativeService;

@SpringBootTest
@AutoConfigureMockMvc
public class OpportunitiesControllerTest {

    private static final String LIST_URL = "/api/v1/opportunities";
    @Autowired
    private MockMvc mockMvc;
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
    public void testGetAllOpportunities() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(OpportunitiesControllerTest.LIST_URL))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
            .andExpect(MockMvcResultMatchers.jsonPath("$..company.name",
                Matchers.containsInAnyOrder("ALBERTSONS COS.", "MICRON TECHNOLOGY")))
            .andReturn();
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
