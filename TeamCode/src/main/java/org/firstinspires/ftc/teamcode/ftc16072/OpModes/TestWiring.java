package org.firstinspires.ftc.teamcode.ftc16072.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

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


    public void init(){
        robot.init(hardwareMap);
        mechanismList = robot.getMechanismList();

    }
    public void loop(){
        if (gamepad1.dpad_up){ //swap mechanisms
            // alternate 1 mechanism up unless you are already at the last one in which you move to the first one
            if (currentMechanism >= mechanismList.size()){
                currentMechanism = 0;
            }
            else {
                currentMechanism = currentMechanism + 1;

            }
            currentTest = 0;

            // update mechanism

        }
        else if (gamepad1.dpad_down){ // possible failure point
            if (currentMechanism <= 0){
                currentMechanism = mechanismList.size()-1;

            }
            else{
                currentMechanism = currentMechanism - 1;
            }
            currentTest = 0;
            // update mechanism
        }
        testList = mechanismList.get(currentMechanism).getTests();
        if (gamepad1.dpad_right){ // swap tests
            if (currentTest>=testList.size()){
                currentTest = 0;

            }
            else{
                currentTest = currentTest + 1;
            }
        }

        else if (gamepad1.dpad_left){
            if (currentTest<=0){
                currentTest = testList.size()-1;
            }
            else{
                currentTest = currentTest - 1;
            }


        }
        // run mechanism
        telemetry.addData("mechanism", mechanismList.get(currentMechanism));
        telemetry.addData("test", testList.get(currentTest));
        testList.get(currentTest).run(gamepad1.a, telemetry);
    }


}
