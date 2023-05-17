package com.cleverassets.crossroad;

import com.cleverassets.crossroad.result.Crossroad;

public class CrossroadTaskApplication {

        public static void main(String[] args) {
            var crossroad = new Crossroad(30,5,6,7,2,20,12);
            crossroad.startSimulation(60);
        }

}
