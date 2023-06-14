package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.ftc16072.QQTest.QQtest;
import java.util.List;

public class Claw implements Mechanism{
    public Servo claw;
    private ColorRangeSensor sensor;
    public static double GRIPPED_SERVO_POSITION = 0.15;
    public static double RELEASED_SERVO_POSITION = 0.30;

    public enum team_color {
        blue,
        red
    }
    private team_color Team_color;
    @Override
    public void init(HardwareMap hwMap) {
        claw = hwMap.get(Servo.class,"claw");
        sensor = hwMap.get(ColorRangeSensor.class,"cone_detector");
        claw.setPosition(RELEASED_SERVO_POSITION);
        if(sensor.blue()>sensor.red()){
            Team_color = team_color.blue;
        }else{
            Team_color = team_color.red;
        }



    }

    @Override
    public List<QQtest> getTests() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    public void manualClose(){
        claw.setPosition(GRIPPED_SERVO_POSITION);
    }
    public void manualOpen(){
        claw.setPosition(RELEASED_SERVO_POSITION);
    }
    public void Autograb(){
        // if inRange and correctColor return True
        // then Close the claw

    }
    public boolean inRange(){


    }
    public boolean correctColor(){
        /*

         */

    }

    /*

    Manual Close
    Manual Open
    Autograb
     - Checks color
     - checks range
     - after grab move lift

     */
}
