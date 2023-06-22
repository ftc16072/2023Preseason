package org.firstinspires.ftc.teamcode.ftc16072.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.HorizontalSlides;
import org.firstinspires.ftc.teamcode.ftc16072.Robot;

@TeleOp
public class horizontalSlidesTesting extends OpMode {
    Robot robot = new Robot();


    @Override
    public void init() {
        robot.init(hardwareMap);

    }

    @Override
    public void loop() {
        /*
        if (gamepad1.dpad_up){
            robot.horizontalSlides.goToPosition(HorizontalSlides.Position.BACK);

        }
        if (gamepad1.dpad_left){
            robot.horizontalSlides.goToPosition(HorizontalSlides.Position.FRONT);
        }


        if (gamepad1.dpad_down){
            robot.horizontalSlides.goToPosition(HorizontalSlides.Position.FRONT);

        }

         */

        if (gamepad2.dpad_down){
            robot.horizontalSlides.manualBackward();
        } else {
            robot.horizontalSlides.stop();
        }

        if (gamepad2.dpad_up){
            robot.horizontalSlides.manualForward();
        } else {
            robot.horizontalSlides.stop();
        }

    }
}
