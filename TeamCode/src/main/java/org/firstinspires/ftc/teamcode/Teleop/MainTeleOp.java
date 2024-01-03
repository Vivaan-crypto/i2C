package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp

public class MainTeleOp extends LinearOpMode {
/*Use the code for the Gates if team has time and decides to build the Gates to store pixels
 if not then comment out the code, DON'T DELETE! and set the drone launch to X and Y,
  Y = recoils rubber band & X = Shoot Drone!*/

  private Servo Gate1;

  private Servo Gate2;
    private Servo DroneLaunchServo;
    private Servo Claw;
    private DcMotorEx LinearClaw;

// private
    private Forward drive;

    @Override
    public void runOpMode() {
        drive = new Forward(hardwareMap, gamepad1, telemetry);
        Gate1 = hardwareMap.get(Servo.class, "Gate1");
        Gate2 = hardwareMap.get(Servo.class, "Gate2");
        DroneLaunchServo = hardwareMap.get(Servo.class, "DroneLaunchServo");
        Claw = hardwareMap.get(Servo.class, "Claw");
        LinearClaw = hardwareMap.get(DcMotorEx.class, "LinearClaw"); //Configure in Expansion Hub
        waitForStart();
        while (opModeIsActive()) {

            drive.loop();
            ControllLoops();
        }

    }
    // If the if loops start glitchin then change remove the left_trigger and right_trigger if loops, and keep everything else the same. After that for the hanger if loops ONLY put them so that the LEFT trigger and the button/joystick have to be pressed/moved TOGETHER for the hanger if loops to work: For example: if(gameapd1.left_trigger & gamepad1.a) {...}
    public void ControllLoops() {
        if (gamepad1.dpad_down) {
            DroneLaunchServo.setPosition(1); /* Set servo to 180 degrees position, which primes launcher for drone launch. Change the # as needed*/
        }
        if (gamepad1.dpad_up) {
            DroneLaunchServo.setPosition(0);
            // Launches the drone, and sets it to initial position. Change the # as needed
        }
        if (gamepad1.a) {
            Claw.setPosition(1);//Grabs the pixel, change the # as needed-Left
        }
        if (gamepad1.b) {
            Claw.setPosition(0);//Releases the pixel, change the # as needed-Left
        }
        if (gamepad1.right_stick_y > 0.4 || gamepad1.right_stick_y < -0.4) {
            //This if statement moves the linear slides for the claw
            //Change to -gamepade..... if needed, you probably will need to do it because of how the motors will be place, MAKE SURE TO UPDATE THE -'s based on the placement of the motors.
            LinearClaw.setPower(gamepad1.right_stick_y);
        }
        if(gamepad1.x) {
            Gate1.setPosition(0);
            Gate2.setPosition(1);
            //Opens Gates-Change the # as needed
        }
        if(gamepad1.y) {
            Gate1.setPosition(1);
            Gate2.setPosition(0);
            //Closes Gates-Change the # as needed
        }
    }

}

