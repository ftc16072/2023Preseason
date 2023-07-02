package org.firstinspires.ftc.teamcode.ftc16072.QQTest;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TestServo extends  QQtest{
    double position;
    Servo servo;
    public TestServo(String name, double position, Servo servo) {
        super(name);
        this.position = position;
        this.servo = servo;

    }

    @Override
    public void run(boolean on, Telemetry telemetry) {
        if(on){
            servo.setPosition(position);
        }
        else{
            servo.setPosition(0);
        }
        telemetry.addData("Current position", this.servo.getPosition());


    }
}
