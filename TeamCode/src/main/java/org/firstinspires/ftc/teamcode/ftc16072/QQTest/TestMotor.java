package org.firstinspires.ftc.teamcode.ftc16072.QQTest;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.jar.Attributes;

public class TestMotor extends QQtest {
    DcMotor motor;
    double speed;

    public TestMotor(String name, double speed, DcMotor motor) {
        super(name);
        this.speed = speed;
        this.motor = motor;
    }

    @Override
    public void run(boolean on, Telemetry telemetry){
        if (on){
            motor.setPower(speed);
        }
        else{
            motor.setPower(0);
        }
        telemetry.addData("Encoder Value", motor.getCurrentPosition());
    }



}
