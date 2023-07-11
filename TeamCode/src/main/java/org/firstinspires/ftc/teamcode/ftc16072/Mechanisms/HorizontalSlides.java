package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.ftc16072.QQTest.QQtest;
import org.firstinspires.ftc.teamcode.ftc16072.QQTest.TestServo;

import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

public class HorizontalSlides implements Mechanism{
    public enum Position {
        BACK,
        MIDDLE,
        FRONT
    }
    Hashtable<Position, Double> positions = new Hashtable<>();

    public Servo slideServo;
    public final double BACK_SERVO_POSITION = 0.473; // preset values need to be tuned
    public final double MIDDLE_SERVO_POSITION = 0.757;
    public final double FRONT_SERVO_POSITION = 0.93;
    public final double SAFE_SERVO_POSITION = 0.8;
    public final double MANUAL_CHANGE= 0.001;

    private void fillPositions(){
        positions.clear();
        positions.put(Position.BACK, BACK_SERVO_POSITION);
        positions.put(Position.MIDDLE, MIDDLE_SERVO_POSITION);
        positions.put(Position.FRONT, FRONT_SERVO_POSITION);
    }

    @Override
    public void init(HardwareMap hwMap) {
        slideServo = hwMap.get(Servo.class,  "horizontal");
        fillPositions();
    }

    public boolean manualForward(){
        if (slideServo.getPosition()+MANUAL_CHANGE<=FRONT_SERVO_POSITION){
            slideServo.setPosition(slideServo.getPosition()+MANUAL_CHANGE);
            return true;
        }
        return false;
    }

    public boolean manualBackward(){
        if (slideServo.getPosition()-MANUAL_CHANGE>=BACK_SERVO_POSITION){
            slideServo.setPosition(slideServo.getPosition()-MANUAL_CHANGE);
            return true;
        }
        return false;
    }

    public void goToPosition(Position position){ // uses preset positions
        double desiredPosition = positions.get(position);
        slideServo.setPosition(desiredPosition);

    }

    @SuppressWarnings("unused")
    public void debug(Telemetry telemetry){
        telemetry.addData("Horizontal Slide Pos", slideServo.getPosition());
    }

    public double currentPosition(){
        return slideServo.getPosition();
    }

    @Override
    public List<QQtest> getTests() {
        return   Collections.singletonList(
                new TestServo("horizontal", FRONT_SERVO_POSITION, SAFE_SERVO_POSITION, slideServo)
        );
    }
    public boolean isSafe(){
        return slideServo.getPosition() >= SAFE_SERVO_POSITION;
    }

    @Override
    public String getName() {
        return "horizontalSlides";
    }
}
