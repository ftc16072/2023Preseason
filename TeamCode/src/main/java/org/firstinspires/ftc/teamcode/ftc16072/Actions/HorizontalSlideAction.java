package org.firstinspires.ftc.teamcode.ftc16072.Actions;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.HorizontalSlides;
import org.firstinspires.ftc.teamcode.ftc16072.Robot;

public class HorizontalSlideAction extends QQAction {
    HorizontalSlides.Position position;
   public HorizontalSlideAction(HorizontalSlides.Position position){
       this.position = position;
   }

    @Override
    public QQAction run(OpMode opMode, Robot robot) {
        robot.horizontalSlides.goToPosition(position);
        return nextAction;
    }

    @Override
    public String getDebugString() {
        return "Horizontal position:" + position;
    }
}