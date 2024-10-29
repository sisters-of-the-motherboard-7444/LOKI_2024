package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp

public class TeleOpPrimary extends LinearOpMode {
  public Servo armLeft, armRight, clawServo, wristServo;
  public DcMotorEx motor, slidesLeft, slidesRight, frontLeft, frontRight, backLeft, backRight;
  
  PIDSlides leftPID = new PIDSlides(0.005, 0, 0.0001);
  PIDSlides rightPID = new PIDSlides(0.005, 0, 0.0001);
  
  @Override
  public void runOpMode() throws InterruptedException {
    armLeft = hardwareMap.servo.get("armLeft");
    armRight = hardwareMap.servo.get("armRight");
    clawServo = hardwareMap.servo.get("clawServo");
    wristServo = hardwareMap.servo.get("wristServo");
    slidesLeft = hardwareMap.get(DcMotorEx.class, "slidesLeft");
    slidesRight = hardwareMap.get(DcMotorEx.class, "slidesRight");
    DcMotor frontLeftMotor = hardwareMap.dcMotor.get("frontLeft");
    DcMotor backLeftMotor = hardwareMap.dcMotor.get("backLeft");
    DcMotor frontRightMotor = hardwareMap.dcMotor.get("frontRight");
    DcMotor backRightMotor = hardwareMap.dcMotor.get("backRight");

    slidesRight.setDirection(DcMotorEx.Direction.REVERSE);
    slidesLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    slidesRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    slidesLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    slidesRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
    frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    
    waitForStart();
    if (isStopRequested()) return;
    
    int slidesPosition = 0;

    int GROUND_POSITION = 0;
    int LOW_POSITION = 1000;
    int MID_POSITION = 0;
    int HIGH_POSITION = 0;
    int HANG_POSITION = 0;
    
    while (opModeIsActive()) {
      
      double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
      double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
      double rx = gamepad1.right_stick_x;
      
      double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
      double frontLeftPower = (y + x + rx) / denominator;
      double backLeftPower = (y - x + rx) / denominator;
      double frontRightPower = (y - x - rx) / denominator;
      double backRightPower = (y + x - rx) / denominator;

      frontLeftMotor.setPower(frontLeftPower);
      backLeftMotor.setPower(backLeftPower);
      frontRightMotor.setPower(frontRightPower);
      backRightMotor.setPower(backRightPower);
      
      if (gamepad1.x) { // ground
        armRight.setPosition(0.04);
        armLeft.setPosition(.96);
        wristServo.setPosition(0.75);
        slidesPosition = GROUND_POSITION;
      }
      else if (gamepad1.a) { // low score
        armRight.setPosition(0.75);
        armLeft.setPosition(0.25);
        wristServo.setPosition(1);
        slidesPosition = LOW_POSITION;
      }
      
      if (gamepad1.right_bumper) { // claw open
        clawServo.setPosition(0);
      }
      else if (gamepad1.left_bumper) { // claw closed
        clawServo.setPosition(0.1);
      }
      
      telemetry.addData("Left Slides", slidesLeft.getCurrentPosition());
      telemetry.addData("Right Slides", slidesRight.getCurrentPosition());
      telemetry.addData("Target Position", slidesPosition);

      double leftPower = leftPID.update(slidesPosition, slidesLeft.getCurrentPosition());
      double rightPower = rightPID.update(slidesPosition, slidesRight.getCurrentPosition());

      telemetry.addData("Left Power", leftPower);
      telemetry.addData("Right Power", rightPower);
      telemetry.update(); 

      slidesLeft.setPower(leftPower);  
      slidesRight.setPower(rightPower); 
      
    }
  }
}
