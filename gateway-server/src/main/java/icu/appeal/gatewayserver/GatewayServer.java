package icu.appeal.gatewayserver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class GatewayServer {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServer.class, args);
    }

    @Value("${token.shared_key}")
    String key;

    @PostConstruct
    public void post(){
        System.out.println(key);
    }
}
