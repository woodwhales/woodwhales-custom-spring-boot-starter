package org.woodwhales.starter;

import java.util.Objects;

/**
 * @projectName: woodwhales-spring-boot-starter
 * @author: woodwhales
 * @date: 20.3.14 17:31
 * @description:
 */
public class HelloService {

    private HelloProperties helloProperties;

    public HelloService() {
    }

    public HelloService(HelloProperties helloProperties) {
        this.helloProperties = helloProperties;
    }

    public String sayHello(String name) {
        Objects.requireNonNull(helloProperties, "读取用户配置文件失败");
        return String.format("%s - %s - %s", helloProperties.getPrefix(), name, helloProperties.getSuffix());
    }

}
