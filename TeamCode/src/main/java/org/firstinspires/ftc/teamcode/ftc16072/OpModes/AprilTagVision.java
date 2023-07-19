package org.firstinspires.ftc.teamcode.ftc16072.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.List;

@TeleOp
public class AprilTagVision extends OpMode {

    private AprilTagProcessor aprilTag;

    private VisionPortal visionPortal;


    @Override
    public void init() {
        aprilTag = AprilTagProcessor.easyCreateWithDefaults();

        visionPortal = VisionPortal.easyCreateWithDefaults(
                hardwareMap.get(WebcamName.class, "Webcam Left"), aprilTag);

    }

    @Override
    public void loop() {

        telemetry.addLine("Hello");

        List<AprilTagDetection> currentDetections = aprilTag.getDetections();

        for(AprilTagDetection detection : currentDetections) {
            telemetry.addLine(String.format("\n==== (ID %d) Unknown", detection.id));
            telemetry.addLine(String.format("Center %6.0f %6.0f   (pixels)", detection.center.x, detection.center.y));
            telemetry.addData("id: ", detection.id);
        }



    }

}
