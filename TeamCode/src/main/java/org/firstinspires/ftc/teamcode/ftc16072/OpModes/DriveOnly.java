package org.firstinspires.ftc.teamcode.ftc16072.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.ftc16072.Robot;

@TeleOp()
public class DriveOnly extends OpMode {
    Robot robot = new Robot();

    @Override
    public void init() {
        robot.makeDriveOnly();
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {

        robot.nav.fieldRelative(-gamepad1.left_stick_y,
                                 gamepad1.left_stick_x,
                                 gamepad1.right_stick_x);
        telemetry.addData("right", robot.rightPod.getEncoderDistance(DistanceUnit.INCH));
        telemetry.addData("left", robot.leftPod.getEncoderDistance(DistanceUnit.INCH));
        telemetry.addData("x", robot.middlePod.getEncoderDistance(DistanceUnit.INCH));
    }
}
