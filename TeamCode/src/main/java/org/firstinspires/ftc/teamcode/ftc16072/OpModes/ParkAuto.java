package org.firstinspires.ftc.teamcode.ftc16072.OpModes;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.ftc16072.Actions.DriveActionRR;
import org.firstinspires.ftc.teamcode.ftc16072.Actions.QQAction;
@Autonomous
public class ParkAuto extends BaseAuto{

    @Override
    public void init(){
        robot.makeDriveOnly();
        super.init();
    }

    QQAction driveToZoneOne(Pose2d startPose){
        return new DriveActionRR(
                robot.nav.trajectoryBuilder(startPose, false).
                        lineToConstantHeading(new Vector2d(-36, 38)).build(), "first move")
                .append(new DriveActionRR(
                        robot.nav.trajectoryBuilder(new Pose2d(-36, 38, startPose.getHeading()), false).
                                lineToConstantHeading(new Vector2d(-60, 38)).build(), "second move")
                );
    }

    @Override
    QQAction setUpAction() {
        Pose2d startPose = new Pose2d(-36,65,Math.toRadians(270)); //0,0 blue terminal on blue alliance side
        robot.nav.setPoseEstimate(startPose);

        return driveToZoneOne(startPose);
    }
}
