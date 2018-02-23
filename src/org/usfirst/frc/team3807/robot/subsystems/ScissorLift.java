package org.usfirst.frc.team3807.robot.subsystems;

import org.usfirst.frc.team3807.robot.RobotValues;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class ScissorLift extends Subsystem {
	WPI_TalonSRX ScissorLiftMotor1;

//	ScissorLiftMotor1.
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}

	public ScissorLift(int motorOne) {
		if (motorOne != -1) {
			ScissorLiftMotor1 = new WPI_TalonSRX(motorOne);
		}
	}

	public void extendScissorLift(double speedWheelIntake) {
		ScissorLiftMotor1.set(speedWheelIntake);
		RobotValues.extending = true;
		RobotValues.retracting = false;
	}

	public void reduceScissorLift(double speedWheelIntake) {
		ScissorLiftMotor1.set(-speedWheelIntake);
		RobotValues.extending = false;
		RobotValues.retracting = true;
	}

	public void stopScissorLift() {
		ScissorLiftMotor1.stopMotor();
		RobotValues.extending = false;
		RobotValues.retracting = false;
	}
}