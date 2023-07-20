package org.firstinspires.ftc.teamcode.ftc16072.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.HorizontalSlides;
import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.Lift;
import org.firstinspires.ftc.teamcode.ftc16072.Robot;
import org.firstinspires.ftc.teamcode.ftc16072.Util.SafeChecker;

@TeleOp
public class ControlScheme2 extends OpMode {
    Robot robot = new Robot();
    SafeChecker sc = new SafeChecker(robot);

    @Override
    public void init(){
        robot.init(hardwareMap);
    }

    @Override
    public void loop(){
        //Drive and Rotate Field Relative
        robot.nav.fieldRelative(-gamepad1.left_stick_y,
                                gamepad1.left_stick_x,
                                gamepad1.right_stick_x);

            //Lift Preset Positions
                //Gamepad2
        if (gamepad2.a) {
            sc.goToVertical(Lift.Position.INTAKE_POSITION);
        } else if (gamepad2.b){
            sc.goToVertical(Lift.Position.GROUND_POSITION);

                //Gamepad1
        } else if (gamepad1.cross){
            sc.goToVertical(Lift.Position.LOWEST_POLE);
        } else if (gamepad1.circle){
            sc.goToVertical(Lift.Position.MIDDLE_POLE);
        } else if (gamepad1.triangle){
            sc.goToVertical(Lift.Position.HIGHEST_POLE);

            //Horizontal Manual Forward/Backward
        } else if (gamepad1.square){
            sc.moveHorizontalForward();
        } else if (gamepad1.dpad_right){
            sc.moveHorizontalBackwards();

            //Horizontal Preset Positions
        } else if (gamepad1.dpad_left){
            sc.gotoHorizontal(HorizontalSlides.Position.BACK);
        }else if (gamepad1.dpad_left){
            sc.gotoHorizontal(HorizontalSlides.Position.MIDDLE);
        }else if (gamepad1.dpad_up){
            sc.gotoHorizontal(HorizontalSlides.Position.FRONT);

            //Manual Vertical Lift Controls
        }else if (gamepad1.right_trigger>0.4){
            sc.moveVerticalUpwards();
        } else if (gamepad1.left_trigger>0.4){
            sc.moveVerticalDownwards();

            //Open/Close Claw Manually
        }else if(gamepad1.right_bumper){
            robot.claw.manualOpen();
        } else if(gamepad1.left_bumper){
            robot.claw.manualClose();
        } else if(!(gamepad1.right_bumper)){
            robot.claw.autograb();
        }
    }
}
