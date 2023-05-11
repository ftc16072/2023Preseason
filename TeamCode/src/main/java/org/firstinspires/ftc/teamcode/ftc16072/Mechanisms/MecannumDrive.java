package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

public class MecannumDrive {
    DcMotor BackLeftMotor;
    DcMotor BackRightMotor;
    DcMotor FrontRightMotor;
    DcMotor FrontLeftMotor;
    BNO055IMU imu;

    public void init(HardwareMap HwMap){
        imu = HwMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters params = new BNO055IMU.Parameters(); // might want to eventually make a seperate gyro class
        params.calibrationDataFile = "BNO055IMUCalibration.json";
        imu.initialize(params);

        BackLeftMotor = HwMap.get(DcMotor.class, "back_left_motor");
        BackRightMotor = HwMap.get(DcMotor.class, "back_right_motor");
        FrontRightMotor = HwMap.get(DcMotor.class, "front_right_motor");
        FrontLeftMotor = HwMap.get(DcMotor.class, "front_left_motor");

        BackLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BackRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FrontRightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FrontLeftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        FrontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        BackLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

    }
    public void convert_to_field(double forward, double right, double rotate){

        double heading = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.RADIANS).firstAngle;
        double angle = right/forward;
        double field_forward = (Math.cos(angle)*forward);

        // do all the math then call drive function

    }
    public void drive(double frontLeftPower, double frontRightPower, double backLeftPower, double backRightPower){
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


    public void loop(){


    }
}
