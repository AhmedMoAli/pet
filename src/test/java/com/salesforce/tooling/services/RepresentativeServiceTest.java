package com.salesforce.tooling.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import com.salesforce.tooling.controller.errorhandling.BadRequestException;
import com.salesforce.tooling.services.impl.RepresentativeServiceImpl;

@SpringBootTest
public class RepresentativeServiceTest {

    @Autowired
    RepresentativeService reprenentativeService;

    @Test
    public void testRepresentativeServiceReturnsReps() {
        assertNotNull(reprenentativeService.getRepresentatives());
    }

    @Test
    public void testRepresentativeServiceWithMalformedURL() {
        RepresentativeServiceImpl impl = new RepresentativeServiceImpl();

        ReflectionTestUtils.setField(impl, "repEndpointURL", "wrongURL");
        Assertions.assertThrows(BadRequestException.class, () -> {
            impl.getRepresentatives();
        });
    }

    @Test
    public void testRepresentativeServiceWithWrongURL() {
        RepresentativeServiceImpl impl = new RepresentativeServiceImpl();

        ReflectionTestUtils.setField(impl, "repEndpointURL", "http://www.google.com");
        Assertions.assertThrows(BadRequestException.class, () -> {
            impl.getRepresentatives();
        });
    }
}
