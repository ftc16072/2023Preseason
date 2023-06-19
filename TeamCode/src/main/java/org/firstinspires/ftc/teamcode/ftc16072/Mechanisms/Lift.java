package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.ftc16072.QQTest.QQtest;

import java.util.List;

public class Lift implements Mechanism {
    DcMotorEx rightLiftMotor;
    DcMotorEx leftLiftMotor;
    double Kp = 0;
    double Ki = 0;
    double Kd = 0;
    double integralSum=0;

    ElapsedTime timer = new ElapsedTime();

    public final double LIFT_POSITION_3=0;
    public final double LIFT_POSITION_2 = 0;
    public final double LIFT_POSITION_1 = 0; // the numbers correspond to each height level of the poles
    public final double LIFT_POSITION_GROUND=0;
    public final double LIFT_POSITION_INTAKE=0;

    private double lastError=0;


    @Override
    public void init(HardwareMap hwMap) {
        rightLiftMotor = hwMap.get(DcMotorEx.class,"right_lift");
        leftLiftMotor = hwMap.get(DcMotorEx.class, "left_lift");


        rightLiftMotor.setDirection(DcMotorSimple.Direction.REVERSE); // this might need to be edited to left instead




    }

    @Override
    public List<QQtest> getTests() {
        return null;
    }

    @Override
    public String getName() {
        return "verticalLift";
    }
    public void manualLiftUp(){

    }
    public void manualLiftDown(){

    }

    public void liftToPosition(){

    }
    public double PID(double destination, double position){
        double error = destination-position;
        integralSum +=error * timer.seconds();
        double derivative = (error - lastError)/timer.seconds();
        lastError = error;

        timer.reset();
        return ((error*Kp) + (integralSum*Ki) + (derivative *Kd));

    }
    public void update(){
        double power = PID(desiredPosition, leftLiftMotor.getCurrentPosition());
        rightLiftMotor.setPower(power);
        leftLiftMotor.setPower(power);
    }


    // add pid stuff

}
