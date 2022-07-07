package com.eray.dummyclient.sampleapp;

import com.eray.dummyclient.domain.DummyClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SampleAppTest {

    @Autowired
    private DummyClient dummyClient;

    @Test
    void shouldInjectDummyClient() {
        assertThat(dummyClient).isNotNull();
        assertThat(dummyClient.getName()).isNotNull();
        assertThat(dummyClient.getPort()).isNotNull().isNotZero();
        assertThat(dummyClient.getType()).isNotNull();
        assertThat(dummyClient.getVersion()).isNotNull();
        assertThat(dummyClient.getTimeout()).isNotNull().isPositive();
    }

}
