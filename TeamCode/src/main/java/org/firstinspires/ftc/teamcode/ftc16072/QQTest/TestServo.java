package org.firstinspires.ftc.teamcode.ftc16072.QQTest;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TestServo extends  QQtest{
    double onPosition;
    double offPostion;
    Servo servo;

    public TestServo(String name, double onPosition, double offPosition, Servo servo) {
        super(name);
        this.onPosition = onPosition;
        this.offPostion = offPosition;
        this.servo = servo;

    }

    @Override
    public void run(boolean on, Telemetry telemetry) {
        if(on){
            servo.setPosition(onPosition);
        }
        else{
            servo.setPosition(offPostion);
        }
        telemetry.addData("Current position", this.servo.getPosition());


    }
}
