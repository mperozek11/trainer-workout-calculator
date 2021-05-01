package org.perom.trainercalculator;

import java.util.ArrayList;
import java.util.List;

public class TrainerSpeedEstimator {

    private static final double G = 9.8067;
    private static final double Crr = 0.006; // rolling resistance coefficient estimate for road bike tires on asphalt
    private double Cd; // drag coefficient
    private double A; // frontal area
    private static final double CdA = 0.30; // estimate drag coefficient * frontal area
    private static final double Rho = 1.22601; // air density estimate


    // this will eventually return a TrainerWorkoutReport obj
    public List<Double> estimateDistance(List<String[]> data, double riderWeight, double bikeWeight) {

        List<Double> velocities = new ArrayList<>();

        double currVelocity = 0.1;
        double pNeeded;
        double prevTime = Double.parseDouble(data.get(0)[0]);
        double currTime;
        double accel;
        double pExcess;

        velocities.add(0.0); // start from rest

        for (int i = 1; i < data.size() ; i++) {
            pNeeded = getPNeeded(currVelocity, 0, riderWeight + bikeWeight);
            pExcess = Double.parseDouble(data.get(i)[1]) - pNeeded;
            accel = (pExcess / currVelocity) / (riderWeight + bikeWeight);

            currVelocity = currVelocity + (accel * 1);
            velocities.add(currVelocity);
        }
        return velocities;

    }

    /**
     * Gets the power needed to maintain a given velocity
     *
     * @param velocity
     * @param grade
     * @param weight
     * @return power in watts
     */
    private double getPNeeded(double velocity, double grade, double weight) {
        //pNeeded = [fGravity + fRolling + fDrag] * V


        double fGravity = G * Math.sin(Math.atan(grade / 100)) * weight;
        double fRolling = G * Math.cos(Math.atan(grade / 100)) * weight * Crr;
        double fDrag = 0.5 * CdA * Rho * Math.pow(velocity, 2);

        return (fGravity + fRolling + fDrag) * velocity;
    }

}
