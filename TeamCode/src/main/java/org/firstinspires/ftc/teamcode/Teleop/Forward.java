package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp
//Where in the driver program this will be found ^
public class Forward {
    private DcMotor fr;
    private DcMotor fl;
    private DcMotor br;
    private DcMotor bl;
    Telemetry telemetry;
    Gamepad gamePad1;
    //Change to linear op mode and make the adjusments needed
    //Delete telemetry if error about telemetry comes up
    public Forward(HardwareMap hardwareMap, Gamepad gamePad1, Telemetry telemetry) {
        this.telemetry = telemetry;
        this.gamePad1 = gamePad1;
        fr = hardwareMap.get(DcMotor.class, "fr");
        fl = hardwareMap.get(DcMotor.class, "fl");
        br= hardwareMap.get(DcMotor.class, "br");
        bl = hardwareMap.get(DcMotor.class, "bl");
        // double FPower = gamePad1.left_stick_y;
        // double TPower = gamePad1.left_stick_x;
        //Which Motor.
        //Alternate: motor = hardwareMap/.get(DcMotor.class, deviceName "motor1"); In case number one does not work.
    }
    public void loop() {
        if (gamePad1.left_stick_y > .3 || gamePad1.left_stick_y < -.3 ){
            fr.setDirection(DcMotor.Direction.REVERSE);
            fl.setDirection(DcMotor.Direction.FORWARD);
            br.setDirection(DcMotor.Direction.REVERSE);
            bl.setDirection(DcMotor.Direction.REVERSE);
            fr.setPower(gamePad1.left_stick_y);
            fl.setPower(gamePad1.left_stick_y);
            br.setPower(gamePad1.left_stick_y);
            bl.setPower(gamePad1.left_stick_y);
        }
        if (gamePad1.left_stick_x > .3 || gamePad1.left_stick_x < -.3){
            fr.setDirection(DcMotor.Direction.REVERSE);
            fl.setDirection(DcMotor.Direction.REVERSE);
            br.setDirection(DcMotor.Direction.REVERSE);
            bl.setDirection(DcMotor.Direction.FORWARD);
            fr.setPower(gamePad1.left_stick_x);
            fl.setPower(gamePad1.left_stick_x);
            br.setPower(gamePad1.left_stick_x);
            bl.setPower(gamePad1.left_stick_x);
        }
        if (gamePad1.left_bumper){
            fr.setDirection(DcMotor.Direction.REVERSE);
            fl.setDirection(DcMotor.Direction.FORWARD);
            br.setDirection(DcMotor.Direction.FORWARD);
            bl.setDirection(DcMotor.Direction.FORWARD);
            fr.setPower(.8);
            fl.setPower(.8);
            br.setPower(.8);
            bl.setPower(.8);
        }
        if (gamePad1.right_bumper){
            fr.setDirection(DcMotor.Direction.REVERSE);
            fl.setDirection(DcMotor.Direction.FORWARD);
            br.setDirection(DcMotor.Direction.FORWARD);
            bl.setDirection(DcMotor.Direction.FORWARD);
            fr.setPower(-.8);
            fl.setPower(-.8);
            br.setPower(-.8);
            bl.setPower(-.8);
        }
        fr.setPower(0);
        fl.setPower(0);
        br.setPower(0);
        bl.setPower(0);
    }
}
//Thanks