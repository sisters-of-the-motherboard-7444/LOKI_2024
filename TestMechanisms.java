package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp

public class TestMechanisms extends LinearOpMode {
  public Servo armLeft, armRight, clawServo, wristServo;
  public DcMotorEx motor, slidesLeft, slidesRight;
  
  @Override
  public void runOpMode() throws InterruptedException {
    armLeft = hardwareMap.servo.get("armLeft");
    armRight = hardwareMap.servo.get("armRight");
    clawServo = hardwareMap.servo.get("clawServo");
    wristServo = hardwareMap.servo.get("wristServo");
    slidesLeft = hardwareMap.get(DcMotorEx.class, "slidesLeft");
    slidesRight = hardwareMap.get(DcMotorEx.class, "slidesRight");
    
    slidesRight.setDirection(DcMotorEx.Direction.REVERSE);
    
    waitForStart();
    
    while (opModeIsActive()) {
      if (gamepad1.a) {
        slidesLeft.setPower(-0.5);
        slidesRight.setPower(-0.5);
      }
      else if (gamepad1.x) {
        armRight.setPosition(0);
        armLeft.setPosition(1);
      }
      else if (gamepad1.y) {
        slidesLeft.setPower(0.5);
        slidesRight.setPower(0.5);
      }
      else if (gamepad1.b) {
        wristServo.setPosition(0.77);
      }
      else {
        slidesLeft.setPower(0);
        slidesRight.setPower(0);
      }
      
      telemetry.addData("Left Slides", slidesLeft.getCurrentPosition());
      telemetry.addData("Right Slides", slidesRight.getCurrentPosition());
      
    }
  }
}
