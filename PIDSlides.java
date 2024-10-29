// updated 7/22

package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.util.ElapsedTime;

public class PIDSlides {
  ElapsedTime timer = new ElapsedTime();
  
  double lastError = 0;
  double integralSum = 0;
  double Kp, Ki, Kd; // proportional, integral, derivative
  //double reference = 1;

  // constructing controller
  public PIDSlides(double Kp, double Ki, double Kd) {
    this.Kp = Kp;
    this.Ki = Ki;
    this.Kd = Kd;
  }

  // update the controller output
  public double update(double target, double state) {
    double error = target - state;
    integralSum += error * timer.seconds();
    double derivative = (error - lastError) / timer.seconds();
    lastError = error;
    timer.reset();

    return Kp * error + Ki * integralSum + Kd * derivative;
  }
}
