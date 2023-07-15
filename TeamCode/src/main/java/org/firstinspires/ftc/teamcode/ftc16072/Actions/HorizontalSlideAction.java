package org.firstinspires.ftc.teamcode.ftc16072.Actions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.HorizontalSlides;
import org.firstinspires.ftc.teamcode.ftc16072.Robot;

public class HorizontalSlideAction extends QQAction {
    HorizontalSlides.Position position;
   HorizontalSlideAction(HorizontalSlides.Position position){
       this.position = position;
   }

    @Override
    QQAction run(OpMode opMode, Robot robot) {
        robot.horizontalSlides.goToPosition(position);
        return nextAction;
    }
}