package org.firstinspires.ftc.teamcode.ftc16072.Actions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.Lift;
import org.firstinspires.ftc.teamcode.ftc16072.Robot;

public class VerticalSlidesAction extends QQAction {
    Lift.Position position;

    VerticalSlidesAction(Lift.Position position) {
        this.position = position;
    }

    @Override
    QQAction run(OpMode opMode, Robot robot) {
        robot.lift.liftToPosition(Lift.Position.INTAKE_POSITION);
        return nextAction;
    }

    @Override
    String getDebugString() {
        return "vertical desired position" + position;
    }
}
