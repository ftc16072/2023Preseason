package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.ftc16072.QQTest.QQtest;
import org.firstinspires.ftc.teamcode.ftc16072.QQTest.TestEncoder;
import org.firstinspires.ftc.teamcode.ftc16072.Util.Encoder;

import java.util.Collections;
import java.util.List;

public class OdometryPod implements Mechanism {
    Encoder encoder;

    String name;
    int offset;
    double x_pos_in;
    double y_pos_in;
    double heading_radians;
    boolean flip;

    // Simulator Wheel Diameter is 2 inches, so radius is 1 inch, 2.54 gets to CM
    static double WHEEL_RADIUS_CM = 1 * 2.54;
    static double WHEEL_CIRCUMFERENCE_CM = 2 * Math.PI * WHEEL_RADIUS_CM;
    static int TICKS_PER_ROTATION = 1120; //From the simulator

    public OdometryPod(String name, double x_pos, double y_pos, DistanceUnit du) {
        this.name = name;
        this.x_pos_in = du.toInches(x_pos);
        this.y_pos_in = du.toInches(y_pos);
    }
    public OdometryPod(String name, boolean flip, double x_pos, double y_pos, DistanceUnit du) {
        this(name, x_pos, y_pos, du);
        this.flip = flip;
    }
    public OdometryPod(String name, boolean flip, double x_pos, double y_pos, DistanceUnit du, double heading, AngleUnit au){
        this(name, flip, x_pos,y_pos, du);
        this.heading_radians = au.toRadians(heading);
    }
    @Override
    public void init(HardwareMap hwMap) {
        encoder = new Encoder(hwMap.get(DcMotorEx.class, name));
        if(flip){
            encoder.setDirection(Encoder.Direction.REVERSE);
        }
    }

    @Override
    public List<QQtest> getTests() {
        return Collections.singletonList(
                new TestEncoder(name, encoder)
        );
    }

    @Override
    public String getName() {
        return name;
    }


    public double getEncoderDistance(DistanceUnit distanceUnit) {
        double distanceCM = (encoder.getCurrentPosition() - offset) * WHEEL_CIRCUMFERENCE_CM / TICKS_PER_ROTATION;
        return distanceUnit.fromCm(distanceCM);
    }
    public double getEncoderVelocity(DistanceUnit distanceUnit){
        double velocity = encoder.getCorrectedVelocity() * WHEEL_CIRCUMFERENCE_CM / TICKS_PER_ROTATION;
        return distanceUnit.fromCm(velocity);
    }

    public Pose2d getPose(){
        return new Pose2d(x_pos_in, y_pos_in, heading_radians);
    }

    public void reset() {
        offset = encoder.getCurrentPosition();
    }
}
