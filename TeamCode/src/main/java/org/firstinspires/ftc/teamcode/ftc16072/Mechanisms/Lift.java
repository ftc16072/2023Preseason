package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

//import com.acmerobotics.dashboard.FtcDashboard;
//import com.acmerobotics.dashboard.config.Config; Use this for Dashboard
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.ftc16072.QQTest.QQtest;
import org.firstinspires.ftc.teamcode.ftc16072.QQTest.TestSwitch;
import org.firstinspires.ftc.teamcode.ftc16072.QQTest.TestTwoMotor;

import java.util.Arrays;
        import java.util.Hashtable;
import java.util.List;

@Config  //Use this for Dashboard
public class Lift implements Mechanism {
    DcMotorEx rightLiftMotor;
    DcMotorEx leftLiftMotor;
    public DigitalChannel limitSwitch;

    public static double K_P = 0.007; // Drives the the lift
    public static double K_I = 0.0001; // reduces Steady-state error
    public static double K_D = 0.0002; // eliminates noise
    public double integralSum;

    ElapsedTime timer = new ElapsedTime();
    public enum Position {
        HIGHEST_POLE,
        MIDDLE_POLE,
        LOWEST_POLE,
        GROUND_POSITION,
        INTAKE_POSITION,
    }
    //TODO: tune lift preset values
    public static int LIFT_POSITION_SAFETY = 3300;
    public static int LIFT_POSITION_HIGH_POLE = 2400;
    public static int LIFT_POSITION_MIDDLE_POLE = 2000;
    public static int LIFT_POSITION_LOWEST_POLE = 1800;
    public static int LIFT_POSITION_GROUND = 200;
    public static int LIFT_POSITION_REST = 70;
    public static int LIFT_POSITION_INTAKE = 0;
    public static int MANUAL_CHANGE = 100;
    private static double GRAVITY_CONSTANT = 0.1;

    final static double MAX_MOTOR_SPEED = 1.0;

    double desiredPosition;

    Hashtable<Position, Integer> positions = new Hashtable<>();
    private double lastError=0;

    private void fillPositions(){ // adds the lift preset values to the dictionary
        positions.clear();
        positions.put(Position.HIGHEST_POLE, LIFT_POSITION_HIGH_POLE);
        positions.put(Position.MIDDLE_POLE, LIFT_POSITION_MIDDLE_POLE);
        positions.put(Position.LOWEST_POLE, LIFT_POSITION_LOWEST_POLE);
        positions.put(Position.GROUND_POSITION, LIFT_POSITION_GROUND);
        positions.put(Position.INTAKE_POSITION, LIFT_POSITION_INTAKE);
    }


    @Override
    public void init(HardwareMap hwMap) {
        rightLiftMotor = hwMap.get(DcMotorEx.class,"right_lift_motor");
        leftLiftMotor = hwMap.get(DcMotorEx.class, "left_lift_motor");
        rightLiftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        rightLiftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightLiftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftLiftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftLiftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        limitSwitch = hwMap.get(DigitalChannel.class, "lift_switch");
        limitSwitch.setMode(DigitalChannel.Mode.INPUT);

        desiredPosition = LIFT_POSITION_INTAKE;
        fillPositions();
        timer.reset();
    }

    @Override
    public List<QQtest> getTests() { // needs to be added
        return Arrays.asList(
                new TestTwoMotor("lift up", leftLiftMotor, rightLiftMotor, 0.3),
                new TestTwoMotor("lift down", leftLiftMotor, rightLiftMotor, -0.2),
                new TestSwitch("limit switch", limitSwitch));
    }

    @Override
    public String getName() {
        return "verticalLift";
    }

    public void manualLiftUp(){
        desiredPosition = currentPosition() + MANUAL_CHANGE;
        if(desiredPosition > LIFT_POSITION_SAFETY){
            desiredPosition = LIFT_POSITION_SAFETY;
        }
    }
    public void manualLiftDown(){
        desiredPosition = currentPosition() - MANUAL_CHANGE;
        if(desiredPosition < LIFT_POSITION_INTAKE){
            desiredPosition = LIFT_POSITION_INTAKE;
        }
    }

    public void liftToPosition(Position position){
        desiredPosition = positions.get(position); // matches the position with the value in the dictionary and sets desiredPosition
    }

    public void update(Telemetry telemetry){
        double power;

        if (!limitSwitch.getState()){ // resets the encoder values
            rightLiftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightLiftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            leftLiftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftLiftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }

        power = PID(desiredPosition, currentPosition());
        if(desiredPosition == 0 && !(currentPosition() == 0)){
            power = -0.3;
        }

        if (currentPosition() > LIFT_POSITION_SAFETY){ // lift cap for safety
            rightLiftMotor.setPower(0);
            leftLiftMotor.setPower(0);
        } else {
            rightLiftMotor.setPower(power);
            leftLiftMotor.setPower(power);
        }
        telemetry.addData("currentPosition", currentPosition());
        telemetry.addData("desiredPosition", desiredPosition);
        telemetry.addData("power", power);
    }

    public double PID(double destination, double position){ // does all of the PID math
        double result;
        double error = destination-position;
        /*if(Math.signum(error) != Math.signum(integralSum)){
           integralSum = 0;
        }
        else{
           integralSum += error * timer.seconds();
        }



        if(integralSum * K_I > MAX_MOTOR_SPEED){
            integralSum = MAX_MOTOR_SPEED / K_I;
        }
        if(integralSum * K_I < -MAX_MOTOR_SPEED){
            integralSum = -MAX_MOTOR_SPEED / K_I;
        }


         */

        result = (error * K_P);
        if (desiredPosition > LIFT_POSITION_REST){
            return result + GRAVITY_CONSTANT;
        } else {
            return result;
        }
    }

    public boolean isSafeTelemetry(Telemetry telemetry){
        telemetry.addData("current position", currentPosition());
        telemetry.addData("compare", LIFT_POSITION_GROUND);
        return isSafe();
    }
    public boolean isSafe(){
        return (currentPosition() >= LIFT_POSITION_GROUND);
    }

    public double currentPosition(){
        return (rightLiftMotor.getCurrentPosition() + leftLiftMotor.getCurrentPosition())/2.0;
    }
}
