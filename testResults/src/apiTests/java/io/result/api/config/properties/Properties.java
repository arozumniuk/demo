package io.result.api.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("url")
public class Properties {
    private String api;
    private String ui;
}
