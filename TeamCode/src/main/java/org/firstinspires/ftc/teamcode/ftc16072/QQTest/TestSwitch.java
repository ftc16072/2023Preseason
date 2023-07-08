package org.firstinspires.ftc.teamcode.ftc16072.QQTest;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TestSwitch extends QQtest{
    DigitalChannel button;

    public TestSwitch(String name, DigitalChannel button) {
        super(name);
        this.button = button;
    }

    @Override
    public void run(boolean on, Telemetry telemetry) {
        telemetry.addData(name, button.getState());
    }
}
