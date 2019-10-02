package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class DriverControl extends LinearOpMode{

    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    private Servo platform;
    public void runOpMode(){
        frontLeft  = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight  = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft  = hardwareMap.get(DcMotor.class, "backLeft");
        backRight  = hardwareMap.get(DcMotor.class, "backRight");
        platform = hardwareMap.get(Servo.class, "platform");

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();
        double fbLeftStick=0;
        double lrLeftStick=0;
        double lrRightStick=0;
        double fbRightStick=0;
        double ppUp=0; //subject to change
        double ppDown=0; // subject to change
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        //put all movement code in here
        while(opModeIsActive()){
            fbLeftStick = -this.gamepad1.left_stick_y;
            fbRightStick = -this.gamepad1.right_stick_y;
            lrLeftStick = -this.gamepad1.left_stick_x; //if turning the wrong direction reverse the sign
            lrRightStick = -this.gamepad1.right_stick_x; //if turning the wrong direction reverse the sign
            if(this.gamepad1.left_trigger>0.1){
                platformUp(ppUp);
            }
            else if(this.gamepad1.left_bumper==true){
                platformDown(ppDown);
            }
            movingFB(fbLeftStick);
            //strafe(lrLeftStick);
            turn(lrRightStick);

            //tank(fbLeftStick, fbRightStick);
            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }
    public void tank(double fbLeftStick, double fbRightStick){

        frontLeft.setPower(fbLeftStick);
        backLeft.setPower(fbLeftStick);
        backRight.setPower(fbRightStick);
        frontRight.setPower(fbRightStick);
        telemetry.addData("Target Power", fbLeftStick);
        telemetry.addData("Target Power", fbRightStick);
        telemetry.addData("Motor Power", frontLeft.getPower());
        telemetry.addData("Motor Power", frontRight.getPower());
        telemetry.addData("Motor Power", backLeft.getPower());//hi test
        telemetry.addData("Motor Power", backRight.getPower());

    }
    public void movingFB(double fbLeftStick){

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

        frontLeft.setPower(lrLeftStick);
        backLeft.setPower(-lrLeftStick);
        frontRight.setPower(-lrLeftStick);
        backRight.setPower(lrLeftStick);
        telemetry.addData("Target Power", lrLeftStick);
        telemetry.addData("Motor Power", frontLeft.getPower());
        telemetry.addData("Motor Power", frontRight.getPower());
        telemetry.addData("Motor Power", backLeft.getPower());//hi test
        telemetry.addData("Motor Power", backRight.getPower());
    }
    public void turn(double lrRightStick){

        frontLeft.setPower(lrRightStick);
        backLeft.setPower(lrRightStick);
        frontRight.setPower(-lrRightStick);
        backRight.setPower(-lrRightStick);
        telemetry.addData("Target Power", lrRightStick);
        telemetry.addData("Motor Power", frontLeft.getPower());
        telemetry.addData("Motor Power", frontRight.getPower());
        telemetry.addData("Motor Power", backLeft.getPower());
        telemetry.addData("Motor Power", backRight.getPower());
    }

    public void platformUp(double plat){
        platform.setPosition(plat);
        telemetry.addData("Target Power", plat);
        telemetry.addData("Servo Power", platform.getPosition());
    }
    public void platformDown(double plat){
        platform.setPosition(plat);
        telemetry.addData("Target Power", plat);
        telemetry.addData("Servo Power", platform.getPosition());
    }
    /*
    public void m2(double m2){
    }
    public void m3(double m3){
    }
    public void m4(double m4){
    }
    */
}
