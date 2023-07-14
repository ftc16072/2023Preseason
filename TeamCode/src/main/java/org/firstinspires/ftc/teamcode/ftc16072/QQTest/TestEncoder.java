package org.firstinspires.ftc.teamcode.ftc16072.QQTest;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TestEncoder extends QQtest  {
    DcMotor encoder;

    public TestEncoder(String name, DcMotor encoder){
        super(name);
        this.encoder = encoder;
    }

    @Override
    public void run(boolean on, Telemetry telemetry) {
        telemetry.addData("Encoder Value", encoder.getCurrentPosition());
    }
}
