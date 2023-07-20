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
    }

    @Override
    public void loop() {
        if(gamepad1.a) {
            robot.lift.liftToPosition(Lift.Position.HIGHEST_POLE);


        } else if (gamepad1.right_stick_y < -0.1){

            robot.lift.manualLiftUp();

        } else if (gamepad1.right_stick_y > 0.1){
            robot.lift.manualLiftDown();

        }
        robot.lift.update(telemetry);
    }
}
