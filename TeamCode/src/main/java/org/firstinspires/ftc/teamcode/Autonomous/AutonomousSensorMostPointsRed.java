package org.firstinspires.ftc.teamcode.Autonomous;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import  com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
@Autonomous(name = "AutonomousSensorMostPointsRed")
public class AutonomousSensorMostPointsRed extends LinearOpMode {
    ColorSensor ColorSensor1;
    ColorSensor ColorSensor2;
    ColorSensor ColorSensor3;
    private DistanceSensor DistanceSensor;
    private DcMotor fl, bl, fr, br; //Br, Bl, and Fr move backwards
    private Servo Claw;// Left Servo
    Gamepad gamePad1;
    @Override
    public void runOpMode(){
        ColorSensor1 = hardwareMap.get(ColorSensor.class, "ColorSensor1");
        ColorSensor2 = hardwareMap.get(ColorSensor.class, "ColorSensor2");
        ColorSensor3 = hardwareMap.get(ColorSensor.class, "ColorSensor3");
        DistanceSensor = hardwareMap.get(DistanceSensor.class, "DistanceSensor");
        fl = hardwareMap.get(DcMotor.class, "fl");
        bl = hardwareMap.get(DcMotor.class, "bl");
        fr = hardwareMap.get(DcMotor.class, "fr");
        br = hardwareMap.get(DcMotor.class, "br");
        Claw = hardwareMap.get(Servo.class, "Claw");
        telemetry.addData("Status", "Initialized ");
        telemetry.update();
        fr.setDirection(DcMotorSimple.Direction.FORWARD);
        fl.setDirection(DcMotorSimple.Direction.FORWARD);
        br.setDirection(DcMotorSimple.Direction.FORWARD);
        bl.setDirection(DcMotorSimple.Direction.FORWARD);
        fl.setPower(0);
        bl.setPower(0);
        fr.setPower(0);
        br.setPower(0);
        double distance = DistanceSensor.getDistance(DistanceUnit.INCH);
        waitForStart();
        while(opModeIsActive()){
        /*For the autonomouses when close to the backboard(Auto 1 & 2) code the code to move to position
then sense the place where the team prop is and the place PURPLE pixel down there
and then drive to backboard and put yellow pixel over there.  Then for Auto 3 & 4  make robot put
    both pixels down on he line with team prop!*/
            sleep(3000);
            //Moves Forward
            fr.setPower(-0.5);
            fl.setPower(0.5);
            br.setPower(0.5);
            bl.setPower(0.5);
            sleep(1000);
            /*To make the sensor detect team prop, program it to sense on angles, for example make it first detect at the
             * left 30 degree, the in front 30 degree and then finally detect on the right at a 30 degree scope*/
            if(ColorSensor1.red() > 100) { /*Change from what it is right now to the Color sensor Detecting Team Prop on Right */
                //Turn right
                fl.setPower(0.5);
                fr.setPower(0.5);
                br.setPower(0.5);
                bl.setPower(-0.5);
                sleep(500);
                fl.setPower(0);
                fr.setPower(0);
                bl.setPower(0);
                br.setPower(0);
                //Make wrist horizonatal
                sleep(1000);
                //Move backward so claw is in line with spike line
                fr.setPower(0.5);
                fl.setPower(-0.5);
                br.setPower(-0.5);
                bl.setPower(-0.5);
                sleep(100);
                //drop purple pixel
                fl.setPower(0);
                fr.setPower(0);
                bl.setPower(0);
                br.setPower(0);
                Claw.setPosition(0);
                //Claw2.setPosition(1);
                sleep(500);
                //strafe to the right so in line with truss 1
                fr.setPower(.5);
                fl.setPower(.5);
                br.setPower(.5);
                bl.setPower(-.5);
                sleep(300);
                if(distance <= 100 && distance >= 8) {
                    //Moves to bakcboard
                    fr.setPower(-.5);
                    fl.setPower(.5);
                    br.setPower(.5);
                    bl.setPower(.5);
                }
                if(distance <= 7){
                /*If we build gates, add the code for that right here, so that the gates open and leave the yellow pixel in the backstage and then make it so
               that robot motors go to zero power and gate servo moves to open position for 0.5 seconds and then set only the MOTORS to zero power for 30 secs AFTER that*/
                    fl.setPower(0);
                    fr.setPower(0);
                    bl.setPower(0);
                    br.setPower(0);
                    sleep(30000);
                }
            }
            if(ColorSensor2.red() > 100){ /*Change from what it is right now to the Color sensor Detecting Team Prop on the Left  */
                //Turn Left
                fl.setPower(-0.5);
                fr.setPower(-0.5);
                br.setPower(-0.5);
                bl.setPower(0.5);
                sleep(500);
                fl.setPower(0);
                fr.setPower(0);
                bl.setPower(0);
                br.setPower(0);
//Make wrist horizonatal
                sleep(1000);
                //Moves backward so claw is in line with spike tape
                fr.setPower(0.5);
                fl.setPower(-0.5);
                br.setPower(-0.5);
                bl.setPower(-0.5);
                sleep(100);
                //Releases purple pixel
                fl.setPower(0);
                fr.setPower(0);
                bl.setPower(0);
                br.setPower(0);
                Claw.setPosition(0);
//Claw2.setPosition(1);
                sleep(500);
                //Turns 180 degrees to face truss 2
                fl.setPower(0.5);
                fr.setPower(0.5);
                br.setPower(0.5);
                bl.setPower(-0.5);
                sleep(1000);
                //strafe to the right so in line with truss 1
                fr.setPower(.5);
                fl.setPower(.5);
                br.setPower(.5);
                bl.setPower(-.5);
                sleep(300);
                if(distance <= 100 && distance >= 8) {
                    //Moves to bakcboard
                    fr.setPower(-.5);
                    fl.setPower(.5);
                    br.setPower(.5);
                    bl.setPower(.5);
                }
                if(distance <= 7){
                /*If we build gates, add the code for that right here, so that the gates open and leave the yellow pixel in the backstage and then make it so
               that robot motors go to zero power and gate servo moves to open position for 0.5 seconds and then set only the MOTORS to zero power for 30 secs AFTER that*/
                    fl.setPower(0);
                    fr.setPower(0);
                    bl.setPower(0);
                    br.setPower(0);
                    sleep(30000);
                }
            }
            if(ColorSensor3.red() > 100) {/*Change from what it is right now to the Color sensor Detecting Team Prop in front  */
                fl.setPower(0.5);
                fr.setPower(-0.5);
                br.setPower(-0.5);
                bl.setPower(-0.5);
                sleep(500);
                fl.setPower(0);
                fr.setPower(0);
                bl.setPower(0);
                br.setPower(0);
                //Make wrist horizontal
                sleep(1000);
                //Moves backward so claw is in line with spike tape
                fr.setPower(0.5);
                fl.setPower(-0.5);
                br.setPower(-0.5);
                bl.setPower(-0.5);
                sleep(100);
                //Releases purple pixel
                fl.setPower(0);
                fr.setPower(0);
                bl.setPower(0);
                br.setPower(0);
                Claw.setPosition(0);
                //   Claw2.setPosition(1);
                sleep(500);
                //Turns 90 degrees to face truss 2
                fl.setPower(0.5);
                fr.setPower(0.5);
                br.setPower(0.5);
                bl.setPower(-0.5);
                sleep(500);
                //strafes to the right so in line with truss 1
                fr.setPower(.5);
                fl.setPower(.5);
                br.setPower(.5);
                bl.setPower(-.5);
                sleep(500);
                if(distance <= 100 && distance >= 8) {
                    //Moves to backboard
                    fr.setPower(-.5);
                    fl.setPower(.5);
                    br.setPower(.5);
                    bl.setPower(.5);
                }
                if(distance <= 7){
                /*If we build gates, add the code for that right here, so that the gates open and leave the yellow pixel in the backstage and then make it so
               that robot motors go to zero power and gate servo moves to open position for 0.5 seconds and then set only the MOTORS to zero power for 30 secs AFTER that*/
                    fl.setPower(0);
                    fr.setPower(0);
                    bl.setPower(0);
                    br.setPower(0);
                    sleep(30000);
                }
        }
            else{
                fl.setPower(0);
                fr.setPower(0);
                bl.setPower(0);
                br.setPower(0);
                Claw.setPosition(0);
            }
            telemetry.addData("Red: ",ColorSensor1.red());
            telemetry.addData("Blue: ",ColorSensor1.blue());
            telemetry.addData("Green: ",ColorSensor1.green());
            telemetry.addData("Red: ",ColorSensor2.red());
            telemetry.addData("Blue: ",ColorSensor2.blue());
            telemetry.addData("Green: ",ColorSensor2.green());
            telemetry.addData("Red: ",ColorSensor3.red());
            telemetry.addData("Blue: ",ColorSensor3.blue());
            telemetry.addData("Green: ",ColorSensor3.green());
            telemetry.addData("Statues", "Running");
            telemetry.update();
    }
 }
}