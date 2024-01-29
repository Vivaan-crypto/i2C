package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp

public class MainTeleOpV2 extends LinearOpMode {
/*Use the code for the Gates if team has time and decides to build the Gates to store pixels
 if not then comment out the code, DON'T DELETE! and set the drone launch to X and Y,
  Y = recoils rubber band & X = Shoot Drone!*/
/*Use the codes for Hanging if team has time and is able to build the mechanism. Mechanism: Put a viper
kit behind the elbow on the side which doesn't have the expansion hub, or if possible then on the
center-plate which is in front of the control hub, attach claw to viper with inside facing downward*/
//Claw for hanging will basically a cylinder cut in half and inside hollow, and then place it latterally on viper kit


    private Servo DroneLaunchServo;
    private Servo DroneLaunchPlatformServo;
    private Servo ClawL;
    private Servo ClawR;
    private Servo ClawWrist;
    private DcMotorEx LinearClaw;
    private ElapsedTime     runtime = new ElapsedTime();
    private Forward drive;

    @Override
    public void runOpMode() {
        DroneLaunchServo.setPosition(1);
        drive = new Forward(hardwareMap, gamepad1, telemetry);
        DroneLaunchPlatformServo = hardwareMap.get(Servo.class, "DroneLaunchPlatformServo");
        DroneLaunchServo = hardwareMap.get(Servo.class, "DroneLaunchServo");
        ClawR = hardwareMap.get(Servo.class, "ClawR");
        ClawL = hardwareMap.get(Servo.class, "ClawL");
        ClawWrist = hardwareMap.get(Servo.class, "ClawWrist");
        LinearClaw = hardwareMap.get(DcMotorEx.class, "LinearClaw"); //Configure in Expansion Hub

        waitForStart();
        while (opModeIsActive()) {
            DroneLaunchServo.setPosition(1);
            drive.loop();
            ControlLoops();

        }
    }
    public void ControlLoops() {
        DroneLaunchServo.setPosition(1);
        //IMPORTANT NOTE:
        /*For hanging use the viper kit, so you don't need an extra motor nor it's code.
         * So put 2 good claws on one of the two bottom stages of the viper kit(Choose stage based upon how high viper kit can go),
         * make sure you put the claws on the same stage!!!. For the claws just some good sturdy hooks.
         * So attach a rod/metal beam to each hook somehow and somewhere(preferably near the bottom)
         * and attach the other side of the rod/metal beam to the viper kit stage*/
        if (gamepad1.dpad_down) {
            DroneLaunchServo.setPosition(1); /* Set servo to 180 degrees position, which primes launcher for drone launch. Change the # as needed*/
        }
        if (gamepad1.dpad_right) {
            resetRuntime();
            //Figure out a way to do double click on the dpad_up to launch drone because the code below might not work!!!
            //But still test the code below before actually changing it!!!
            if (gamepad1.dpad_up && runtime.seconds() <= 3.5)  {
             if(gamepad1.dpad_up && runtime.seconds() <= 3.5) {
                 DroneLaunchPlatformServo.setPosition(1);
                 //Sets drone launch platform to shooting position / shooting angle.
                 DroneLaunchServo.setPosition(0);
                 // Launches the drone, and sets it to initial position. Change the # as needed
             }
            } else {
                DroneLaunchPlatformServo.setPosition(0.5);
                DroneLaunchServo.setPosition(1);
            }
        }
        if (gamepad1.a) {
            ClawR.setPosition(0.6);
            ClawL.setPosition(0.1);
            //Holds the pixel, change the #'s as needed
        }
        if (gamepad1.b) {
            ClawR.setPosition(0.4);
            ClawL.setPosition(0.35);
            //Releases the pixel, change the #'s as needed
        }
        if (gamepad1.right_stick_y == 0) {
            LinearClaw.setPower(0.1);
        } else {
            LinearClaw.setPower(gamepad1.right_stick_y * 0.8);
        }
        if (gamepad1.x) {
            DroneLaunchPlatformServo.setPosition(1);
            //Sets drone launch platform to shooting position / shooting angle.
            //Change # as needed
        }
        if (gamepad1.y) {
            DroneLaunchPlatformServo.setPosition(0.5);
            //Sets drone launch platform to resting position / 0 degree angle.
            //Change # as needed
        }
        if (gamepad1.right_trigger >= 0.6) {
            ClawWrist.setPosition(0.7);
            //Change the # as needed but make sure that the claw wrist turns downwards when left trigger pressed
        }
        if (gamepad1.left_trigger >= 0.6) {
            ClawWrist.setPosition(0);
            //Change the # as needed but make sure that the claw wrist turns upwards when right trigger pressed
        }
    }
}

