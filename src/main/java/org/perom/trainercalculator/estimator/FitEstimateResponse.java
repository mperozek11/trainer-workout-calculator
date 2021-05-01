package org.perom.trainercalculator.estimator;

public class FitEstimateResponse {
    private String name;
    private double avgVel;

    private double avgKph;
    private double avgMph;

    private double distanceMi;
    private double distanceKm;

    /**
     * Calculates and stores
     *
     * @param name   fit file name for this report
     * @param avgVel m/s
     * @param time   seconds
     */
    public FitEstimateResponse(String name, double avgVel, double time) {
        this.name = name;
        this.avgVel = avgVel;

        this.avgMph = avgVel * 2.23694;
        this.avgKph = avgVel * 3.6;
        this.distanceMi = (time * 0.000277778) * avgMph;
        this.distanceKm = (time * 0.000277778) * avgKph;
    }

    public String printImperialReport() {
        return "Report for: " + this.name + "\nAverage speed: " + this.avgMph + " mph\nDistance: " + distanceMi + " miles";
    }

    public String printMetricReport() {
        return "Report for: " + this.name + "\nAverage speed: " + this.avgKph + " kph\nDistance: " + distanceKm + " kilometers";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAvgVel() {
        return avgVel;
    }

    public void setAvgVel(double avgVel) {
        this.avgVel = avgVel;
    }

    public double getAvgKph() {
        return avgKph;
    }

    public void setAvgKph(double avgKph) {
        this.avgKph = avgKph;
    }

    public double getAvgMph() {
        return avgMph;
    }

    public void setAvgMph(double avgMph) {
        this.avgMph = avgMph;
    }

    public double getDistanceMi() {
        return distanceMi;
    }

    public void setDistanceMi(double distanceMi) {
        this.distanceMi = distanceMi;
    }

    public double getDistanceKm() {
        return distanceKm;
    }

    public void setDistanceKm(double distanceKm) {
        this.distanceKm = distanceKm;
    }
}
