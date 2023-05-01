package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.ftc16072.QQTest.QQtest;
import org.firstinspires.ftc.teamcode.ftc16072.QQTest.TestMotor;

import java.util.Arrays;
import java.util.List;

public class Motor extends Mechanism {
    public DcMotor motor;


    public void init (HardwareMap hwMap){
        motor = hwMap.get(DcMotor.class, "test_motor");
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


    }


    public List<QQtest> getTests(){
        return Arrays.asList(

                new TestMotor("test_motor",50, motor)
        );
    }



}
