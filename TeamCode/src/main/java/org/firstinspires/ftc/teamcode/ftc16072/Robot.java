package org.firstinspires.ftc.teamcode.ftc16072;

import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.Mechanism;
import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.Motor;

import java.util.Arrays;
import java.util.List;

public class Robot {
    public Motor motor = new Motor();

    List<Mechanism> Mechanisms = Arrays.asList(
            motor


    );

    public List getMechanismList(){
        return (Mechanisms);
    }


}
