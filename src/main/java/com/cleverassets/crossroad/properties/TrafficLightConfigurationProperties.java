package com.cleverassets.crossroad.properties;

import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Getter
public class TrafficLightConfigurationProperties {

    private Integer northSouthTrafficLightGreenDuration;
    private Integer westEastTrafficLightGreenDuration;


    public TrafficLightConfigurationProperties() {
        Properties properties = new Properties();
        try (InputStream inputStream = TrafficLightConfigurationProperties.class.getClassLoader().getResourceAsStream("configuration.properties")) {
            properties.load(inputStream);

            this.northSouthTrafficLightGreenDuration  = Integer.valueOf(properties.getProperty("northSouthTrafficLightDuration"));
            this.westEastTrafficLightGreenDuration = Integer.valueOf(properties.getProperty("westEastTrafficLightDuration"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
