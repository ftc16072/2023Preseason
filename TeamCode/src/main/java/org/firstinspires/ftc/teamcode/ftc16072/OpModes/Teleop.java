package org.firstinspires.ftc.teamcode.ftc16072.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.HorizontalSlides;
import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.Lift;
import org.firstinspires.ftc.teamcode.ftc16072.Robot;
import org.firstinspires.ftc.teamcode.ftc16072.Util.SafeChecker;
@TeleOp
public class Teleop extends OpMode {
    Robot robot = new Robot();
    SafeChecker sc = new SafeChecker(robot);
    @Override
    public void init() {
        robot.init(hardwareMap);

    }

    @Override
    public void loop() {
        if (gamepad2.a){
            sc.goToVertical(Lift.liftPosition.INTAKE_POSITION);

        } else if (gamepad2.x){
            sc.goToVertical(Lift.liftPosition.POLE1);
        } else if(gamepad2.b){
            sc.goToVertical(Lift.liftPosition.POLE2);
        }else if (gamepad2.y){
            sc.goToVertical(Lift.liftPosition.POLE3);
        } else if (gamepad2.right_bumper){
            sc.goToVertical(Lift.liftPosition.GROUND_POSITION);
        } else if (gamepad2.right_stick_y < -0.1){ // check
            sc.moveVerticalUpwards();
        } else if (gamepad2.right_stick_y > 0.1) {
            sc.moveVerticalDownwards();
        }
        if (gamepad2.dpad_up){
            sc.gotoHorizontal(HorizontalSlides.Position.FRONT);
        } else if (gamepad2.dpad_left || gamepad2.dpad_right){
            sc.gotoHorizontal(HorizontalSlides.Position.MIDDLE);
        } else if (gamepad2.dpad_down){
            sc.gotoHorizontal(HorizontalSlides.Position.BACK);
        }

        if (gamepad2.left_stick_x>0){
            sc.moveHorizontalForward();
        } else {
            robot.horizontalSlides.stop();
        }
        if (gamepad2.left_stick_x<0){
            sc.moveHorizontalBackwards();
        } else {
            robot.horizontalSlides.stop();
        }
        if (gamepad2.right_trigger >0.4){
            robot.claw.manualOpen();
        } else if (gamepad2.left_trigger > 0.4){
            robot.claw.manualClose();
        }


        robot.nav.fieldRelative(-gamepad1.left_stick_y,
                gamepad1.left_stick_x,
                gamepad1.right_stick_x);

        robot.lift.updateLift(); // needed for pid
        telemetry.addData("horizontal", robot.horizontalSlides.isSafe());
        telemetry.addData("lift", robot.lift.isSafe());
        telemetry.addData("limit switch", robot.lift.limitSwitch.getState());

    }
}
