// updated 7/22

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp

public class TeleOpLOKI extends LinearOpMode {
  DcMotorEx motor, slidesLeft, slidesRight;
  public Servo clawServo, wristServo, armLeft, armRight;

  PIDSlides leftPID = new PIDSlides(0.25, 0, 0);
  PIDSlides rightPID - new PIDSlides(0.25, 0, 0);

  @Override
  public boid runOpMode() throws InterruptedException {
    slidesLeft = hardwareMap.get(DcMotorEx.class, "slidesLeft");
    slidesRight = hardwardMap.get(DcMotorEx.class, "slidesRight");

    slidesRight.setDirection(DcMotorEx.Direction.REVERSE);
    slidesLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    slidesRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    slidesLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    slidesRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    waitForStart();

    int targetPosition 0;

    int GROUND_POSITION = 0;
    int MID_POSITION = 0;
    int HIGH_POSITION = 0;
    int HANG_POSITION = 0;

    while (opModeIsActive()) {
      
      if (gamepad1.right_bumper) { // open
        clawServo.setPosition(1); } 
      else if (gamepad1.left_bumper) { // closed
        clawServo.setPosition(1); }
        
      if (gamepad1.x) { 
        targetPosition = GROUND_POSITION;
        clawServo.setPosition(1);
        wristServo.setPosition(1);
        armLeft.setPosition(1);
        armRight.setPosition(1); }

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
