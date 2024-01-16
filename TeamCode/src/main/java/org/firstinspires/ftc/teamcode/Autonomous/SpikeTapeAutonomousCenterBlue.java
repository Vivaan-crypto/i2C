package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
@Autonomous
public class SpikeTapeAutonomousCenterBlue extends LinearOpMode {

    private DcMotor fr, fl, br, bl; //fr is the wonky one
    private Servo Claw;
    private DistanceSensor DistanceSensor;

    @Override
    public void runOpMode() {
        fr = hardwareMap.get(DcMotor.class, "fr"); //Wonky One
        fl = hardwareMap.get(DcMotor.class, "fl");
        br = hardwareMap.get(DcMotor.class, "br");
        bl = hardwareMap.get(DcMotor.class, "bl");
        Claw = hardwareMap.get(Servo.class, "Claw");
        DistanceSensor = hardwareMap.get(DistanceSensor.class, "DistanceSensor");
        fr.setDirection(DcMotor.Direction.REVERSE);
        fl.setDirection(DcMotor.Direction.FORWARD);
        br.setDirection(DcMotor.Direction.REVERSE);
        bl.setDirection(DcMotor.Direction.REVERSE);
        fl.setPower(0);
        bl.setPower(0);
        fr.setPower(0);
        br.setPower(0);
        double distance = DistanceSensor.getDistance(DistanceUnit.INCH);
        telemetry.addData("Status", "Initialized & All systems are at 0");
        telemetry.update();
        waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("Status", "Program Starting");
            telemetry.update();
            sleep(3000);
            fr.setPower(1);
            fl.setPower(1);
            br.setPower(1);
            bl.setPower(1);
            //Move to Spike tape zone to place pixel
            sleep(600);//Change the time as needed
            fr.setPower(0.5);
            fl.setPower(0.5);
            br.setPower(0.5);
            bl.setPower(0.5);
            //Moves forward so Claw is above spike tape
            sleep(100);
            fr.setPower(0);
            fl.setPower(0);
            br.setPower(0);
            bl.setPower(0);
            Claw.setPosition(1); //Change # as needed
            //Drops pixel on spike tape
            sleep(500);
            fr.setPower(0.5);
            fl.setPower(0.5);
            br.setPower(0.5);
            bl.setPower(0.5);
            //Moves forward a little so side of robot is aligned with truss 2
            sleep(100);
            fr.setPower(1);
            fl.setPower(-1);
            br.setPower(1);
            bl.setPower(-1);
            //Turns left to face truss 2
            sleep(150);
            distance = DistanceSensor.getDistance(DistanceUnit.INCH);
            while (distance >= 0) {
                distance = DistanceSensor.getDistance(DistanceUnit.INCH);
                if (distance <= 100 && distance >= 6) {
                    fr.setPower(0.5);
                    fl.setPower(0.5);
                    br.setPower(0.5);
                    bl.setPower(0.5);
                }
                distance = DistanceSensor.getDistance(DistanceUnit.INCH);
                if (distance <= 5) {
                    fl.setPower(0);
                    fr.setPower(0);
                    bl.setPower(0);
                    br.setPower(0);
                    telemetry.addData("Status", "Program Over, everything at 0");
                    telemetry.update();
                }
            }
        }
    }
}