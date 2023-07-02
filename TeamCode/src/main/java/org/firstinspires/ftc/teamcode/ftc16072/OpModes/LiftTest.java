package org.firstinspires.ftc.teamcode.ftc16072.OpModes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.Lift;
import org.firstinspires.ftc.teamcode.ftc16072.Robot;
@Config
@TeleOp
public class LiftTest extends OpMode {
    FtcDashboard dashboard;
    Robot robot = new Robot();



    @Override
    public void init() {
        robot.init(hardwareMap);
        dashboard = FtcDashboard.getInstance();
        telemetry = dashboard.getTelemetry();
        telemetry.addData("position",        robot.lift.currentPostion());

        telemetry.addData("Kp",  robot.lift.returnKp());
        telemetry.addData("Ki",  robot.lift.returnKi());
        telemetry.addData("Kd",  robot.lift.returnKd());
        telemetry.addData("Desired Position", robot.lift.returnDesiredPosition());


    }

    @Override
    public void loop() {
        if(gamepad1.a) {
            robot.lift.liftToPosition(Lift.liftPosition.POLE3);


        } else if (gamepad1.right_stick_y < -0.1){

            robot.lift.manualLiftUp();

        } else if (gamepad1.right_stick_y > 0.1){
            robot.lift.manualLiftDown();

        }



        telemetry.addData("position",        robot.lift.currentPostion());
        //robot.lift.updateLift();
        telemetry.addData("power",  robot.lift.updateLift());
        telemetry.addData("Kp",  robot.lift.returnKp());
        telemetry.addData("Ki",  robot.lift.returnKi());
        telemetry.addData("Kd",  robot.lift.returnKd());
        telemetry.addData("Desired Position", robot.lift.returnDesiredPosition());
        telemetry.addData("limit switch", robot.lift.limitSwitch.getState());
    }
}
