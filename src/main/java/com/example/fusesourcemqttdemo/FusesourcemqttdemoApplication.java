package com.example.fusesourcemqttdemo;

import com.example.fusesourcemqttdemo.mqttprovider.MqttProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FusesourcemqttdemoApplication {

    @Autowired
    private static MqttProvider mqttProvider;

    public static void main(String[] args) {
        SpringApplication.run(FusesourcemqttdemoApplication.class, args);
        mqttProvider.providerOfMqtt();
    }

}
