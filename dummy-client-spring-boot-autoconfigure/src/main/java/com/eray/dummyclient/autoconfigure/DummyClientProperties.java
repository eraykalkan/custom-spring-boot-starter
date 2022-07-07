package com.eray.dummyclient.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@Data
@ConfigurationProperties(prefix = "dummy-client.init")
public class DummyClientProperties {

    private String name;
    private Integer port;
    private String type;
    private Integer version;
    private Duration timeout;

}
