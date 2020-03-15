package org.woodwhales.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @projectName: woodwhales-spring-boot-starter
 * @author: woodwhales
 * @date: 20.3.14 17:30
 * @description:
 */
@ConfigurationProperties("woodwhales.hello")
public class HelloProperties {

    private String prefix;
    private String suffix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
