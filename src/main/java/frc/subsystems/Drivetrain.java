/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;


import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

import frc.robot.*;

public class Drivetrain extends Subsystem {
  public static final double DEADBAND = 0.05;

  private WPI_TalonSRX frontLeftTalon, backlefttalon, frontRightTalon, backRightTalon;

  public Drivetrain() {
    this.frontLeftTalon = new WPI_TalonSRX(RobotMap.FRONTLEFT_TALON);
    this.backLeftTalon = new WPI_TalonSRX(RobotMap.BACKLEFT_TALON);
    this.frontRightTalon = new WPI_TalonSRX(RobotMap.FRONTRIGHT_TALON);
    this.backRightTalon = new WPI_TalonSRX(RobotMap.BACKRIGHT_TALON);

    this.frontLeftTalon.setInverted(true);
    this.backLeftTalon.setInverted(true);
    this.frontRightTalon.setInverted(false);
    this.backRightTalon.setInverted(false);

    this.backLeftTalon.set(ControlMode.Follower, this.frontLeftTalon.getDeviceID());
    this.backRightTalon.set(ControlMode.Follower, this.frontRightTalon.getDeviceID());
  }

  public void drive(double throttle, double turn) {
    if(Math.abs(throttle) < DEADBAND) {
      throttle = 0;
    }
    if(Math.abs(turn) < DEADBAND) {
      turn = 0;
    }

    this.frontLeftTalon.set(throttle+turn);
    this.frontRightTalon.set(throttle-turn);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
