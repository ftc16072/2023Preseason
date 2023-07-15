package org.firstinspires.ftc.teamcode.ftc16072.Actions;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.ftc16072.Robot;

public class DriveActionRR extends QQAction{
    boolean isFollowing;
    Trajectory trajectory;
    String description;

    public DriveActionRR(Trajectory trajectory, String description){
        this.trajectory = trajectory;
        this.description = description;
    }
    @Override
    public QQAction run(OpMode opMode, Robot robot) {
        if(!isFollowing){
            robot.nav.follower.followTrajectory(trajectory);
            isFollowing = true;
        }
        robot.nav.updatePoseEstimate();
        Pose2d currentPose = robot.nav.getPoseEstimate();
        opMode.telemetry.addData("pose",currentPose);

        if(robot.nav.isDoneFollowing(currentPose)){
            return nextAction;
        }
        return this;

    }

    @Override
    public String getDebugString() {
        return "drive action " + description;
    }
}
