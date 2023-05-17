package com.cleverassets.crossroad.properties;

import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static com.cleverassets.crossroad.common.util.UtilProperty.DURATION_OF_SIMULATION;
import static com.cleverassets.crossroad.common.util.UtilProperty.EAST_TRAFFIC;
import static com.cleverassets.crossroad.common.util.UtilProperty.NORTH_TRAFFIC;
import static com.cleverassets.crossroad.common.util.UtilProperty.SOUTH_TRAFFIC;
import static com.cleverassets.crossroad.common.util.UtilProperty.TRAFFIC_DELAY;
import static com.cleverassets.crossroad.common.util.UtilProperty.WEST_TRAFFIC;

@Getter
public class CrossroadConfigurationProperties {

    private Integer northTraffic;
    private Integer southTraffic;
    private Integer westTraffic;
    private Integer eastTraffic;
    private Integer trafficDelay;
    private Integer durationOfSimulation;

    public CrossroadConfigurationProperties() {
        Properties properties = new Properties();
        try (InputStream inputStream = CrossroadConfigurationProperties.class.getClassLoader().getResourceAsStream("configuration.properties")) {
            properties.load(inputStream);

            this.northTraffic = Integer.valueOf(properties.getProperty(NORTH_TRAFFIC));
            this.southTraffic = Integer.valueOf(properties.getProperty(SOUTH_TRAFFIC));
            this.westTraffic = Integer.valueOf(properties.getProperty(WEST_TRAFFIC));
            this.eastTraffic = Integer.valueOf(properties.getProperty(EAST_TRAFFIC));
            this.trafficDelay = Integer.valueOf(properties.getProperty(TRAFFIC_DELAY));
            this.durationOfSimulation = Integer.valueOf(properties.getProperty(DURATION_OF_SIMULATION));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
