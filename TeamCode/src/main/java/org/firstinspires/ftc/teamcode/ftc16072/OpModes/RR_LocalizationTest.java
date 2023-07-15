package org.firstinspires.ftc.teamcode.ftc16072.OpModes;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.ftc16072.Robot;


@Autonomous
public class RR_LocalizationTest extends OpMode {
    Robot robot = new Robot();

    @Override
    public void init() {
        robot.makeDriveOnly();
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        robot.nav.updatePoseEstimate();
        Pose2d currentPose = robot.nav.getPoseEstimate();

        telemetry.addData("POSE","x = %.2f y = %.2f h = %.1f", currentPose.getX(),currentPose.getY(),Math.toDegrees(currentPose.getHeading()));

        robot.nav.setDrivePower(
                new Pose2d(
                        -gamepad1.left_stick_y,
                        -gamepad1.left_stick_x,
                        -gamepad1.right_stick_x
                )
        );
    }
}
