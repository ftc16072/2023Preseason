package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.ftc16072.QQTest.QQtest;
import org.firstinspires.ftc.teamcode.ftc16072.QQTest.TestServo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Claw implements Mechanism{
    public Servo claw;
    private ColorRangeSensor sensor;
    public static double GRIPPED_SERVO_POSITION = 0.15;
    public static double RELEASED_SERVO_POSITION = 0.30;

    public enum TeamColor {
        blue,
        red,
        unknown
    }
    private TeamColor teamColor;
    @Override
    public void init(HardwareMap hwMap) {
        claw = hwMap.get(Servo.class,"claw");
        sensor = hwMap.get(ColorRangeSensor.class,"cone_detector");
        claw.setPosition(RELEASED_SERVO_POSITION);
        teamColor = TeamColor.unknown;
        while(teamColor.equals(TeamColor.unknown)){
        if(sensor.blue()>sensor.red()){
            teamColor = TeamColor.blue;
        }
        else if(sensor.red()>sensor.blue()){
            teamColor = TeamColor.red;
        }
        else{
            teamColor = TeamColor.unknown;
        }}



    }
    @Override
    public List<QQtest> getTests() {

        return Arrays.asList(
                new TestServo("Claw", 0.3, claw)
        );
    }

    @Override
    public String getName() {
        return null;
    }

    public void manualClose(){
        if(CorrectColor()) {
            claw.setPosition(GRIPPED_SERVO_POSITION);
        }
    /*} d
    else{gamepad1.rumble(10);}*/
    }
    public void manualOpen(){
        claw.setPosition(RELEASED_SERVO_POSITION);
    }
    public boolean inRange(){
        return sensor.getDistance(DistanceUnit.INCH) <= 3;
    }
    public boolean CorrectColor() {
        if ((teamColor.equals(TeamColor.blue)) && (sensor.blue() > sensor.red())) {
            return true;
        }
        return teamColor.equals(TeamColor.red) && (sensor.blue() < sensor.red());
     }
    public void Autograb() {
        if (CorrectColor() && inRange()) {
            claw.setPosition(GRIPPED_SERVO_POSITION);
        }
    }}