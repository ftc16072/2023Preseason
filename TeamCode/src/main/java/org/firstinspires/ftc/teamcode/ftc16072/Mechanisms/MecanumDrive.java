package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.ftc16072.QQTest.QQtest;
import org.firstinspires.ftc.teamcode.ftc16072.QQTest.TestMotor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MecanumDrive implements Mechanism {
    DcMotor backLeftMotor;
    DcMotor backRightMotor;
    DcMotor frontRightMotor;
    DcMotor frontLeftMotor;

    public static final double SECS_PER_MIN = 60.0;

    // depending on drive motor
    public static final double TICKS_PER_REV = 1120;
    public static final double MAX_RPM = 133.9;
    public static final double WHEEL_DIAM_IN = 4;
    public static final double TRACK_WIDTH_IN = 12;  // TODO: FOR SIMULATOR
    public static final double MAX_MOTOR_VELOCITY = MAX_RPM * Math.PI * WHEEL_DIAM_IN / SECS_PER_MIN;

    public static double MAX_VELOCITY = 30;
    public static double MAX_ACCELERATION = 60;
    public static double MAX_ANGULAR_VELOCITY = Math.PI;
    public static double MAX_ANGULAR_ACCELERATION = Math.PI;


    public void init(HardwareMap HwMap) {
        backLeftMotor = HwMap.get(DcMotor.class, "back_left_motor");
        backRightMotor = HwMap.get(DcMotor.class, "back_right_motor");
        frontRightMotor = HwMap.get(DcMotor.class, "front_right_motor");
        frontLeftMotor = HwMap.get(DcMotor.class, "front_left_motor");

        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        List<DcMotor> motors = Arrays.asList(backLeftMotor, backRightMotor, frontRightMotor, frontLeftMotor);
        for (DcMotor motor : motors) {
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
    }

    @Override
    public List<QQtest> getTests() {
        return Arrays.asList(
                new TestMotor("Back Left", 0.2, backLeftMotor),
                new TestMotor("Back Right", 0.2, backRightMotor),
                new TestMotor("Front Left", 0.2, frontLeftMotor),
                new TestMotor("Front Right", 0.2, frontRightMotor));
    }

    @Override
    public String getName() {
        return "MecanumDrive";
    }

    public void move(double forward, double right, double rotate) {
        double frontLeftPower = forward + right + rotate;
        double frontRightPower = forward - right - rotate;
        double backRightPower = forward + right - rotate;
        double backLeftPower = forward - right + rotate;

        setPowers(frontLeftPower, frontRightPower, backLeftPower, backRightPower);
    }

    public void setPowers(double frontLeftPower, double frontRightPower, double backLeftPower, double backRightPower) {
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


    private double ticksToInches(double ticks) {
        return WHEEL_DIAM_IN * Math.PI * ticks / TICKS_PER_REV;
    }

    public List<Double> getWheelPositions() {
        List<Double> wheelPositions = new ArrayList<>();
        wheelPositions.add(ticksToInches(frontLeftMotor.getCurrentPosition()));
        wheelPositions.add(ticksToInches(backLeftMotor.getCurrentPosition()));
        wheelPositions.add(ticksToInches(backRightMotor.getCurrentPosition()));
        wheelPositions.add(ticksToInches(frontRightMotor.getCurrentPosition()));
        return wheelPositions;
    }


}
