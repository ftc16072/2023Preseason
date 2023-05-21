package org.firstinspires.ftc.teamcode.ftc16072;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.Gyro;
import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.MecanumDrive;
import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.Mechanism;
import org.firstinspires.ftc.teamcode.ftc16072.Util.Navigation;

import java.util.Arrays;
import java.util.List;

public class Robot {
    List<Mechanism> mechanisms;
    public MecanumDrive mecanumDrive;
    Gyro gyro;
    public Navigation nav;

    public Robot(){
        gyro = new Gyro();
        mecanumDrive = new MecanumDrive();
        nav = new Navigation(gyro, mecanumDrive);

        mechanisms = Arrays.asList(
                        mecanumDrive,
                        gyro
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
