package org.firstinspires.ftc.teamcode.ftc16072.Util;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.Gyro;
import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.MecanumDrive;

public class Navigation {
    Gyro gyro;
    MecanumDrive mecanumDrive;

    public Navigation(Gyro gyro, MecanumDrive mecanumDrive){
        this.gyro = gyro;
        this.mecanumDrive = mecanumDrive;
    }

    public void fieldRelative(double forward, double right, double rotate){
        double heading = gyro.getHeading(AngleUnit.RADIANS);

        Polar drive = new Polar(right, forward);
        drive.rotate(-heading, AngleUnit.RADIANS);

        mecanumDrive.move(drive.getY(), drive.getX(), rotate);
    }
}
