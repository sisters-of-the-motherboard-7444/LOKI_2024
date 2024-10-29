package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "Auto7444Loki2024")
public class Loki2024Motherboard extends LinearOpMode {

  private DcMotor backLeft;
  private DcMotor backRight;
  private DcMotor frontLeft;
  private DcMotor frontRight;

  double Speed;

  /**
   * This sample contains the bare minimum Blocks for any regular OpMode. The 3 blue
   * Comment Blocks show where to place Initialization code (runs once, after touching the
   * DS INIT button, and before touching the DS Start arrow), Run code (runs once, after
   * touching Start), and Loop code (runs repeatedly while the OpMode is active, namely not
   * Stopped).
   */
  @Override
  public void runOpMode() {
    backLeft = hardwareMap.get(DcMotor.class, "backLeft");
    backRight = hardwareMap.get(DcMotor.class, "backRight");
    frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
    frontRight = hardwareMap.get(DcMotor.class, "frontRight");

    // Put initialization blocks here.
    backLeft.setDirection(DcMotor.Direction.REVERSE);
    backRight.setDirection(DcMotor.Direction.FORWARD);
    frontLeft.setDirection(DcMotor.Direction.REVERSE);
    frontRight.setDirection(DcMotor.Direction.FORWARD);
    waitForStart();

    if (opModeIsActive()) {
      // Put run blocks here.
      Speed = .5;
      diagonal_right();
      sleep(250);
      Speed = .25;
      forward();
      sleep(500);
      stop2();
    }
  }

  /**
   * Describe this function...
   */
  private void diagonal_back_left() {
    backLeft.setPower(0);
    backRight.setPower(Speed * -1);
    frontLeft.setPower(Speed * -1);
    frontRight.setPower(0);
  }

  /**
   * Describe this function...
   */
  private void right() {
    backLeft.setPower(Speed * -1);
    backRight.setPower(Speed);
    frontLeft.setPower(Speed);
    frontRight.setPower(Speed * -1);
  }

  /**
   * Describe this function...
   */
  private void diagonal_left() {
    backLeft.setPower(Speed);
    backRight.setPower(0);
    frontLeft.setPower(0);
    frontRight.setPower(Speed);
  }

  /**
   * Describe this function...
   */
  private void backwords() {
    backLeft.setPower(Speed * -1);
    backRight.setPower(Speed * -1);
    frontLeft.setPower(Speed * -1);
    frontRight.setPower(Speed * -1);
  }

  /**
   * Describe this function...
   */
  private void diagonal_right() {
    backLeft.setPower(0);
    backRight.setPower(Speed);
    frontLeft.setPower(Speed);
    frontRight.setPower(0);
  }

  /**
   * Describe this function...
   */
  private void forward() {
    backLeft.setPower(Speed);
    backRight.setPower(Speed);
    frontLeft.setPower(Speed);
    frontRight.setPower(Speed);
  }

  /**
   * Describe this function...
   */
  private void stop2() {
    backLeft.setPower(0);
    backRight.setPower(0);
    frontLeft.setPower(0);
    frontRight.setPower(0);
  }

  /**
   * Describe this function...
   */
  private void RotateLeft() {
    backLeft.setPower(Speed * -1);
    backRight.setPower(Speed);
    frontLeft.setPower(Speed * -1);
    frontRight.setPower(Speed);
  }

  /**
   * Describe this function...
   */
  private void RotateRight() {
    backLeft.setPower(Speed);
    backRight.setPower(Speed * -1);
    frontLeft.setPower(Speed);
    frontRight.setPower(Speed * -1);
  }
  
  /**
   * Describe this function...
   */
  private void diagonal_back_right() {
    backLeft.setPower(Speed * -1);
    backRight.setPower(0);
    frontLeft.setPower(0);
    frontRight.setPower(Speed * -1);
  }

  /**
   * Describe this function...
   */
  private void left() {
    backLeft.setPower(Speed);
    backRight.setPower(Speed * -1);
    frontLeft.setPower(Speed * -1);
    frontRight.setPower(Speed);
  }
}
