package org.firstinspires.ftc.teamcode.ftc16072.QQTest;

import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TestTwoMotor extends QQtest{
    DcMotorEx motorRight;
    DcMotorEx motorLeft;
    double speed;


    public TestTwoMotor(String name, DcMotorEx motorLeft, DcMotorEx motorRight, double speed) {
        super(name);

        this.speed = speed;
        this.motorRight = motorRight;
        this.motorLeft = motorLeft;

    }
    @Override
    public void run(boolean on, Telemetry telemetry) {
        if (on){
            motorRight.setPower(speed);
            motorLeft.setPower(speed);

        } else{
            motorRight.setPower(0);
            motorLeft.setPower(0);

        }
        telemetry.addData("Current Position", motorLeft.getCurrentPosition());

    }
}
