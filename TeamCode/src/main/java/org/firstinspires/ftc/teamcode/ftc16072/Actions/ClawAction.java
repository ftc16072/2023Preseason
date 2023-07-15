package org.firstinspires.ftc.teamcode.ftc16072.Actions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.ftc16072.Robot;

public class ClawAction extends QQAction {
    enum Claw{
        OPEN,
        CLOSED
    }
    Claw action;

    public ClawAction(Claw action){
        this.action = action;
    }
    @Override
    public QQAction run(OpMode opMode, Robot robot) {
        if(action == Claw.OPEN){
            robot.claw.manualOpen();
        }
        if(action == Claw.CLOSED){
            robot.claw.manualClose();
        }
        return nextAction;
    }

    @Override
    public String getDebugString() {
        return "Claw: " + action;
    }
}
