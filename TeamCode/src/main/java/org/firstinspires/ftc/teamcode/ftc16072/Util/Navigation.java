package org.firstinspires.ftc.teamcode.ftc16072.Util;

import androidx.annotation.NonNull;

import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.control.PIDFController;
import com.acmerobotics.roadrunner.drive.DriveSignal;
import com.acmerobotics.roadrunner.followers.HolonomicPIDVAFollower;
import com.acmerobotics.roadrunner.followers.TrajectoryFollower;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import com.acmerobotics.roadrunner.trajectory.constraints.AngularVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.MinVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.ProfileAccelerationConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryAccelerationConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TranslationalVelocityConstraint;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.Gyro;
import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.MecanumDrive;

import java.util.Arrays;
import java.util.List;

public class Navigation extends com.acmerobotics.roadrunner.drive.MecanumDrive{
    Gyro gyro;
    MecanumDrive mecanumDrive;

    public static PIDCoefficients TRANSLATIONAL_PID = new PIDCoefficients(1, 0, 0);
    public static PIDCoefficients HEADING_PID = new PIDCoefficients(1,0,0);
    public static double kV = 1.0 / MecanumDrive.MAX_MOTOR_VELOCITY;
    public static double kA = 0.0;
    public static double kStatic = 0;

    public TrajectoryVelocityConstraint velocityConstraint = new MinVelocityConstraint(Arrays.asList(
            new AngularVelocityConstraint(MecanumDrive.MAX_ANGULAR_VELOCITY),
            new TranslationalVelocityConstraint(MecanumDrive.MAX_VELOCITY)
    ));
    public TrajectoryAccelerationConstraint accelConstraint = new ProfileAccelerationConstraint(MecanumDrive.MAX_ACCELERATION);
    private PIDFController turnController;
    public TrajectoryFollower follower;


    public Navigation(Gyro gyro, MecanumDrive mecanumDrive){
        super(kV,kA,kStatic,mecanumDrive.TRACK_WIDTH_IN);
        this.gyro = gyro;
        this.mecanumDrive = mecanumDrive;

        turnController = new PIDFController(HEADING_PID);
        turnController.setInputBounds(0,2 * Math.PI);
        follower = new HolonomicPIDVAFollower(TRANSLATIONAL_PID, TRANSLATIONAL_PID,HEADING_PID,
                new Pose2d(0.5,0.5,Math.toRadians(5.0)),0.5);

    }

    public void fieldRelative(double forward, double right, double rotate){
        double heading = gyro.getHeading(AngleUnit.RADIANS);

        Polar drive = new Polar(right, forward);
        drive.rotate(-heading, AngleUnit.RADIANS);

        mecanumDrive.move(drive.getY(), drive.getX(), rotate);
    }

    @Override
    protected double getRawExternalHeading() {
        return gyro.getHeading(AngleUnit.RADIANS);
    }


    @NonNull
    @Override
    public List<Double> getWheelPositions() {
        return mecanumDrive.getWheelPositions();
    }

    @Override
    public void setMotorPowers(double v, double v1, double v2, double v3) {
        mecanumDrive.setPowers(v, v3, v1, v2);
    }

    public TrajectoryBuilder trajectoryBuilder(Pose2d startPose, Boolean reversed){
        return new TrajectoryBuilder(startPose, reversed, velocityConstraint, accelConstraint);
    }
    public boolean isDoneFollowing(Pose2d currentPose){
        setDriveSignal(follower.update(currentPose));
        if(!follower.isFollowing()){
            setDriveSignal(new DriveSignal());
            return true;
        }
        return false;
    }

}
