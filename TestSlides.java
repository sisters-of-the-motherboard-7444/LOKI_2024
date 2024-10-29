package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp

public class TestSlides extends LinearOpMode {
  public DcMotorEx motor, slidesLeft, slidesRight;
  public Servo clawServo, wristServo, armLeft, armRight;

  PIDSlides leftPID = new PIDSlides(0.005, 0, 0.0001);
  PIDSlides rightPID = new PIDSlides(0.005, 0, 0.0001);

  @Override
  public void runOpMode() throws InterruptedException {
    slidesLeft = hardwareMap.get(DcMotorEx.class, "slidesLeft");
    slidesRight = hardwareMap.get(DcMotorEx.class, "slidesRight");

    slidesRight.setDirection(DcMotorEx.Direction.REVERSE);
    slidesLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    slidesRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    slidesLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    slidesRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    
    waitForStart();

    int targetPosition = 0;

    int GROUND_POSITION = 0;
    int LOW_POSITION = 3000;
    int MID_POSITION = 0;
    int HIGH_POSITION = 0;
    int HANG_POSITION = 0;

    while (opModeIsActive()) {
      
      if (gamepad1.x) { 
        targetPosition = LOW_POSITION;
        }
      else if (gamepad1.a) {
        targetPosition = GROUND_POSITION;
      }
      else if (gamepad1.y) {
        slidesRight.setPower(0.5);
        slidesLeft.setPower(0.5);
      }
      else if (gamepad1.b) {
        slidesRight.setPower(-0.5);
        slidesLeft.setPower(-0.5);
      }

      telemetry.addData("Left Slides", slidesLeft.getCurrentPosition());
      telemetry.addData("Right Slides", slidesRight.getCurrentPosition());
      telemetry.addData("Target Position", targetPosition);

      double leftPower = leftPID.update(targetPosition, slidesLeft.getCurrentPosition());
      double rightPower = rightPID.update(targetPosition, slidesRight.getCurrentPosition());

      telemetry.addData("Left Power", leftPower);
      telemetry.addData("Right Power", rightPower);
      telemetry.update(); 

      slidesLeft.setPower(leftPower);  
      slidesRight.setPower(rightPower); 
    }
  }
}
