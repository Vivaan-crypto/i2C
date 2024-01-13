package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
@Autonomous(name = "RightAuto_DriveToBackBoard")
//Where in the driver program this will be found ^
public class RightAuto_DriveToBackBoard extends LinearOpMode
{
    DcMotor fr, fl, br, bl;
    private Servo Claw;
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
        Claw = hardwareMap.get(Servo.class, "Claw");
        fr.setDirection(DcMotor.Direction.REVERSE); //Motor with ISSUES!!! Backwards
        fl.setDirection(DcMotor.Direction.FORWARD);
        br.setDirection(DcMotor.Direction.REVERSE);
        bl.setDirection(DcMotor.Direction.REVERSE);
        waitForStart();
        while(opModeIsActive()) {
            //1000 = 1 sec
            //IF YOU CHANGE SPEED THEN CHANGE TIME OR IT WILL OVER/UNDER TRAVEL
            //Change when Mechanical team adds 425's(motors)
            sleep(3000);
            //Moves Forward
            fr.setPower(-0.5);
            fl.setPower(0.5);
            br.setPower(0.5);
            bl.setPower(0.5);
            sleep(300);
            //Robot Drifts into position.
            fr.setPower(-0.5);
            fl.setPower(0.5);
            br.setPower(0.5);
            bl.setPower(-0.5);
            sleep (1800);
            //Robot Moves FORWARD
            fr.setPower(-0.5);
            fl.setPower(0.5);
            br.setPower(0.5);
            bl.setPower(0.5);
            sleep (800);
            Claw.setPosition(0);//Releases pixel-Change # if needed
            fl.setPower(0);
            fr.setPower(0);
            bl.setPower(0);
            br.setPower(0);
            sleep (1000);
            fr.setPower(-0.3);
            fl.setPower(-0.3);
            br.setPower(0.3);
            bl.setPower(-0.3);
            sleep(1500);
            fl.setPower(-0.3);
            fr.setPower(0.3);
            bl.setPower(0.3);
            br.setPower(0.3);
            sleep (600);
            fl.setPower(0);
            fr.setPower(0);
            bl.setPower(0);
            br.setPower(0);
            sleep (24500);
        }
    }
}