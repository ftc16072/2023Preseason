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
        robot.mecanumDrive.updatePoseEstimate();
        Pose2d currentPose = robot.mecanumDrive.getPoseEstimate();

        telemetry.addData("STATE",state);
        telemetry.addData("POSE","x = %.2f y = %.2f h = %.1f", currentPose.getX(),currentPose.getY(),Math.toDegrees(currentPose.getHeading()));
        switch(state){
            case BEGIN:
                state = State.AWAY;
                trajectory = new TrajectoryBuilder(new Pose2d(),false,robot.mecanumDrive.velocityConstraint,robot.mecanumDrive.accelConstraint)
                        .splineTo(new Vector2d(30,30),0)
                        .build();
                robot.mecanumDrive.follower.followTrajectory(trajectory);
                break;
            case AWAY:
                robot.mecanumDrive.setDriveSignal(robot.mecanumDrive.follower.update(currentPose));
                if (!robot.mecanumDrive.follower.isFollowing()){
                    state = State.PAUSE;
                    robot.mecanumDrive.setDriveSignal(new DriveSignal());
                    startPause = clock.seconds();
                }
                break;
            case PAUSE:
                if ((clock.seconds() - startPause) > 2.0){
                    state =State.RETURN;
                    trajectory = new TrajectoryBuilder(new Pose2d(30,30,0),true,robot.mecanumDrive.velocityConstraint,robot.mecanumDrive.accelConstraint)
                            .splineTo(new Vector2d(0,0),Math.toRadians(180))
                            .build();
                    robot.mecanumDrive.follower.followTrajectory(trajectory);
                }
                break;
            case RETURN:
                robot.mecanumDrive.setDriveSignal(robot.mecanumDrive.follower.update(currentPose));
                if(!robot.mecanumDrive.follower.isFollowing()){
                    state = State.DONE;
                    robot.mecanumDrive.setDriveSignal(new DriveSignal());
                }
                break;
            case DONE:
                break;
        }
    }
}
