package org.firstinspires.ftc.teamcode.ftc16072;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.Claw;
import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.Gyro;
import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.HorizontalSlides;
import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.Lift;
import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.MecanumDrive;
import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.Mechanism;
import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.OdometryPod;
import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.Temp;
import org.firstinspires.ftc.teamcode.ftc16072.Util.Navigation;

import java.util.Arrays;
import java.util.List;

public class Robot {
    List<Mechanism> mechanisms;
    public MecanumDrive mecanumDrive;
    public HorizontalSlides horizontalSlides;
    Gyro gyro;
    public Claw claw;
    public Navigation nav;
    public Lift lift;
    public OdometryPod rightPod;
    public OdometryPod leftPod;
    public OdometryPod middlePod;
    public Temp temp;
    public Robot(){
        claw = new Claw();
        gyro = new Gyro();
        mecanumDrive = new MecanumDrive();
        lift = new Lift();
        horizontalSlides = new HorizontalSlides();
        rightPod = new OdometryPod("enc_right", false,0,-6, DistanceUnit.INCH);
        leftPod = new OdometryPod("enc_left",true,0,6, DistanceUnit.INCH);
        middlePod = new OdometryPod("enc_x", false,0,0, DistanceUnit.INCH, 90, AngleUnit.DEGREES);
        nav = new Navigation(gyro, mecanumDrive, rightPod, leftPod, middlePod);
        temp = new Temp();


        mechanisms = Arrays.asList(
                        mecanumDrive,
                        gyro,
                        lift,
                        horizontalSlides,
                        claw,
                        rightPod,
                        leftPod,
                        middlePod,
                        temp
        );
    }
    public void makeDriveOnly(){
        mechanisms = Arrays.asList(
                mecanumDrive,
                gyro,
                rightPod,
                leftPod,
                middlePod
        );
    }

    public void init(HardwareMap hwMap){
        for (Mechanism mechanism : mechanisms) {
            mechanism.init(hwMap);
        }
    }

    public List<Mechanism> getMechanismList(){
        return mechanisms;
    }


}
