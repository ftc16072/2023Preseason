package org.firstinspires.ftc.teamcode.ftc16072.QQTest;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.ftc16072.Util.Encoder;

public class TestEncoder extends QQtest  {
    Encoder encoder;

    public TestEncoder(String name, Encoder encoder){
        super(name);
        this.encoder = encoder;
    }

    @Override
    public void run(boolean on, Telemetry telemetry) {
        telemetry.addData("Encoder Value", encoder.getCurrentPosition());
    }
}
