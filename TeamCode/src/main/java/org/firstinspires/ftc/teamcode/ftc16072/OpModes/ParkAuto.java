package org.firstinspires.ftc.teamcode.ftc16072.OpModes;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.ftc16072.Actions.DriveActionRR;
import org.firstinspires.ftc.teamcode.ftc16072.Actions.QQAction;
@Autonomous
public class ParkAuto extends BaseAuto{
    @Override
    QQAction setUpAction() {
        Pose2d startPose = new Pose2d(0,0,0);

        Trajectory trajectory = robot.nav.trajectoryBuilder(startPose,false)
                .splineTo(new Vector2d(30,30),0)
                .build();

        return new DriveActionRR(trajectory, "Drive to 30,30");
    }
}
