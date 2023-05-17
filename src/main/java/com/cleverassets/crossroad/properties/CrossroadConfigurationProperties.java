package com.cleverassets.crossroad.properties;

import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Getter
public class CrossroadConfigurationProperties {

    private Integer northTraffic;
    private Integer southTraffic;
    private Integer westTraffic;
    private Integer eastTraffic;
    private Integer northSouthTrafficLightDuration;
    private Integer westEastTrafficLightDuration;
    private Integer trafficDelay;
    private Integer durationOfSimulation;

    public CrossroadConfigurationProperties() {
        Properties properties = new Properties();
        try (InputStream inputStream = CrossroadConfigurationProperties.class.getClassLoader().getResourceAsStream("configuration.properties")) {
            properties.load(inputStream);

            this.northTraffic = Integer.valueOf(properties.getProperty("northTraffic"));
            this.southTraffic = Integer.valueOf(properties.getProperty("southTraffic"));
            this.westTraffic = Integer.valueOf(properties.getProperty("westTraffic"));
            this.eastTraffic = Integer.valueOf(properties.getProperty("eastTraffic"));
            this.northSouthTrafficLightDuration  = Integer.valueOf(properties.getProperty("northSouthTrafficLightDuration"));
            this.westEastTrafficLightDuration = Integer.valueOf(properties.getProperty("westEastTrafficLightDuration"));
            this.trafficDelay = Integer.valueOf(properties.getProperty("trafficDelay"));
            this.durationOfSimulation = Integer.valueOf(properties.getProperty("durationOfSimulation"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
