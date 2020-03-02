package com.salesforce.tooling.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.salesforce.tooling.dto.OpportunityDTO;
import com.salesforce.tooling.entity.Opportunity;
import com.salesforce.tooling.facades.OppertunitiesFacade;

import io.swagger.annotations.Api;

/**
 * REST Controller for handling opportunities actions.
 */
@RestController
@RequestMapping("/api/v1")
@Api(value = "Opportunities endpoint")
public class OpportunitiesController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private OppertunitiesFacade oppertunitiesFacade;

    /**
     * Returns a list of opportunities.
     * 
     * @return List of {@link OpportunityDTO}.
     */
    @GetMapping(path = "/opportunities", produces = "application/json")
    public ResponseEntity<List<OpportunityDTO>> getOpertunities() {
        List<Opportunity> opportunities = oppertunitiesFacade.getAll();

        return new ResponseEntity<>(opportunities
            .stream()
            .map(opportunity -> modelMapper.map(opportunity, OpportunityDTO.class))
            .collect(Collectors.toList()),
            HttpStatus.OK);
    }
}
