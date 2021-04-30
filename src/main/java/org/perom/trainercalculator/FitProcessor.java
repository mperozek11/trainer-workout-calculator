package org.perom.trainercalculator;

import com.garmin.fit.Decode;
import com.garmin.fit.DeveloperFieldDescriptionListener;
import com.garmin.fit.MesgBroadcaster;
import com.garmin.fit.RecordMesgListener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FitProcessor {

    private Decode decode;
    private MesgBroadcaster mesgBroadcaster;
    private TrainerListener trainerListener;


    public FitProcessor(){
        this.decode = new Decode();
        mesgBroadcaster = new MesgBroadcaster(decode);
        trainerListener = new TrainerListener();

    }

    public void loadFit(FileInputStream in){
        mesgBroadcaster.addListener((RecordMesgListener)trainerListener);
        decode.addListener((DeveloperFieldDescriptionListener)trainerListener);
        decode.read(in, mesgBroadcaster, mesgBroadcaster);
//        System.out.println(trainerListener.data);

        //okay this looks good so far. We are gathering a list of all timestamps and power values at those times
        for (String[] arr : trainerListener.data){
            for(String str : arr){
                System.out.println(str);
            }
        }


    }

    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream in = new FileInputStream("/Users/maxperozek/sample-fit-files/Kaiser.fit");

        FitProcessor fitPro = new FitProcessor();
        fitPro.loadFit(in);
    }


}

