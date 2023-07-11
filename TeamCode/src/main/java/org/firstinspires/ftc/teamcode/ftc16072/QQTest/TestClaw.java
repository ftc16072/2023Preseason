package org.firstinspires.ftc.teamcode.ftc16072.QQTest;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TestClaw extends QQtest{
    Servo claw;
    double GRIPPED_SERVO_POSITION;
    double RELEASED_SERVO_POSITION;

    public TestClaw(String name){
        super(name);
    }

    @Override
    public void run(boolean on, Telemetry telemetry) {
        if(on){
            claw.setPosition(GRIPPED_SERVO_POSITION);
            claw.setPosition(RELEASED_SERVO_POSITION);
            }
        telemetry.addData("Claw Moved","");
        }


    }


