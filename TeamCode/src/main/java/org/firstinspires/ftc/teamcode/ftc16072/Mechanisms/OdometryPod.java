package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.ftc16072.QQTest.QQtest;
import org.firstinspires.ftc.teamcode.ftc16072.QQTest.TestEncoder;

import java.util.Collections;
import java.util.List;

public class OdometryPod implements Mechanism {
    DcMotor encoder;

    String name;
    int offset;
    double x_pos_in;
    double y_pos_in;
    boolean flip;

    // Simulator Wheel Diameter is 2 inches, so radius is 1 inch, 2.54 gets to CM
    static double WHEEL_RADIUS_CM = 1 * 2.54;
    static double WHEEL_CIRCUMFERENCE_CM = 2 * Math.PI * WHEEL_RADIUS_CM;
    static int TICKS_PER_ROTATION = 1120; //From the simulator

    public OdometryPod(String name, boolean flip, double x_pos, double y_pos, DistanceUnit du) {
        this.name = name;
        this.flip = flip;
        this.x_pos_in = du.toInches(x_pos);
        this.y_pos_in = du.toInches(y_pos);
    }
    @Override
    public void init(HardwareMap hwMap) {
        encoder = hwMap.get(DcMotor.class, name);
        if(flip){
            encoder.setDirection(DcMotorSimple.Direction.REVERSE);
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

    public void reset() {
        offset = encoder.getCurrentPosition();


    }
}
