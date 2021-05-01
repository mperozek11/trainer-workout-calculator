package org.perom.trainercalculator;

public class TrainerWorkoutReport {
    private String name;
    private double distanceMi;
    private double distanceKm;

    public enum Unit{
        MILES,
        KILOMETERS
    }
    public TrainerWorkoutReport(String name, double distance, Unit unit) {
        this.name = name;
        this.distanceMi = distanceMi;
        this.distanceKm = distanceKm;
    }

    public String getName() {
        return name;
    }

    public double getDistanceMi() {
        return distanceMi;
    }

    public double getDistanceKm() {
        return distanceKm;
    }

}
