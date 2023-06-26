package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import android.icu.text.Transliterator;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.ftc16072.QQTest.QQtest;

import java.util.Hashtable;
import java.util.List;
@Config
public class Lift implements Mechanism {
    //FtcDashboard
    DcMotorEx rightLiftMotor;
    DcMotorEx leftLiftMotor;
    public static double Kp = 0.007; // Drives the the lift
    public static double Ki = 0.0001; // reduces Steady-state error
    public static double Kd = 0.0002; // eliminates noise
    public double integralSum=0;

    ElapsedTime timer = new ElapsedTime();
    public enum liftPosition {
        POLE3,
        POLE2,
        POLE1,
        GROUND_POSITION,
        INTAKE_POSITION,
    }
    public final double LIFT_POSITION_3=2000;
    public final double LIFT_POSITION_2 = 0;
    public final double LIFT_POSITION_1 = 0; // the numbers correspond to each height level of the poles
    public final double LIFT_POSITION_GROUND=0;
    public final double LIFT_POSITION_INTAKE=0;
    private final double GRAVITY_CONSTANT = 0.1;
    public static double desiredPosition;
    public static double power = 0;
    Hashtable<liftPosition, Double> positions = new Hashtable<>();
    private liftPosition presets;
    private double lastError=0;

    private void fillPositions(liftPosition position){ // adds the lift preset values to the dictionary
        positions.clear();
        positions.put(liftPosition.POLE3, LIFT_POSITION_3);
        positions.put(liftPosition.POLE2, LIFT_POSITION_2);
        positions.put(liftPosition.POLE1, LIFT_POSITION_1);
        positions.put(liftPosition.GROUND_POSITION, LIFT_POSITION_GROUND);
        positions.put(liftPosition.INTAKE_POSITION, LIFT_POSITION_INTAKE);
    }


    @Override
    public void init(HardwareMap hwMap) {
        rightLiftMotor = hwMap.get(DcMotorEx.class,"right_lift_motor");
        leftLiftMotor = hwMap.get(DcMotorEx.class, "left_lift_motor");
        rightLiftMotor.setDirection(DcMotorSimple.Direction.REVERSE); // this might need to be edited to left instead

        rightLiftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightLiftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftLiftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftLiftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        desiredPosition =  (rightLiftMotor.getCurrentPosition()+leftLiftMotor.getCurrentPosition())/2;
        fillPositions(presets);
    }

    @Override
    public List<QQtest> getTests() { // needs to be added
        return null;
    }

    @Override
    public String getName() {
        return "verticalLift";
    }
    public void manualLiftUp(){
        desiredPosition = (rightLiftMotor.getCurrentPosition()+leftLiftMotor.getCurrentPosition())/2+10;



    }
    public void manualLiftDown(){
        desiredPosition = (rightLiftMotor.getCurrentPosition()+leftLiftMotor.getCurrentPosition())/2-10;
    }

    public void liftToPosition(liftPosition position){
        desiredPosition = positions.get(position); // matches the position with the value in the dictionary and sets desiredPosition

    }
    public double updateLift(){

        double motorAverage = (rightLiftMotor.getCurrentPosition()+leftLiftMotor.getCurrentPosition())/2;
        power = PID(desiredPosition,motorAverage);
        /*
        Issues/ideas:
        add limit switch
        add negative power to fully close the lift, maybe could be incorperated with the limit switch

         */

        if (currentPostion() > 3000){ // lift cap for safety
            rightLiftMotor.setPower(0);
            leftLiftMotor.setPower(0);
        } else {
            rightLiftMotor.setPower(power);
            leftLiftMotor.setPower(power);
        }
        return (power);
    }
    public double PID(double destination, double position){ // does all of the PID math
        double error = destination-position;
        integralSum +=error * timer.seconds();
        double derivative = (error - lastError)/timer.seconds();
        lastError = error;

        timer.reset();
        if (desiredPosition > 100){
            return ((error*Kp) + (integralSum*Ki) + (derivative *Kd))+GRAVITY_CONSTANT;
        } else {
            return ((error*Kp) + (integralSum*Ki) + (derivative *Kd));
        }


    }





    // Methods used for PID tuning
    public double currentPostion(){
        return (rightLiftMotor.getCurrentPosition()+leftLiftMotor.getCurrentPosition())/2;
    }

    public double returnKp(){
        return Kp;
    }
    public double returnKi(){
        return Ki;
    }
    public double returnKd(){
        return Kd;
    }
    public double returnDesiredPosition(){
        return desiredPosition;
    }


}
