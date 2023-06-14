package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.ftc16072.QQTest.QQtest;

import java.util.List;

public class Claw implements Mechanism{
    @Override
    public void init(HardwareMap hwMap) {

    }

    @Override
    public List<QQtest> getTests() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    public void manualClose(){

    }
    public void manualOpen(){

    }
    public void Autograb(color){

    }

    /*

    Manual Close
    Manual Open
    Autograb
     - Checks color
     - checks range
     - after grab move lift

     */
}
