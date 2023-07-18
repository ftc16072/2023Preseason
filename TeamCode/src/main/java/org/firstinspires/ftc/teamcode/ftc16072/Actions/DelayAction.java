package org.firstinspires.ftc.teamcode.ftc16072.Actions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.ftc16072.Robot;

public class DelayAction extends QQAction{
    double delayTill;
    double seconds;
    DelayAction(double seconds){
        this.seconds = seconds;
    }
    @Override
    QQAction run(OpMode opMode, Robot robot) {
        if(delayTill == 0){
            delayTill = opMode.time + seconds;
        }
        if(opMode.time >= delayTill){
            return nextAction;
        }
        return this;
    }
}
