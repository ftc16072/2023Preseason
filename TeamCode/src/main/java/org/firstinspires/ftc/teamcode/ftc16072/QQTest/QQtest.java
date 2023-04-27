package org.firstinspires.ftc.teamcode.ftc16072.QQTest;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class QQtest {
    public final String name;

    QQtest(String name) {
        this.name = name;
    }
    public abstract void run(boolean on, Telemetry telemetry);



}
