package org.usfirst.frc.team3807.robot.subsystems;

import org.usfirst.frc.team3807.robot.RobotMap;

import org.usfirst.frc.team3807.robot.RobotValues;

//import org.usfirst.frc.team3807.robot.commands.DriveWithJoystick;

import org.usfirst.frc.team3807.robot.commands.DriveWithJoystick;
import org.usfirst.frc.team3807.robot.commands.DriveWithLeftHandXbox;
import org.usfirst.frc.team3807.robot.commands.DriveWithRightHandXbox;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Subsystem;

/*
 * CTRE Talon Reference Deprecated, see Latest
 */
public class Chassis extends Subsystem {

	private double joystickTurnSpeed = 0.75; // Change this to change the speed of the robot in relation to the control
												// devices
	private double joystickMoveSpeed = 0.75; // Change this to change the speed of the robot in relation to the control
												// devices
	private double xboxTurnSpeed = 0.7; // Change this to change the speed of the robot in relation to the control
										// devices
	private double xboxMoveSpeed = -1.0; // Change this to change the speed of the robot in relation to the control
										// devices

	WPI_TalonSRX leftMotor; /* device IDs here (1 of 2) */
	WPI_TalonSRX rightMotor;

	public DifferentialDrive drive;

	public Chassis(int L, int R) {
		if (L != -1 && R != -1) {

			leftMotor = new WPI_TalonSRX(L);
			rightMotor = new WPI_TalonSRX(R);

			// Make an actual object for speed controller

			drive = new DifferentialDrive(leftMotor, rightMotor); // Call this method inside the if statement to
																	// double-check that Talon ports were assigned.
			drive.setSafetyEnabled(false);
			leftMotor.getSensorCollection().setQuadraturePosition(0, 0);
			rightMotor.getSensorCollection().setQuadraturePosition(0, 0);
		}
	}

	public void drive(double speed, double turn) {
		drive.arcadeDrive(speed, turn);
	}

	public void driveWithJoystick(Joystick joystick) {
		// Get Joystick Input
		double turn = joystick.getZ() * joystickTurnSpeed;
		double move = joystick.getY() * joystickMoveSpeed;

		// Share the input with the Driver
		SmartDashboard.putNumber("turn", turn);
		SmartDashboard.putNumber("move", move);

		// Move the robot
		drive(move, turn);
	}

	public void driveWithJoystickInverse(Joystick joystick) {
		// Get Joystick Input
		double turn = joystick.getZ() * joystickTurnSpeed;
		double move = joystick.getY() * joystickMoveSpeed;

		// Share the input with the Driver
		SmartDashboard.putNumber("turn", turn);
		SmartDashboard.putNumber("move", move);

		// Move the robot
		drive(-move, -turn);
	}
	
	public void driveWithRightHandXbox(XboxController controller) {
		double turn, move;
		turn = controller.getX(GenericHID.Hand.kRight) * xboxTurnSpeed;
		move = controller.getY(GenericHID.Hand.kRight) * xboxMoveSpeed;
		
		drive(move, turn);
	}

	public void driveWithLeftHandXbox(XboxController controller) {
		// Get the input
		double turn;
		double move;
    	turn = controller.getX(GenericHID.Hand.kLeft) * xboxTurnSpeed;
		move = controller.getY(GenericHID.Hand.kLeft) * xboxMoveSpeed;

		drive(move, turn);
	}
	
	public int getControlScheme() {
		return RobotValues.controlType;
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		switch(getControlScheme()) {
		case 1:
			setDefaultCommand(new DriveWithLeftHandXbox());
			System.out.println("LEFT XBOX");
			break;
		case 2:
			setDefaultCommand(new DriveWithRightHandXbox());
			System.out.println("RIGHT XBOX");
			break;
		default:
			setDefaultCommand(new DriveWithJoystick());
			System.out.println("JOYSTICK");
			break;

		}
	}
	

	public void Halt() {
		// Stop the chassis from moving
		leftMotor.set(0);
		rightMotor.set(0);
	}
	public void updateEncoder() {
		double leftQuadPos = leftMotor.getSensorCollection().getQuadraturePosition();
		double rightQuadPos = rightMotor.getSensorCollection().getQuadraturePosition();
		SmartDashboard.putNumber("Left quadposition", leftQuadPos);
		SmartDashboard.putNumber("left quadvelocity", leftMotor.getSensorCollection().getQuadratureVelocity());
		double leftPos = leftQuadPos * 18.85/ (12 * 1450);
		double rightPos = rightQuadPos * 18.85/ (12 * 1450);
		SmartDashboard.putNumber("left actual pos", leftPos);
		SmartDashboard.putNumber("right actual pos", rightPos);
		SmartDashboard.putNumber("Right quadposition", rightQuadPos);
		SmartDashboard.putNumber("Right quadvelocity", rightMotor.getSensorCollection().getQuadratureVelocity());
	}

}
