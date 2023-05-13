package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.ftc16072.QQTest.QQtest;
import org.firstinspires.ftc.teamcode.ftc16072.QQTest.TestMotor;
import org.firstinspires.ftc.teamcode.ftc16072.Util.Polar;

import java.util.Arrays;
import java.util.List;

public class MecanumDrive implements Mechanism{
    DcMotor backLeftMotor;
    DcMotor backRightMotor;
    DcMotor frontRightMotor;
    DcMotor frontLeftMotor;

    public void init(HardwareMap HwMap){
        backLeftMotor = HwMap.get(DcMotor.class, "back_left_motor");
        backRightMotor = HwMap.get(DcMotor.class, "back_right_motor");
        frontRightMotor = HwMap.get(DcMotor.class, "front_right_motor");
        frontLeftMotor = HwMap.get(DcMotor.class, "front_left_motor");

        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public List<QQtest> getTests() {
        return Arrays.asList(new TestMotor("Back Left", 0.2, backLeftMotor),
                             new TestMotor("Back Right", 0.2, backRightMotor),
                             new TestMotor("Front Left", 0.2, frontLeftMotor),
                             new TestMotor("Front Right", 0.2, frontRightMotor));
    }

    @Override
    public String getName() {
        return "MecanumDrive";
    }

    public void move(double forward, double right, double rotate){
        double frontLeftPower = forward + right + rotate;
        double frontRightPower = forward - right - rotate;
        double backRightPower = forward + right - rotate;
        double backLeftPower = forward - right + rotate;

        double maxSpeed = 1.0;

        maxSpeed = Math.max(maxSpeed, Math.abs(frontLeftPower));
        maxSpeed = Math.max(maxSpeed, Math.abs(frontRightPower));
        maxSpeed = Math.max(maxSpeed, Math.abs(backLeftPower));
        maxSpeed = Math.max(maxSpeed, Math.abs(backRightPower));

        frontLeftPower /= maxSpeed;
        frontRightPower /= maxSpeed;
        backLeftPower /= maxSpeed;
        backRightPower /= maxSpeed;

        frontLeftMotor.setPower(frontLeftPower);
        frontRightMotor.setPower(frontRightPower);
        backLeftMotor.setPower(backLeftPower);
        backRightMotor.setPower(backRightPower);
    }


}
