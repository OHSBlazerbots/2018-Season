package org.usfirst.frc.team3807.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

//RobotMap contains all of the port numbers for the motor controllers, input devices, and anything else that requires a port.
public class RobotMap {
	//ID's for the chassis motor controllers
	public static final int CHASSIS_RIGHT = 8;
	public static final int CHASSIS_LEFT = 4;
	
	//ID's for the WheelIntake motor controllers
	public static final int INTAKE_WHEEL_ONE = 1;
	public static final int INTAKE_WHEEL_TWO = 5;
	
	//ID's for the scissor lift
	public static final int SCISSOR_LIFT_MOTOR_A = -1;
	public static final int SCISSOR_LIFT_MOTOR_B = -1;
	public static final int SCISSOR_LIFT_MOTOR_C = -1;
	public static final int SCISSOR_LIFT_MOTOR_D = -1;

	//IDs for the joysticks
	public static final int XBOX_CONTROLLER = 0; 
	public static final int CODRIVER_JOYSTICK_PORT = -1;
	public static final int CODRIVER_JOYSTICK_PORT2= -1;
	public static final int JOYSTICK_PORT = 1;
	
	//Encoder Ports:
	public static int STRING_POTENT = 4;
	public static WPI_TalonSRX STRING_POT = new WPI_TalonSRX(CHASSIS_LEFT);
	
	//Hall Effect Sensor Ports
	public static int maxHallPort = 0;
	public static int minHallPort = 1;
	
	
	
	//Reference Values
	public static int MAX_POTENT_VALUE = 200;
	
	
}
