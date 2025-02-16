package org.firstinspires.ftc.teamcode;

        import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
        import com.qualcomm.robotcore.hardware.DcMotor;
        import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="AutonCode1", group="LinearOpMode")
public class AutonCode1 extends LinearOpMode { //redPlat
    private DcMotor frontLeft, backLeft, frontRight, backRight;
    private Servo hooks, platform;
    public int d = 4; //Diameter of Wheel
    public double tick = 537.6; //# of ticks for one rotation
    public void runOpMode(){
        frontLeft  = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight  = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft  = hardwareMap.get(DcMotor.class, "backLeft");
        backRight  = hardwareMap.get(DcMotor.class, "backRight");
        platform = hardwareMap.get(Servo.class, "platform");
        hooks = hardwareMap.get(Servo.class, "hooks");
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        /* skeleton code
        forward(null);
        turn(false, null);
        forward(null);
        turn(true, null);
        platDown ( null );
        backwards(null);
        platUp(null);
        turn(true, null);
        forward(null);
        turn(false, null);
        forward(null);
        turn(false, null);
        forward(null);
        turn(false, null);
        platDown ( null );
        forward(null);
        platUp();
        turn(false, null);
        forward(null);
        */

    }
    public int distanceCalc(int distance){
        int ticks=(int)(tick*(distance/(12.56)));
        return ticks;
    }
    public void forward(int distance) {
        reset();
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        int tick = distanceCalc(distance);
        frontLeft.setTargetPosition(tick);
        frontRight.setTargetPosition(tick);
        backLeft.setTargetPosition(tick);
        backRight.setTargetPosition(tick);
        while(frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy() && opModeIsActive()) {

        }
        stopDrive();

    }
    public void backwards(int distance) {
        reset();
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        int tick = distanceCalc(-distance);
        frontLeft.setTargetPosition(tick);
        frontRight.setTargetPosition(tick);
        backLeft.setTargetPosition(tick);
        backRight.setTargetPosition(tick);
        while(frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy() && opModeIsActive()) {

        }
        stopDrive();

    }
    public int ratio = 0; //needs to be calculated
    public int calcTurn(int degrees){

        int encoderTick = ratio*degrees;
        return encoderTick;
    }
    public void turn(boolean turn,int degrees) { //true = left, false = right
        if(turn){
            reset();

        }
        else{
            reset();
        }
    }
    public void platDown(int down) {
    }
    public void platUp(int up) {
    }
    public void reset(){ //resetEncoder Values
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public void stopDrive(){ //stop DriveTrain
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower (0);
        backRight.setPower(0);
    }
}


