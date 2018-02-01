package org.usfirst.frc.team3807.robot.subsystems;

//import org.usfirst.frc.team3807.robot.commands.DriveWithJoystick;

import org.usfirst.frc.team3807.robot.commands.DriveWithJoystick;
import org.usfirst.frc.team3807.robot.commands.DriveWithXbox;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Subsystem;

/*
 * CTRE Talon Reference Deprecated, see Latest
 */
public class Chassis extends Subsystem {
	
	private boolean useXbox = true; //Set to true to enable control with the xbox controller
	private boolean xboxRightHand = false; //Set to true to change movement controls to the right joystick
	
	private double joystickTurnSpeed = 0.75; //Change this to change the speed of the robot in relation to the control devices
	private double joystickMoveSpeed = 0.75; //Change this to change the speed of the robot in relation to the control devices
	private double xboxTurnSpeed = 0.7; //Change this to change the speed of the robot in relation to the control devices
	private double xboxMoveSpeed = 0.7; //Change this to change the speed of the robot in relation to the control devices

	WPI_TalonSRX leftMotor;  		/* device IDs here (1 of 2) */
	WPI_TalonSRX rightMotor;

	public DifferentialDrive drive;

	public Chassis(int L, int R) {
		if (L != -1 && R != -1) {

			leftMotor = new WPI_TalonSRX(L);
			rightMotor = new WPI_TalonSRX(R);

			// Make an actual object for speed controller
			
			drive = new DifferentialDrive(leftMotor, rightMotor); //Call this method inside the if statement to double-check that Talon ports were assigned.
			drive.setSafetyEnabled(false);
		}
	}

	public void drive(double speed, double turn) {
		drive.arcadeDrive(speed, turn);
	}

	public void driveWithJoystick(Joystick joystick) {
		//Get Joystick Input
		double turn = joystick.getZ() * joystickTurnSpeed;
		double move = joystick.getY() * joystickMoveSpeed;
		
		//Share the input with the Driver
		SmartDashboard.putNumber("turn", turn);
		SmartDashboard.putNumber("move", move);
		
		//Move the robot
		drive(move, turn);
	}

	public void driveWithJoystickInverse(Joystick joystick) {
		//Get Joystick Input
		double turn = joystick.getZ() * joystickTurnSpeed;
		double move = joystick.getY() * joystickMoveSpeed;
		
		//Share the input with the Driver
		SmartDashboard.putNumber("turn", turn);
		SmartDashboard.putNumber("move", move);
		
		//Move the robot
		drive(-move, -turn);
	}

	public void driveWithXbox(XboxController controller){
		//Get the input
		double turn;
		double move;
		
		//Forward, Reverse, left joystick. Right, Left, right joystick. 
		//Determine if we are using the left side or the right side of the controller.
		/* ######### COMMENTED OUT TO TEST ALL-ONE CODE
		  if(xboxRightHand){
		 
			 turn = controller.getX(GenericHID.Hand.kRight) * xboxTurnSpeed;
			 move = controller.getY(GenericHID.Hand.kRight) * xboxMoveSpeed;
		}else{
			 turn = controller.getX(GenericHID.Hand.kRight) * xboxTurnSpeed;
			 move = controller.getY(GenericHID.Hand.kLeft) * xboxMoveSpeed;
		}
		*/
		//Forward, Reverse, Left, Right, right joystick.
		turn = controller.getX(GenericHID.Hand.kRight) * xboxTurnSpeed;
		move = controller.getY(GenericHID.Hand.kRight) * xboxMoveSpeed;
		
		drive(move, turn);
	}


	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		if(useXbox){
			setDefaultCommand(new DriveWithXbox()); //If we are using the xbox controller, call DriveWithXbox()
		}else{
			setDefaultCommand(new DriveWithJoystick()); //If we are using the joystick, call DriveWithJoystick()
		}
	}

	public void Halt() {
		//Stop the chassis from moving
		leftMotor.set(0);
		rightMotor.set(0);
	}



}
