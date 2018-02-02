package org.usfirst.frc.team3807.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class WheelIntake extends Subsystem {
	WPI_TalonSRX IntakeWheel1;
	WPI_TalonSRX IntakeWheel2;

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

	public WheelIntake(int motorOne, int motorTwo) {
		if (motorOne != -1 && motorTwo != -1) {
			IntakeWheel1 = new WPI_TalonSRX(motorOne);
			IntakeWheel2 = new WPI_TalonSRX(motorTwo);

		}
	}

	public void RunWheelIntake(double speedWheelIntake) {
		IntakeWheel1.set(speedWheelIntake);
		IntakeWheel2.set(-speedWheelIntake);

	}

	public void reverseWheelIntake(double speedWheelIntake) {
		IntakeWheel1.set(-speedWheelIntake);
		IntakeWheel2.set(speedWheelIntake);
	}

	public void StopWheelIntake() {
		IntakeWheel1.stopMotor();
		IntakeWheel2.stopMotor();
	}
}