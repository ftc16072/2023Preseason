package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.ftc16072.QQTest.QQtest;

import java.util.List;

public abstract class Mechanism {
    public abstract void init(HardwareMap hwMap);

    public abstract List <QQtest> getTests();

    public String toString() {
        return this.getClass().getSimpleName();
    }







}
