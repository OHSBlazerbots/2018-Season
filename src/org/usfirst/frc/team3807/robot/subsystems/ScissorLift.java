package org.usfirst.frc.team3807.robot.subsystems;

import org.usfirst.frc.team3807.robot.RobotValues;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class ScissorLift extends Subsystem {
	WPI_TalonSRX ScissorLiftMotor1a;
	WPI_TalonSRX ScissorLiftMotor1b;
	WPI_TalonSRX ScissorLiftMotor2a;
	WPI_TalonSRX ScissorLiftMotor2b;

//	ScissorLiftMotor1.
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}

	public ScissorLift(int sideAMotor1, int sideAMotor2, int sideBMotor1, int sideBMotor2) {
		if (sideAMotor1 != -1 && sideAMotor2 != -1 && sideBMotor1 != -1 && sideBMotor2 != -1) {
			ScissorLiftMotor1a = new WPI_TalonSRX(sideAMotor1);
			ScissorLiftMotor1b = new WPI_TalonSRX(sideAMotor2);
			ScissorLiftMotor2a = new WPI_TalonSRX(sideBMotor1);
			ScissorLiftMotor2b = new WPI_TalonSRX(sideBMotor2);
		}
	}

	public void extendScissorLift(double speedWheelIntake) {
//		ScissorLiftMotor1a.set(speedWheelIntake);
//		ScissorLiftMotor1b.set(speedWheelIntake);
//		ScissorLiftMotor2a.set(-speedWheelIntake);
//		ScissorLiftMotor2b.set(-speedWheelIntake);
		
		RobotValues.extending = true;
		RobotValues.retracting = false;
	}

	public void reduceScissorLift(double speedWheelIntake) {
//		ScissorLiftMotor1a.set(-speedWheelIntake);
//		ScissorLiftMotor1b.set(-speedWheelIntake);
//		ScissorLiftMotor2a.set(speedWheelIntake);
//		ScissorLiftMotor2b.set(speedWheelIntake);
		RobotValues.extending = false;
		RobotValues.retracting = true;
	}

	public void stopScissorLift() {
//		ScissorLiftMotor1a.stopMotor();
//		ScissorLiftMotor1b.stopMotor();
//		ScissorLiftMotor2a.stopMotor();
//		ScissorLiftMotor2b.stopMotor();
		
		RobotValues.extending = false;
		RobotValues.retracting = false;
	}
}