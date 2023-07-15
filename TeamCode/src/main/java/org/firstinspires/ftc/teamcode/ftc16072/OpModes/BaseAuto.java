package org.firstinspires.ftc.teamcode.ftc16072.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.ftc16072.Actions.QQAction;
import org.firstinspires.ftc.teamcode.ftc16072.Robot;

abstract public class BaseAuto extends OpMode {

    Robot robot = new Robot();
    QQAction currentAction;

    @Override
    public void init() {
        robot.init(hardwareMap);
        currentAction = setUpAction();
    }

    @Override
    public void loop() {
        if(currentAction != null){
            telemetry.addData("action",currentAction.getDebugString());
            currentAction = currentAction.run(this, robot);
        }else {
            telemetry.addData("action", "finished");
        }
    }
    abstract QQAction setUpAction();
}
