package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.ftc16072.QQTest.QQtest;
import org.firstinspires.ftc.teamcode.ftc16072.QQTest.TestEncoder;

import java.util.Arrays;
import java.util.List;

public class OdometryPod implements Mechanism {
    DcMotor encoder;

    String name;
    int offset;

    static double WHEEL_RADIUS_CM = 4;
    static double WHEEL_CIRCUMFERENCE_CM = 2 * Math.PI * WHEEL_RADIUS_CM;
    static int TICKS_PER_ROTATION = 1150; //Not the real number

    public OdometryPod(String name, double x_pos, double y_pos, DistanceUnit du) {
        this.name = name;
    }
    @Override
    public void init(HardwareMap hwMap) {
        encoder = hwMap.get(DcMotor.class, name);
    }

    @Override
    public List<QQtest> getTests() {
        return Arrays.asList(
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

    public void reset() {
        offset = encoder.getCurrentPosition();


    }
}
