package com.eray.dummyclient.domain;

import lombok.Builder;
import lombok.Value;

import java.time.Duration;

@Value
@Builder
public class DummyClient {

    String name;
    Integer port;
    String type;
    Integer version;
    Duration timeout;

}
