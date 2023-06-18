package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.ftc16072.QQTest.QQtest;

import java.util.List;

public class HorizontalSlides implements Mechanism{
    public Servo HorizontalSlide;
    public final double BACK_SERVO_POSITION= 1; // preset values need to be tuned
    public final double MIDDLE_SERVO_POSITION = 1;
    public final double FRONT_SERVO_POSITION = 1;




    /*
    goto (back, middle, front)


    manual forward and backward
     */
    @Override
    public void init(HardwareMap hwMap) {
        HorizontalSlide = hwMap.get(Servo.class, "horizontal_slide");


    }

    public void manualForward(){
        //
        HorizontalSlide.setPosition();
    }

    public void manualBackward(){

    }

    public void goToPosition(double position){

        HorizontalSlide.setPosition(position);

    }

    @Override
    public List<QQtest> getTests() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }
}
