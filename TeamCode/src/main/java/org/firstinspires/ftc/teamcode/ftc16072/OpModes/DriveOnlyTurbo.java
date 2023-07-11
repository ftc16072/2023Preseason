package org.firstinspires.ftc.teamcode.ftc16072.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


import org.firstinspires.ftc.teamcode.ftc16072.Actions.DelayAction;
import org.firstinspires.ftc.teamcode.ftc16072.Actions.QQAction;
import org.firstinspires.ftc.teamcode.ftc16072.Robot;


@TeleOp()
public class DriveOnlyTurbo extends OpMode {
    private enum Turbo{
        USED,
        UNUSED

    }
    private Turbo turbo;

    public void setTurbo(Turbo turbo) {
        this.turbo = Turbo.UNUSED;
    }

    double timer = 0;
    Robot robot = new Robot();

    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {

        if (gamepad1.right_bumper) {
            while ((timer < 3)&&(turbo.equals(Turbo.UNUSED))) {
                robot.mecanumDrive.setPowers(.5, .5, .5, .5);
                robot.nav.fieldRelative(
                        -gamepad1.left_stick_y,
                        gamepad1.left_stick_x,
                        gamepad1.right_stick_x);
                timer = timer + 0.000001;
                new DelayAction(0.000001);
                turbo = Turbo.USED;


            }
            robot.nav.fieldRelative(-gamepad1.left_stick_y,
                    gamepad1.left_stick_x,
                    gamepad1.right_stick_x);
        }
    }}
