package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name="DriverControl", group="LinearOpmode")
public class DriverControl extends LinearOpMode{

    private DcMotor frontLeft, backLeft, frontRight, backRight;
    private Servo hooks1,hooks2, platform;
    public void runOpMode(){
        frontLeft  = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight  = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft  = hardwareMap.get(DcMotor.class, "backLeft");
        backRight  = hardwareMap.get(DcMotor.class, "backRight");
        platform = hardwareMap.get(Servo.class, "platform");
        hooks1 = hardwareMap.get(Servo.class, "hooks1");
        hooks2 = hardwareMap.get(Servo.class, "hooks2");

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        backLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection ( DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);
        waitForStart();
        double fbLeftStick = 0;
        double lrLeftStick = 0;
        double lrRightStick = 0;
        double fbRightStick = 0;
        double ppUp = 1; //subject to change
        double ppDown= 0 ; // subject to change
        double hookUp = 1;//subject to change
        double hookDown = 0;//subject to change
        boolean reverse = false;
        //put all movement code in here
        while(opModeIsActive()){
            //joystick control code
            fbLeftStick = -this.gamepad1.left_stick_y;
            fbRightStick = -this.gamepad1.right_stick_y;
            lrLeftStick = -this.gamepad1.left_stick_x; //if turning the wrong direction reverse the sign
            lrRightStick = -this.gamepad1.right_stick_x; //if turning the wrong direction reverse the sign

            if(this.gamepad1.left_trigger > 0.1){
                platformUp(ppUp); //move the platform up

            }
            else if(this.gamepad1.left_bumper == true){
                platformDown(ppDown); //move the platform down

            }
            if(this.gamepad1.right_trigger > 0.1){
                hUp(hookUp);

            }
            else if(this.gamepad1.right_bumper == true){
                hDown(hookDown);

            }

            //movingFB(fbLeftStick);
            //strafe(lrLeftStick);
            //turn(lrRightStick);

            tank(fbLeftStick, fbRightStick);
            telemetry.addData("Status", "Running");
            telemetry.update();
        }
    }
    public void tank(double fbLeftStick, double fbRightStick){ //tank drive (one joystick controls one wheel)
        if reverse == false{
            frontLeft.setPower(fbLeftStick);
            backLeft.setPower(fbLeftStick);
            backRight.setPower(fbRightStick);
            frontRight.setPower(fbRightStick);
        }else if reverse == true{
            frontLeft.setPower(fbRightStick);
            backLeft.setPower(fbRightStick);
            backRight.setPower(fbLeftStick);
            frontRight.setPower(fbLeftStick);
        }
        telemetry.addData("Target Power", fbLeftStick);
        telemetry.addData("Target Power", fbRightStick);
        telemetry.addData("Motor Power", frontLeft.getPower());
        telemetry.addData("Motor Power", frontRight.getPower());
        telemetry.addData("Motor Power", backLeft.getPower());
        telemetry.addData("Motor Power", backRight.getPower());
        
    }
    public void movingFB(double fbLeftStick){// moving forwards and backwards
        if reverse == false{
            frontLeft.setPower(fbLeftStick);
            frontRight.setPower(fbLeftStick);
            backLeft.setPower(fbLeftStick);
            backRight.setPower(fbLeftStick);
        }else if reverse == true{
            frontLeft.setPower(fbRightStick);
            frontRight.setPower(fbRightStick);
            backLeft.setPower(fbRightStick);
            backRight.setPower(fbEightStick);
        }
        telemetry.addData("Target Power", fbLeftStick);
        telemetry.addData("Motor Power", frontLeft.getPower());
        telemetry.addData("Motor Power", frontRight.getPower());
        telemetry.addData("Motor Power", backLeft.getPower());
        telemetry.addData("Motor Power", backRight.getPower());
    }

    public void strafe(double lrLeftStick){ //moving the root side to side w/o turning
        if reverse == false{
            frontLeft.setPower(lrLeftStick);
            backLeft.setPower(-lrLeftStick);
            frontRight.setPower(-lrLeftStick);
            backRight.setPower(lrLeftStick);
        }else if reverse == true{
            frontLeft.setPower(lrRightStick);
            backLeft.setPower(-lrRightStick);
            frontRight.setPower(-lrRightStick);
            backRight.setPower(lrRightStick);
        }
        telemetry.addData("Target Power", lrLeftStick);
        telemetry.addData("Motor Power", frontLeft.getPower());
        telemetry.addData("Motor Power", frontRight.getPower());
        telemetry.addData("Motor Power", backLeft.getPower());
        telemetry.addData("Motor Power", backRight.getPower());
    }
    public void turn(double lrRightStick){//turning in place
        if reverse == false{
            frontLeft.setPower(lrRightStick);
            backLeft.setPower(-lrRightStick);
            frontRight.setPower(lrRightStick);
            backRight.setPower(-lrRightStick);
        }else if reverse == true{
            frontLeft.setPower(lrLeftStick);
            backLeft.setPower(-lrLeftStick);
            frontRight.setPower(lrLeftStick);
            backRight.setPower(-lrLeftStick);
        }
        telemetry.addData("Target Power", lrRightStick);
        telemetry.addData("Motor Power", frontLeft.getPower());
        telemetry.addData("Motor Power", frontRight.getPower());
        telemetry.addData("Motor Power", backLeft.getPower());
        telemetry.addData("Motor Power", backRight.getPower());
    }

    public void platformUp(double plat){//platform servo up
        platform.setPosition(plat);
        telemetry.addData("Target Power", plat);
        telemetry.addData("Servo Power", platform.getPosition());
    }
    public void platformDown(double plat){//platform servo down
        platform.setPosition(plat);
        telemetry.addData("Target Power", plat);
        telemetry.addData("Servo Power", platform.getPosition());
    }
    public void hUp(double hook){//hook servo up
        hooks1.setPosition(hook);
        hooks2.setPosition(hook);
        telemetry.addData("Target Power", hook);
        telemetry.addData("Servo Power", hooks1.getPosition());
        telemetry.addData("Servo Power", hooks2.getPosition ());

    }
    public void hDown(double hook){//hook servo down
        hooks1.setPosition(hook);
        hooks2.setPosition(hook);
        telemetry.addData("Target Power", hook);
        telemetry.addData("Servo Power", hooks1.getPosition());
        telemetry.addData("Servo Power", hooks2.getPosition ());
    }
    
    public void reveseMotor(this.gamepad1.a){
         if reverse == false{
             reverse == true;
         }else if reverse == true{
             reverse == false;
         }
    } 
             
}
