package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.ftc16072.QQTest.QQtest;
import org.firstinspires.ftc.teamcode.ftc16072.QQTest.TestMotor;
import org.firstinspires.ftc.teamcode.ftc16072.QQTest.TestServo;
import org.firstinspires.ftc.teamcode.ftc16072.QQTest.TestTouchSensor;

import java.util.Arrays;
import java.util.List;

public class Temp implements Mechanism {
    public static final double CLAW_OPEN = 0.5;
    public static final double CLAW_CLOSE = 0.75;
    public static final double WHEEL_SPEED = -0.4;
    private Servo tempServo;
    private DcMotor tempMotor;
    private TouchSensor tempSensor;

    public void init(HardwareMap hwMap) {
        tempServo = hwMap.get(Servo.class, "claw");
        tempMotor = hwMap.get(DcMotor.class, "wheel");
        tempSensor = hwMap.get(TouchSensor.class, "cone_detector");
    }

    @Override
    public List<QQtest> getTests() {
        return Arrays.asList(
                new TestServo("tempServo", CLAW_OPEN, CLAW_CLOSE, tempServo),
                new TestMotor("tempMotor", WHEEL_SPEED, tempMotor),
                new TestTouchSensor("tempSensor", tempSensor)
        );
    }

    public void open() {
        tempServo.setPosition(CLAW_OPEN);
    }

    public void close() {
        tempServo.setPosition(CLAW_CLOSE);
    }

    public void spin() {
        tempMotor.setPower(WHEEL_SPEED);
    }



    @Override
    public String getName() {
        return "Temp";
    }

}
