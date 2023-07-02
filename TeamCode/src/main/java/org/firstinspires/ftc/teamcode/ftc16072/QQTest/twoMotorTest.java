package org.firstinspires.ftc.teamcode.ftc16072.QQTest;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class twoMotorTest extends QQtest{
    DcMotorEx motorRight;
    DcMotorEx motorLeft;
    double speed;


    public twoMotorTest(String name, DcMotorEx motorLeft, DcMotorEx motorRight, double speed) {
        super(name);

        this.speed = speed;
        this.motorRight = motorRight;
        this.motorLeft = motorLeft;

    }
    @Override
    public void run(boolean on, Telemetry telemetry) {
        if (on){
            motorRight.setPower(1);
            motorLeft.setPower(-1);

        } else{
            motorRight.setPower(0);
            motorLeft.setPower(0);

        }
        telemetry.addData("Current Position", motorLeft.getCurrentPosition());

    }
}
