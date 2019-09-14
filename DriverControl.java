package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class DriverControl extends LinearOpMode {
    //initiating motors
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    public void runOpMode(){
        frontLeft  = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight  = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft  = hardwareMap.get(DcMotor.class, "backLeft");
        backRight  = hardwareMap.get(DcMotor.class, "backRight");

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();
        double fbLeftStick=0; //represents the left stick moving forwards and backwards
        double lrLeftStick=0;
        double lrRightStick=0;
        //put all movement code in here
        while(opModeIsActive()){
            movingFB(fbLeftStick);
            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }
    public void movingFB(double fbLeftStick){
        fbLeftStick = -this.gamepad1.left_stick_y;
        frontLeft.setPower(fbLeftStick);
        frontRight.setPower(fbLeftStick);
        backLeft.setPower(fbLeftStick);
        backLeft.setPower(fbLeftStick);
        telemetry.addData("Target Power", fbLeftStick);
        telemetry.addData("Motor Power", frontLeft.getPower());
        telemetry.addData("Motor Power", frontRight.getPower());
        telemetry.addData("Motor Power", backLeft.getPower());
        telemetry.addData("Motor Power", backRight.getPower());
    }
    public void strafe(double lrLeftStick){
        lrLeftStick = -this.gamepad1.left_stick_x;
        frontLeft.setPower(lrLeftStick);
    }
    public void turn(double lrRightStick){

    }
}
