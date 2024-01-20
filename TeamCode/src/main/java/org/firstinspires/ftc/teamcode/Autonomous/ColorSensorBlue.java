package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import org.firstinspires.ftc.robotcore.external.Telemetry;


import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@Autonomous
//Where in the driver program this will be found ^
public class ColorSensorBlue extends LinearOpMode
{
    private DcMotor fr, fl, br, bl;
    private Servo ClawL;
    private Servo ClawR;
    private Servo ClawWrist;
    private DcMotor LinearClaw;
    private RevColorSensorV3 ColorSensorL;
    private RevColorSensorV3 ColorSensorR;
    private DistanceSensor DistanceSensor;
    @Override
    public void runOpMode()
    {

        fr = hardwareMap.get(DcMotor.class, "fl");
        //motor0
        fl = hardwareMap.get(DcMotor.class, "bl");
        //motor1
        br = hardwareMap.get(DcMotor.class, "fr");
        //motor2
        bl = hardwareMap.get(DcMotor.class, "br");
        //motor3
        ClawL = hardwareMap.get(Servo.class, "ClawL");
        ClawR = hardwareMap.get(Servo.class, "ClawR");
        ClawWrist = hardwareMap.get(Servo.class, "ClawWrist");
        ColorSensorL = hardwareMap.get(RevColorSensorV3.class,"ColorSensorL");
        ColorSensorR = hardwareMap.get(RevColorSensorV3.class,"ColorSensorR");
        fr.setDirection(DcMotor.Direction.REVERSE);//Motor with ISSUES!!! Ultu
        fl.setDirection(DcMotor.Direction.FORWARD);
        br.setDirection(DcMotor.Direction.REVERSE);
        bl.setDirection(DcMotor.Direction.REVERSE);
        double BlueL = ColorSensorL.blue();
        double RedL = ColorSensorL.red();
        double GreenL = ColorSensorL.green();
        double AlphaL = ColorSensorL.alpha();
        double BlueR = ColorSensorR.blue();
        double RedR = ColorSensorR.red();
        double GreenR = ColorSensorR.green();
        double AlphaR = ColorSensorR.alpha();
        double distance = DistanceSensor.getDistance(DistanceUnit.INCH);
        telemetry.addData("Status", "Initialized & All systems are at 0");
        telemetry.update();
        waitForStart();
        while(opModeIsActive()) {
            //1000 = 1 sec
            //IF YOU CHANGE SPEED THEN CHANGE TIME OR IT WILL OVER/UNDER TRAVEL
            //Change when mech team adds 425's(motors)
            //Do for blue side that if Blue is greater than red then run code and vice versa for red side
            //for example for left blue side put if(BlueL > RedL) then run the code.
              telemetry.addData("Status", "Program Starting");
              telemetry.update();
              sleep(3000);
            if(BlueL > RedL){
                fr.setPower(0.5);
                fl.setPower(0.5);
                br.setPower(0.5);
                bl.setPower(0.5);
                //Moves to spike tape zone
                sleep(1000); //Change # as needed
                fr.setPower(0.5);
                fl.setPower(-0.5);
                br.setPower(0.5);
                bl.setPower(-0.5);
                //Turns left to face left spike tape
                sleep(500);//Change # as needed
                fr.setPower(0.5);
                fl.setPower(0.5);
                br.setPower(0.5);
                bl.setPower(0.5);
                //Moves forward so claw is directly above spike tape
                sleep(100);//Change the # as needed
                ClawL.setPosition(1);
                ClawR.setPosition(0);
                //Change servo positions as needed but make sure claw opens
                //Pixel is dropped on spike tape
                sleep(250);//Change # as needed
                fr.setPower(-0.5);
                fl.setPower(-0.5);
                br.setPower(-0.5);
                bl.setPower(-0.5);
                //Moves backwards so robot doesn't hit pixels
                sleep(100);
                fr.setPower(-0.5);
                fl.setPower(0.5);
                br.setPower(0.5);
                bl.setPower(-0.5);
                //Strafes to the right so is alligned with Robot
                sleep(500);//Change # as needed
                DistanceSensor.getDistance(DistanceUnit.INCH);
                while (distance <= 0){
                    if(distance <=100 && distance >=8) { //Change #'s as needed
                        fr.setPower(0.5);
                        fl.setPower(0.5);
                        br.setPower(0.5);
                        bl.setPower(0.5);
                        //Robot moves to to inside backstage
                    }
                    if(distance <=7) { //Change # as needed
                        fr.setPower(0);
                        fl.setPower(0);
                        br.setPower(0);
                        bl.setPower(0);
                        telemetry.addData("Status", "Program Over, everything at 0");
                        telemetry.update();
                        sleep(25000);
                    }
                }
            }
            if (BlueR > RedR) {
                fr.setPower(0.5);
                fl.setPower(0.5);
                br.setPower(0.5);
                bl.setPower(0.5);
                //Move to Spike tape zone to place pixel
                sleep(1000);
                fr.setPower(-1);
                fl.setPower(1);
                br.setPower(-1);
                bl.setPower(1);
                //Turns right to face right spike tape
                sleep(500);//Change the time as needed
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
                ClawR.setPosition(1);
                ClawL.setPosition(0);
                //Change # as needed but make sure claw drops pixel
                //Drops pixel on spike tape
                sleep(250);
                fr.setPower(-0.5);
                fl.setPower(-0.5);
                br.setPower(-0.5);
                bl.setPower(-0.5);
                //Moves backwards so robot doesn't hit pixels
                sleep(100);
                fr.setPower(0.5);
                fl.setPower(-0.5);
                br.setPower(0.5);
                bl.setPower(-0.5);
                //Turns left 180 to face truss 1
                sleep(400);
                fr.setPower(-0.5);
                fl.setPower(0.5);
                br.setPower(0.5);
                bl.setPower(-0.5);
                //Strafes to the right to be in aligned with the second truss
                sleep(500);
                distance = DistanceSensor.getDistance(DistanceUnit.INCH);
                while (distance >= 0) {
                    distance = DistanceSensor.getDistance(DistanceUnit.INCH);
                    if (distance <= 100 && distance >= 8) {
                        fr.setPower(.5);
                        fl.setPower(.5);
                        br.setPower(.5);
                        bl.setPower(.5);
                    }
                    distance = DistanceSensor.getDistance(DistanceUnit.INCH);
                    if (distance <=7) {
                        fl.setPower(0);
                        fr.setPower(0);
                        bl.setPower(0);
                        br.setPower(0);
                        telemetry.addData("Status", "Program Over, everything at 0");
                        telemetry.update();
                        sleep(25000);
                    }
                }
            }
            if(BlueL < 0 && GreenL < 0 && RedL <0) {













            }
        }
    }
}