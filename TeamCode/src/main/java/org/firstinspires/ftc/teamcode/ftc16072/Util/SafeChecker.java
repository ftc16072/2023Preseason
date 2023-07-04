package org.firstinspires.ftc.teamcode.ftc16072.Util;

import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.HorizontalSlides;
import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.Lift;
import org.firstinspires.ftc.teamcode.ftc16072.Robot;

public class SafeChecker {
    Robot robot;

    public SafeChecker(Robot robot){
        this.robot = robot;
    }
    public boolean moveHorizontalForward() {
        if (robot.lift.isSafe() || robot.horizontalSlides.isSafe()) {
            robot.horizontalSlides.manualForward();
            return true;
        }
        return false;
    }
    public boolean moveHorizontalBackwards() {
        if (robot.lift.isSafe() || robot.horizontalSlides.isSafe()) {
            robot.horizontalSlides.manualBackward();
            return true;
        }
        return false;
    }
    public boolean moveVerticalUpwards(){
        robot.lift.manualLiftUp();
        return true;

    }
    public boolean moveVerticalDownwards(){
        if(robot.horizontalSlides.isSafe()){
            robot.lift.manualLiftDown();
            return true;
        } else{
            if (robot.lift.isSafe()){
                robot.lift.manualLiftDown();
                return true;
            }
        return false;
        }
    }

        
    public void gotoHorizontal(HorizontalSlides.Position position){
        if (robot.lift.isSafe()){
            robot.horizontalSlides.goToPosition(position);
        } else if (position == HorizontalSlides.Position.FRONT){    
            robot.horizontalSlides.goToPosition(position);
        }


    }
    public void goToVertical(Lift.liftPosition position){
        if (robot.horizontalSlides.isSafe()){
            robot.lift.liftToPosition(position);

        } else if (position != Lift.liftPosition.INTAKE_POSITION){
            robot.lift.liftToPosition(position);

        }

    }
}
