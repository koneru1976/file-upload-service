package com.rkoneru.fileuploaderservice.config;

import org.junit.jupiter.api.Test;
import springfox.documentation.spring.web.plugins.Docket;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class SpringFoxConfigurationTest {

    @Test
    void shouldReturnDocketBean() {
        Docket docket = new SpringFoxConfiguration().api();

        assertNotNull(docket);
    }
}
