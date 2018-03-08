package org.usfirst.frc.team3807.robot;

import org.usfirst.frc.team3807.robot.commands.intake.*;

import org.usfirst.frc.team3807.robot.subsystems.scissorlift.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

//OI is used to obtain Operator Input.
public class OI {
	private static final Joystick joystick = new Joystick(RobotMap.JOYSTICK_PORT);
	private static final Joystick coDriver1 = new Joystick(RobotMap.CODRIVER_JOYSTICK_PORT);
	private static final Joystick coDriver2 = new Joystick(RobotMap.CODRIVER_JOYSTICK_PORT2);
	private static final XboxController xBoxCoDriver = new XboxController(RobotMap.XBOX_CONTROLLER);
	
	private final JoystickButton runWheelIntakeButton;
	private final JoystickButton runWheelIntakeReverseButton;
	private final JoystickButton stopWheelIntakeButton;

	private final JoystickButton runScissorLiftButton;
	private final JoystickButton runScissorLiftReverseButton;
	private final JoystickButton stopScissorLiftButton;

	public OI(){
		runWheelIntakeButton = new JoystickButton(joystick, 1);//runs wheel intake forward when button one is pressed 
		runWheelIntakeButton.whenPressed(new RunIntake(0.5));
		
		runWheelIntakeReverseButton = new JoystickButton(joystick, 2);//runs wheel intake backwards when button 2 is pressed 
		runWheelIntakeReverseButton.whenPressed(new ReverseIntake(0.5));
		
		stopWheelIntakeButton = new JoystickButton(joystick, 3);//stops wheel intake when button 3 is pressed 
		stopWheelIntakeButton.whenPressed(new StopIntake());
		
		
		runScissorLiftButton = new JoystickButton(joystick, 9);//scissor lift moves up when button 9 is pressed 
		runScissorLiftButton.whenPressed(new ExtendScissorlift(0.5));
		
		runScissorLiftReverseButton = new JoystickButton(joystick, 10);//brings scissor lift back down when button 10 is pressed 
		runScissorLiftReverseButton.whenPressed(new ReduceScissorlift(0.5));
		
		stopScissorLiftButton = new JoystickButton(joystick, 11);//stops scissor lift when button 11 is pressed 
		stopScissorLiftButton.whenPressed(new StopScissorlift());
		
	}

	public static Joystick getJoystick(){//gets joy stick
		return joystick;
	}

	public static Joystick getCoDriverJoystick1(){//gets codriver joy stick 
		return coDriver1;
	}

	public static Joystick getCoDriverJoystick2(){//gets codriver2 joy stick 
		return coDriver2;
	}

	public static XboxController getXboxController(){//gets Xbox controller 
		return xBoxCoDriver;
	}
}
