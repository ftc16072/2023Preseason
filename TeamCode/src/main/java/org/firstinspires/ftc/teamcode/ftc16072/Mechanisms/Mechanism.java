package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.ftc16072.QQTest.QQtest;

import java.util.List;

public interface Mechanism {
    void init(HardwareMap hwMap);
    List <QQtest> getTests();
    String getName();
}
