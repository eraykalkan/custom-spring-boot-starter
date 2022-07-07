package com.eray.dummycluent.autoconfigure;

import com.eray.dummyclient.autoconfigure.DummyClientAutoConfiguration;
import com.eray.dummyclient.autoconfigure.DummyClientProperties;
import com.eray.dummyclient.domain.DummyClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

class DummyClientAutoConfigurationTest {

    private static final String PREFIX = "dummy-client.init";

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(DummyClientAutoConfiguration.class));

    String name;
    int port;
    int version;
    String type;
    Duration timeout;

    @BeforeEach
    void setup() {
        name = "Client";
        port = 8181;
        version = 2;
        type = "Test Client";
        timeout = Duration.ofMillis(3000);
    }

    @Test
    void shouldBuildContextSuccessfullyWithPropertiesDefined() {

        givenApplicationContextRunnerWithProperties()
                .run(context -> {
                    assertThat(context).hasSingleBean(DummyClient.class);
                    assertThat(context).getBean(DummyClient.class).isNotNull();
                    assertThat(context.getBean(DummyClient.class).getName()).isEqualTo(name);
                    assertThat(context.getBean(DummyClient.class).getPort()).isEqualTo(port);
                    assertThat(context.getBean(DummyClient.class).getType()).isEqualTo(type);
                    assertThat(context.getBean(DummyClient.class).getVersion()).isEqualTo(version);
                    assertThat(context.getBean(DummyClient.class).getTimeout()).isEqualTo(timeout);
                });

    }

    @Test
    void shouldBuildContextWithoutAutoConfiguredDummyClient() {
        contextRunner
                .withUserConfiguration(DummyClientProperties.class)
                .run(context -> assertThat(context).doesNotHaveBean(DummyClient.class));
    }

    @Test
    void shouldNotInitializeAnotherDummyClientIfThereIsAlreadyOne() {
        givenApplicationContextRunnerWithProperties()
                .withUserConfiguration(ExistingDummyClientConfig.class)
                .run(context -> {
                    assertThat(context).hasSingleBean(DummyClient.class);
                    assertThat(context.getBean(DummyClient.class).getName()).isNotEqualTo(name);
                    assertThat(context.getBean(DummyClient.class).getPort()).isNotEqualTo(port);
                    assertThat(context.getBean(DummyClient.class).getType()).isNotEqualTo(type);
                    assertThat(context.getBean(DummyClient.class).getVersion()).isNotEqualTo(version);
                    assertThat(context.getBean(DummyClient.class).getTimeout()).isNotEqualTo(timeout);
                });
    }

    @Configuration
    static class ExistingDummyClientConfig {
        @Bean
        public DummyClient alreadyConfiguredDummyClient() {
            return DummyClient
                    .builder()
                    .name("Already Exists")
                    .type("Unknown")
                    .port(9999)
                    .version(1)
                    .timeout(Duration.ofMillis(1000))
                    .build();
        }
    }

    private ApplicationContextRunner givenApplicationContextRunnerWithProperties() {

        return contextRunner.withPropertyValues(
                buildProperty("name", name),
                buildProperty("port", String.valueOf(port)),
                buildProperty("version", String.valueOf(version)),
                buildProperty("type", type),
                buildProperty("timeout", timeout.toString())
        );
    }

    private String buildProperty(String name, String value) {
        return PREFIX + "." + name + "=" + value;
    }

}
