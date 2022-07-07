package com.eray.dummyclient.domain;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DummyClientTest {

    @Test
    void shouldConstructObject() {
        // Given
        String name = "Client";
        Integer port = 8181;
        int version = 2;
        String type = "Test Client";

        // When
        DummyClient dummyClient = DummyClient
                .builder()
                .name(name)
                .port(port)
                .version(version)
                .type(type)
                .build();

        // Then
        assertThat(dummyClient).isNotNull();
        assertThat(dummyClient.getName()).isEqualTo(name);
        assertThat(dummyClient.getPort()).isEqualTo(port);
        assertThat(dummyClient.getVersion()).isEqualTo(version);
        assertThat(dummyClient.getType()).isEqualTo(type);
    }

    @Test
    void shouldVerifyEqualsContract() {
        EqualsVerifier.forClass(DummyClient.class).verify();
    }

}
