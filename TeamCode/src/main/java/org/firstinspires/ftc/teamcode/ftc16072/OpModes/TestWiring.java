package org.firstinspires.ftc.teamcode.ftc16072.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.Mechanism;
import org.firstinspires.ftc.teamcode.ftc16072.QQTest.QQtest;
import org.firstinspires.ftc.teamcode.ftc16072.Robot;

import java.util.List;

@TeleOp
public class TestWiring extends OpMode {
    Robot robot = new Robot();
    List<Mechanism> mechanismList;
    int currentTest;
    int currentMechanism;
    List<QQtest> testList;

    public boolean wasUp;
    public boolean wasDown;
    public boolean wasLeft;
    public boolean wasRight;


    public void init() {
        robot.init(hardwareMap);
        mechanismList = robot.getMechanismList();

    }

    public void loop() {
        if (gamepad1.dpad_up && !wasUp) {
            currentMechanism -= 1;
            if (currentMechanism < 0){
                currentMechanism = mechanismList.size() - 1;
            }
            currentTest = 0;
        } else if (gamepad1.dpad_down && !wasDown) {
            currentMechanism += 1;
            if (currentMechanism >= mechanismList.size()) {
                currentMechanism = 0;
            }
            currentTest = 0;
        }
        testList = mechanismList.get(currentMechanism).getTests();
        if (gamepad1.dpad_right && !wasRight) {
            currentTest += 1;
            if (currentTest >= testList.size()){
                currentTest = 0;
            }
        } else if (gamepad1.dpad_left && !wasLeft) {
            currentTest -= 1;
            if (currentTest < 0){
                currentTest = testList.size()-1;
            }
        }
        wasUp = gamepad1.dpad_up;
        wasDown = gamepad1.dpad_down;
        wasRight = gamepad1.dpad_right;
        wasLeft = gamepad2.dpad_left;
        // run mechanism
        telemetry.addData("mechanism", mechanismList.get(currentMechanism).getName());
        telemetry.addData("test", testList.get(currentTest).name);
        testList.get(currentTest).run(gamepad1.a, telemetry);

    }
}
