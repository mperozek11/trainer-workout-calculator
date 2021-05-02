package org.perom.trainercalculator.estimator;

import com.garmin.fit.Decode;
import com.garmin.fit.DeveloperFieldDescriptionListener;
import com.garmin.fit.MesgBroadcaster;
import com.garmin.fit.RecordMesgListener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class FitProcessor {

    private Decode decode;
    private MesgBroadcaster mesgBroadcaster;
    private TrainerListener trainerListener;

    public FitProcessor() {
        this.decode = new Decode();
        mesgBroadcaster = new MesgBroadcaster(decode);
        trainerListener = new TrainerListener();

    }

    /**
     * @param fileName
     * @param weight total weight of rider and bike
     * @return FitEstimateResponse for given fit file
     * @throws FileNotFoundException
     */
    public FitEstimateResponse getEstimate(String fileName, double weight) {

        try{
            FileInputStream in = new FileInputStream(fileName);
            FitProcessor fitPro = new FitProcessor();
            List<String[]> data = fitPro.loadFit(in);

            TrainerSpeedEstimator estimator = new TrainerSpeedEstimator();
            List<Double> velocities = estimator.estimateDistance(data, weight);

            //avg vel in m/s
            double avgVel = velocities.stream().mapToDouble(val -> val).average().orElse(0.0);

            return new FitEstimateResponse(fileName, avgVel, velocities.size());
        }
        catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }

    }

    private List<String[]> loadFit(FileInputStream in) {
        mesgBroadcaster.addListener((RecordMesgListener) trainerListener);
        decode.addListener((DeveloperFieldDescriptionListener) trainerListener);
        decode.read(in, mesgBroadcaster, mesgBroadcaster);

        //okay this looks good so far. We are gathering a list of all timestamps and power values at those times
        for (String[] arr : trainerListener.data) {
            for (String str : arr) {
                //System.out.println(str);
            }
        }

        return trainerListener.data;
    }

//    // === Simple tests ===
//    public static void main(String[] args) throws FileNotFoundException {
//        FitProcessor fitPro = new FitProcessor();
//        FitEstimateResponse response = fitPro.getEstimate("/Users/maxperozek/sample-fit-files/Kaiser.fit", 67, 9.1);
//
//        System.out.println(response.printImperialReport());
//
//    }


}

