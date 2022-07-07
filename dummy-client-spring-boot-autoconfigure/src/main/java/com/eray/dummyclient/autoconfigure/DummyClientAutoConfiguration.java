package com.eray.dummyclient.autoconfigure;

import com.eray.dummyclient.domain.DummyClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(DummyClient.class)
@EnableConfigurationProperties(DummyClientProperties.class)
public class DummyClientAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "dummy-client",
            name = {"init.name",
                    "init.port",
                    "init.type",
                    "init.version",
                    "init.timeout"})
    public DummyClient dummyClient(DummyClientProperties dummyClientProperties) {
        return DummyClient
                .builder()
                .name(dummyClientProperties.getName())
                .port(dummyClientProperties.getPort())
                .type(dummyClientProperties.getType())
                .version(dummyClientProperties.getVersion())
                .timeout(dummyClientProperties.getTimeout())
                .build();
    }

}
