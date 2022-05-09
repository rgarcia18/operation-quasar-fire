package com.quasarfire.quasar.services;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import com.quasarfire.quasar.exceptions.MessageException;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class LocationService {

    @Autowired
    private Environment environment;

    public double[] getLocation(double[] distances) throws MessageException{

        double[][] locations = locations();

        //validate number of distances
        if(distances.length != locations.length) {
            throw new MessageException("Error in the number of distances.");
        }

        //get the secret position
        NonLinearLeastSquaresSolver ObjSquaresSolver = new NonLinearLeastSquaresSolver(
                new TrilaterationFunction(locations(), distances),
                new LevenbergMarquardtOptimizer()
        );

        LeastSquaresOptimizer.Optimum optimum = ObjSquaresSolver.solve();

        return optimum.getPoint().toArray();
    }

    private double[][] locations (){

        int satellitesTotal = Integer.parseInt(environment.getProperty("satellites.total"));
        double[][] locations = new double[satellitesTotal][];
        String[] locationsSatellite;
        for (int i = 0; i < satellitesTotal; i++) {
            locationsSatellite = environment.getProperty("satellites."+i+".position").split(",");
            locations[i] = Arrays.stream(locationsSatellite)
                    .map(Double::valueOf)
                    .mapToDouble(Double::doubleValue)
                    .toArray();
        }

        return locations;
    }
}
