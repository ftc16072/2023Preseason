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
import org.firstinspires.ftc.teamcode.ftc16072.Util.Polar;

import java.util.List;

public class MecanumDrive implements Mechanism{
    DcMotor BackLeftMotor;
    DcMotor BackRightMotor;
    DcMotor FrontRightMotor;
    DcMotor FrontLeftMotor;
    BNO055IMU imu;
    Polar drive;

    public void init(HardwareMap HwMap){
        imu = HwMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters params = new BNO055IMU.Parameters(); // might want to eventually make a seperate gyro class
        params.calibrationDataFile = "BNO055IMUCalibration.json";
        imu.initialize(params);

        BackLeftMotor = HwMap.get(DcMotor.class, "back_left_motor");
        BackRightMotor = HwMap.get(DcMotor.class, "back_right_motor");
        FrontRightMotor = HwMap.get(DcMotor.class, "front_right_motor");
        FrontLeftMotor = HwMap.get(DcMotor.class, "front_left_motor");



        FrontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        BackLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    @Override
    public List<QQtest> getTests() {
        return null;
    }

    @Override
    public String getName() {
        return "MecanumDrive";
    }

    void convert_to_field(double forward, double right, double rotate){
        Orientation angle;
        angle = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS);
        double heading = angle.firstAngle;


        Polar drive = new Polar(right, forward);
        drive.rotate(-heading, AngleUnit.RADIANS);
        move(drive.getY(),drive.getX(),rotate);




    }



    void move(double forward, double right, double rotate){


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

        FrontLeftMotor.setPower(frontLeftPower);
        FrontRightMotor.setPower(frontRightPower);
        BackLeftMotor.setPower(backLeftPower);
        BackRightMotor.setPower(backRightPower);



    }


}
