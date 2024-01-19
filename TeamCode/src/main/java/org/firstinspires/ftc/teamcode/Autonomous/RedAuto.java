package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
@Autonomous
public class RedAuto extends LinearOpMode {

    private DcMotor fr, fl, br, bl; //fr is the wonky one
    private DcMotor LinearClaw;
    private Servo ClawL;
    private Servo ClawR;
    private Servo ClawWrist;
    private DistanceSensor DistanceSensor;

    @Override
    public void runOpMode() {
        fr = hardwareMap.get(DcMotor.class, "fr"); // The wonky One
        fl = hardwareMap.get(DcMotor.class, "fl");
        br = hardwareMap.get(DcMotor.class, "br");
        bl = hardwareMap.get(DcMotor.class, "bl");
        LinearClaw = hardwareMap.get(DcMotor.class, "LinearClaw");
        ClawR = hardwareMap.get(Servo.class, "ClawR");
        ClawL = hardwareMap.get(Servo.class, "ClawL");
        ClawWrist = hardwareMap.get(Servo.class,"ClawWrist");
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
            sleep(3000);
            fr.setPower(0.5);
            fl.setPower(0.5);
            br.setPower(0.5);
            bl.setPower(0.5);
            //Robot moves forward so side of robot is aligned with truss 2.
            sleep(1500);
            fr.setPower(-0.5);
            fl.setPower(0.5);
            br.setPower(-0.5);
            bl.setPower(0.5);
            //Robot turns to the left to face truss 2
            sleep(600);
            distance = DistanceSensor.getDistance(DistanceUnit.INCH);
            while (distance >= 0) {
                distance = DistanceSensor.getDistance(DistanceUnit.INCH);
                if (distance <= 100 && distance > 8) {
//Change number for > to a # so robot stops moving forward when it is on the edge/starting line of backstage.
                    fr.setPower(0.5);
                    fl.setPower(0.5);
                    br.setPower(0.5);
                    bl.setPower(0.5);
//Robot moves to the edge/starting line of backstage
                }
                distance = DistanceSensor.getDistance(DistanceUnit.INCH);
                if (distance <= 8 && distance >4) {
                    //Change # for > to the distance between the distance sensor and backdrop when robot is on the edge/starting line of backdrop.
                    fl.setPower(0.5);
                    fr.setPower(-0.5);
                    bl.setPower(-0.5);
                    br.setPower(0.5);
                }
                distance = DistanceSensor.getDistance(DistanceUnit.INCH);
                if(distance <= 4){ //Change # so that there is enough space for viper kit to extend
                    ClawWrist.setPosition(1); //Change #, but make sure that the claw wrist bends upwards and becomes somewhat vertical.
                    sleep(500);
                    fr.setPower(0.3);
                    fl.setPower(0.3);
                    br.setPower(0.3);
                    bl.setPower(0.3);
                    LinearClaw.setPower(0.5);
                    sleep(500);
                    ClawL.setPosition(1);
                    ClawR.setPosition(0);
                    sleep(500);
                    ClawWrist.setPosition(0); //Change #, but make sure that the claw wrist bends downwards and becomes 180 degree horizontal.
                    sleep(500);
                    fr.setPower(-0.3);
                    fl.setPower(-0.3);
                    br.setPower(-0.3);
                    bl.setPower(-0.3);
                    LinearClaw.setPower(-0.5);
                    sleep(500);
                    fr.setPower(0);
                    fl.setPower(0);
                    br.setPower(0);
                    bl.setPower(0);
                    telemetry.addData("Status", "Program Over, everything at 0");
                    telemetry.update();
                }
            }
        }
    }
}