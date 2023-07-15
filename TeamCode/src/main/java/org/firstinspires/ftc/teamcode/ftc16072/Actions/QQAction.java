package org.firstinspires.ftc.teamcode.ftc16072.Actions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.ftc16072.Robot;

public abstract class QQAction {
    QQAction nextAction;

    public abstract QQAction run(OpMode opMode, Robot robot);

    QQAction append(QQAction next){
        if(nextAction != null){
            return nextAction.append(next);
        }
        else{
            nextAction = next;
            return next;
        }
    }
    public abstract String getDebugString();

}
