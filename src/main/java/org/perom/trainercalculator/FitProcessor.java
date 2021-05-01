package org.perom.trainercalculator;

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


    public FitProcessor(){
        this.decode = new Decode();
        mesgBroadcaster = new MesgBroadcaster(decode);
        trainerListener = new TrainerListener();

    }

    public List<String[]> loadFit(FileInputStream in){
        mesgBroadcaster.addListener((RecordMesgListener)trainerListener);
        decode.addListener((DeveloperFieldDescriptionListener)trainerListener);
        decode.read(in, mesgBroadcaster, mesgBroadcaster);
//        System.out.println(trainerListener.data);

        //okay this looks good so far. We are gathering a list of all timestamps and power values at those times
        for (String[] arr : trainerListener.data){
            for(String str : arr){
                //System.out.println(str);
            }
        }

        return trainerListener.data;


    }


    // === TEST ===
    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "/Users/maxperozek/sample-fit-files/Kaiser.fit";
//        String fileName = "/Users/maxperozek/sample-fit-files/Collins.fit"; // looks like tr files will run just fine

//        String fileName = "/Users/maxperozek/sample-fit-files/Z2.fit"; // we have issues with using outdoor ride files

        FileInputStream in = new FileInputStream(fileName);

        FitProcessor fitPro = new FitProcessor();
        List<String[]> data = fitPro.loadFit(in);
        TrainerSpeedEstimator estimator = new TrainerSpeedEstimator();
        List<Double> velocities = estimator.estimateDistance(data, 67,9.1);
        double total = 0;
        for (Double vel: velocities) {
            total += vel;
            System.out.println(vel);
        }
        System.out.println("avg velocity (m/s): " + total / velocities.size());
        double mph = (total / velocities.size()) * 2.23694;
        System.out.println("avg velocity (mph): " + mph);
        System.out.println("est distance: " + (velocities.size() * 0.000277778) * mph + "miles");

    }


}

