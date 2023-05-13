package org.firstinspires.ftc.teamcode.ftc16072;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.Gyro;
import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.MecanumDrive;
import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.Mechanism;

import java.util.Arrays;
import java.util.List;

public class Robot {
    List<Mechanism> mechanisms;
    MecanumDrive mecanumDrive;
    Gyro gyro;

    public Robot(){
        mecanumDrive = new MecanumDrive();
        gyro = new Gyro();

        mechanisms = Arrays.asList(
                mecanumDrive,
                gyro);
    }

    void init(HardwareMap hwMap){
        for (Mechanism mechanism : mechanisms) {
            mechanism.init(hwMap);
        }
    }

    public List<Mechanism> getMechanismList(){
        return mechanisms;
    }


}
