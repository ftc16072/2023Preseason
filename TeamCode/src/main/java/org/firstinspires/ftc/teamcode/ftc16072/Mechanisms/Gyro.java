package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.ftc16072.QQTest.QQtest;
import org.firstinspires.ftc.teamcode.ftc16072.QQTest.TestGyro;

import java.util.Arrays;
import java.util.List;

public class Gyro implements Mechanism{
    private BNO055IMU imu;

    @Override
    public void init(HardwareMap hwMap) {
        imu = hwMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters params = new BNO055IMU.Parameters();
        params.calibrationDataFile = "BNO055IMUCalibration.json";
        imu.initialize(params);
    }

    @Override
    public List<QQtest> getTests() {
        return Arrays.asList(
        );
    }

    @Override
    public String getName() {
        return "gyro";
    }

    public double getHeading(AngleUnit angleUnit){
        Orientation angles;


        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS);

        return angleUnit.fromRadians(angles.firstAngle);
    }
}
