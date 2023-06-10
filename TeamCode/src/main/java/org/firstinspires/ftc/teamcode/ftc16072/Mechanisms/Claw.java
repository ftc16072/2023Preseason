package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.ftc16072.QQTest.QQtest;

import java.util.Arrays;

/*
Things to do:
COMMENTS COMMENTS COMMENTS
1. Set vars = done
2. Manual open/close = teleop
3. Auto detect
    a. Color
    b. Distance->grab
4. QQTest
*/
public class Claw extends Mechanism{
    //Distance for claw to grip the cone
    public static final double CONE_GRIP_RANGE = 4;

    //Initialize Servo and ColorSensor
    public  Servo clawServo;

    private ColorRangeSensor colorSensor;

    //Set various states for types of cones
    public enum ConeType{
        BLUE,
        RED,
        NONE
    }

    //Position Servo is at to be "closed"
    public static double GRIPPED_SERVO_POSITION = 0.15;

    //Position Servo is at to be "open"
    public static double OPEN_SERVO_POSITION = 0.30;



    //State of the claw
    public enum State {
        //Open Claw
        OPEN,
        //Claw is moving
        MOVING,
        //Full Claw (closed)
        CLOSED
    }

    //Initializing a variable "state"
    private State state;

    //Function set servo position to load, change state as well
    public void close_servo(){
        clawServo.setPosition(GRIPPED_SERVO_POSITION);
        state = State.CLOSED;
    }

    //Function
    public void open_servo(){
        clawServo.setPosition(OPEN_SERVO_POSITION);
        state = State.OPEN;
    }
    //return state
    public State getState() { return state; }

    //Init clawServo and ColorSensor
    @Override
    public void init(HardwareMap hwMap) {
        clawServo = hwMap.get(Servo.class, "claw");
        colorSensor = hwMap.get(ColorRangeSensor.class, "color_sensor");

        //Set starting robot configuration to a closed claw and set state to closed as well
        state= State.CLOSED;
        close_servo();
    }
    
}


