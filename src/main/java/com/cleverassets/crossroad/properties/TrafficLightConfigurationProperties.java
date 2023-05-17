package com.cleverassets.crossroad.properties;

import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.cleverassets.crossroad.common.util.UtilProperty.NORTH_SOUTH_TRAFFIC_LIGHT_DURATION;
import static com.cleverassets.crossroad.common.util.UtilProperty.WEST_EAST_TRAFFIC_LIGHT_DURATION;

@Getter
public class TrafficLightConfigurationProperties {

    private Integer northSouthTrafficLightGreenDuration;
    private Integer westEastTrafficLightGreenDuration;


    public TrafficLightConfigurationProperties() {
        Properties properties = new Properties();
        try (InputStream inputStream = TrafficLightConfigurationProperties.class.getClassLoader().getResourceAsStream("configuration.properties")) {
            properties.load(inputStream);

            this.northSouthTrafficLightGreenDuration  = Integer.valueOf(properties.getProperty(NORTH_SOUTH_TRAFFIC_LIGHT_DURATION));
            this.westEastTrafficLightGreenDuration = Integer.valueOf(properties.getProperty(WEST_EAST_TRAFFIC_LIGHT_DURATION));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
