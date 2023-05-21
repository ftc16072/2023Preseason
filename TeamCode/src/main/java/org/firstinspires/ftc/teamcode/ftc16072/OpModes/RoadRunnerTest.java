package org.firstinspires.ftc.teamcode.ftc16072.OpModes;

import com.acmerobotics.roadrunner.drive.DriveSignal;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import com.acmerobotics.roadrunner.util.NanoClock;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.ftc16072.Robot;


@Autonomous
public class RoadRunnerTest extends OpMode {

    Robot robot = new Robot();

    private enum State {BEGIN, AWAY, PAUSE, RETURN, DONE}
    State state = State.BEGIN;

    NanoClock clock;
    double startPause;

    Trajectory trajectory;

    @Override
    public void init() {
        robot.init(hardwareMap);
        clock = NanoClock.system();
    }

    @Override
    public void loop() {
        robot.nav.updatePoseEstimate();
        Pose2d currentPose = robot.nav.getPoseEstimate();

        telemetry.addData("STATE",state);
        telemetry.addData("POSE","x = %.2f y = %.2f h = %.1f", currentPose.getX(),currentPose.getY(),Math.toDegrees(currentPose.getHeading()));
        switch(state){
            case BEGIN:
                state = State.AWAY;
                trajectory = robot.nav.trajectoryBuilder(currentPose,false)
                        .splineTo(new Vector2d(30,30),0)
                        .build();
                robot.nav.follower.followTrajectory(trajectory);
                break;
            case AWAY:
                if (robot.nav.isDoneFollowing(currentPose)){
                    state = State.PAUSE;
                    startPause = clock.seconds();
                }
                break;
            case PAUSE:
                if ((clock.seconds() - startPause) > 2.0){
                    state =State.RETURN;
                    trajectory = robot.nav.trajectoryBuilder(currentPose,true)
                            .splineTo(new Vector2d(0,0),Math.toRadians(180))
                            .build();
                    robot.nav.follower.followTrajectory(trajectory);
                }
                break;
            case RETURN:
                if(robot.nav.isDoneFollowing(currentPose)){
                    state = State.DONE;
                }
                break;
            case DONE:
                break;
        }
    }
}
