package com.salesforce.tooling.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import com.salesforce.tooling.controller.errorhandling.BadRequestException;
import com.salesforce.tooling.services.impl.CompanyServiceImpl;

@SpringBootTest()
public class CompanyServiceTest {

    @Autowired
    CompanyService companyService;

    @Test
    public void testCompanyServiceReturnsCompanies() {
        assertNotNull(companyService.getCompanies());
    }

    @Test
    public void testCompanyServicWithMalformedURL() {
        CompanyServiceImpl impl = new CompanyServiceImpl();

        ReflectionTestUtils.setField(impl, "companiesEndpointURL", "wrongURL");
        Assertions.assertThrows(BadRequestException.class, () -> {
            impl.getCompanies();
        });
    }

    @Test
    public void testCompanyServicWithWrongURL() {
        CompanyServiceImpl impl = new CompanyServiceImpl();

        ReflectionTestUtils.setField(impl, "companiesEndpointURL", "http://www.google.com");
        Assertions.assertThrows(BadRequestException.class, () -> {
            impl.getCompanies();
        });
    }
}
