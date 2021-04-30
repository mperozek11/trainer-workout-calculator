package org.perom.trainercalculator;

import com.garmin.fit.*;

import java.util.ArrayList;
import java.util.List;

public class TrainerListener implements RecordMesgListener, DeveloperFieldDescriptionListener {

    List<String[]> data = new ArrayList<>();

    public TrainerListener(){
        data.add(new String[] {"timestamp", "power"});
    }

    @Override
    public void onMesg(RecordMesg mesg) {

//        System.out.println("Record:");
//        printValues(mesg, RecordMesg.PowerFieldNum);
//        printValues(mesg, RecordMesg.TimestampFieldNum);
        //Each timestamp is a second
        data.add(new String[] { getFieldVal(mesg, RecordMesg.TimestampFieldNum), getFieldVal(mesg, RecordMesg.PowerFieldNum)});

//        printValues(mesg, RecordMesg.SpeedFieldNum);

    }
    private String getFieldVal(Mesg mesg, int fieldNum){
        return mesg.getField(fieldNum).getValue().toString();

    }

    private void printValues(Mesg mesg, int fieldNum) {
        Iterable<FieldBase> fields = mesg.getOverrideField((short) fieldNum);
        Field profileField = Factory.createField(mesg.getNum(), fieldNum);
        boolean namePrinted = false;

        if (profileField == null) {
            return;
        }

        for (FieldBase field : fields) {
            if (!namePrinted) {
                System.out.println("   " + profileField.getName() + ":");
                namePrinted = true;
            }

            if (field instanceof Field) {
                System.out.println("      native: " + field.getValue());
            } else {
                System.out.println("      override: " + field.getValue());
            }
        }
    }

    @Override
    public void onDescription(DeveloperFieldDescription developerFieldDescription) {
        System.out.println("*Developer Field description*");
    }
}
