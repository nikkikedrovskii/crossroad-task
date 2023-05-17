package com.cleverassets.crossroad;

import com.cleverassets.crossroad.use_case.CrossroadSimulationUseCase;

public class CrossroadTaskApplication {

        static CrossroadSimulationUseCase crossroadSimulationUseCase = new CrossroadSimulationUseCase();

        public static void main(String[] args) {

            crossroadSimulationUseCase.execute();
        }
}
