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
/*Use the codes for Hanging if team has time and is able to build the mechanism. Mechanism: Put a viper
kit behind the elbow on the side which doesn't have the expansion hub, or if possible then on the
center-plate which is in front of the control hub, attach claw to viper with inside facing downward*/
//Claw for hanging will basically a cylinder cut in half and inside hollow, and then place it latterally on viper kit

  private DcMotorEx HangingMechanismMotor;
  private Servo DroneLaunchServo;
  private Servo Gate1;
  private Servo Gate2;
  private Servo Claw;
  private DcMotorEx LinearClaw;
  private Forward drive;

    @Override
    public void runOpMode() {
        drive = new Forward(hardwareMap, gamepad1, telemetry);
        HangingMechanismMotor = hardwareMap.get(DcMotorEx.class, "HangingMechanismMotor");
        DroneLaunchServo = hardwareMap.get(Servo.class, "DroneLaunchServo");
        Gate1 = hardwareMap.get(Servo.class, "Gate1");
        Gate2 = hardwareMap.get(Servo.class, "Gate2");
        Claw = hardwareMap.get(Servo.class, "Claw");
        LinearClaw = hardwareMap.get(DcMotorEx.class, "LinearClaw"); //Configure in Expansion Hub
        double ClawPosition1 = gamepad1.right_trigger;
        double ClawPosition2 = gamepad1.left_trigger;
        waitForStart();
        while (opModeIsActive()) {

            drive.loop();
            ControllLoops();
        }

    }
    public void ControllLoops() {
        if (gamepad1.dpad_down) {
            DroneLaunchServo.setPosition(1); /* Set servo to 180 degrees position, which primes launcher for drone launch. Change the # as needed*/
        }
        if (gamepad1.dpad_up) {
            DroneLaunchServo.setPosition(0);
            // Launches the drone, and sets it to initial position. Change the # as needed
        }
        if (gamepad1.b) {
            Claw.setPosition(1);//Grabs the pixel, change the # as needed-Left
        }
        if (gamepad1.a) {
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
            //Opens Gates-Change the # as needed based upon placement of servo
        }
        if(gamepad1.y) {
            Gate1.setPosition(1);
            Gate2.setPosition(0);
            //Closes Gates-Change the # as needed based upon placement of servo
        }
        if(gamepad1.a){
            HangingMechanismMotor.setPower(gamepad1.left_trigger);//Shortens Viper slides crunching robot and raising robot of ground
            //Change -left trigger... as needed based upon placement of motor
            HangingMechanismMotor.setPower(gamepad1.right_trigger);//Extends Viper slides releasing robot and putting it back down on the ground
            //Change to -right trigger... if needed due to motor placement
          }
        }
    }
