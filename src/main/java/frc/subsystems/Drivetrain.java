/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;



import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import frc.robot.*;

public class Drivetrain {
  public static final double DEADBAND = 0.05;
  public static final double MOTORMIN = 0.03;

  private WPI_TalonSRX frontLeftTalon, backLeftTalon, frontRightTalon, backRightTalon;

  public Drivetrain() {
    this.frontLeftTalon = new WPI_TalonSRX(RobotMap.FRONTLEFT_TALON);
    this.backLeftTalon = new WPI_TalonSRX(RobotMap.BACKLEFT_TALON);
    this.frontRightTalon = new WPI_TalonSRX(RobotMap.FRONTRIGHT_TALON);
    this.backRightTalon = new WPI_TalonSRX(RobotMap.BACKRIGHT_TALON);

    this.frontLeftTalon.setInverted(false);
    this.backLeftTalon.setInverted(false);
    this.frontRightTalon.setInverted(false);
    this.backRightTalon.setInverted(false);

    this.backLeftTalon.set(ControlMode.Follower, this.frontLeftTalon.getDeviceID());
    this.backRightTalon.set(ControlMode.Follower, this.frontRightTalon.getDeviceID());
  }

  public void drive(double throttle, double turn) {
    double leftTurn = throttle-turn;
    double rightTurn = throttle+turn;

    this.frontLeftTalon.set(Math.pow((leftTurn-DEADBAND)/(1-DEADBAND), 3)*(1-MOTORMIN)+MOTORMIN);
    this.frontRightTalon.set(Math.pow((rightTurn-DEADBAND)/(1-DEADBAND), 3)*(1-MOTORMIN)+MOTORMIN);
  }

}
