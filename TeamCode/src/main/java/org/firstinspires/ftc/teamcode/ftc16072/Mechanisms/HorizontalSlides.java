package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.ftc16072.QQTest.QQtest;
import org.firstinspires.ftc.teamcode.ftc16072.QQTest.TestServo;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Handler;

public class HorizontalSlides implements Mechanism{
    public enum Position {
        BACK,
        MIDDLE,
        FRONT
    }
    Hashtable<Position, Double> positions = new Hashtable<>();
    Position presets;


    public Servo HorizontalSlide;
    public final double BACK_SERVO_POSITION = 0.473; // preset values need to be tuned
    public final double MIDDLE_SERVO_POSITION = 0.757;
    public final double FRONT_SERVO_POSITION = 1;

    private void fillPositions(Position position){
        positions.clear();
        positions.put(Position.BACK, BACK_SERVO_POSITION);
        positions.put(Position.MIDDLE, MIDDLE_SERVO_POSITION);
        positions.put(Position.FRONT, FRONT_SERVO_POSITION);
    }

    @Override
    public void init(HardwareMap hwMap) {
        HorizontalSlide = hwMap.get(Servo.class,  "horizontal");
        fillPositions(presets);

    }

    public void manualForward(){
        if (HorizontalSlide.getPosition()+0.001<=1){
            HorizontalSlide.setPosition(HorizontalSlide.getPosition()+0.001);

        }

    }

    public void manualBackward(){
        if (HorizontalSlide.getPosition()-0.001>=0.473){
            HorizontalSlide.setPosition(HorizontalSlide.getPosition()-0.001);
        }


    }//
    public void stop(){
        HorizontalSlide.setPosition(HorizontalSlide.getPosition());
    }


    public void goToPosition(Position position){ // uses preset positions
        double desiredPosition = positions.get(position);
        HorizontalSlide.setPosition(desiredPosition);

    }
    public double currentPosition(){
        return HorizontalSlide.getPosition();
    }

    @Override
    public List<QQtest> getTests() {
        return   Arrays.asList(
                new TestServo("horizontal", 1,HorizontalSlide )
        );

    }

    @Override
    public String getName() {
        return null;
    }
}
