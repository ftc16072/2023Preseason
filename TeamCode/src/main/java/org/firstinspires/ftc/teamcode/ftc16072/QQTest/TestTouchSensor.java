package org.firstinspires.ftc.teamcode.ftc16072.QQTest;

import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TestTouchSensor extends QQtest{
    TouchSensor button;

    public TestTouchSensor(String name, TouchSensor button) {
        super(name);
        this.button = button;
    }

    @Override
    public void run(boolean on, Telemetry telemetry) {
        telemetry.addData(name, button.isPressed());
    }
}
