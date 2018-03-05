package org.usfirst.frc.team3807.robot.commands.autonomous;

import org.usfirst.frc.team3807.robot.commands.Drive;
import org.usfirst.frc.team3807.robot.commands.HaltRobot;

import edu.wpi.first.wpilibj.command.WaitCommand;

public class DriveForwardAndTurnRight extends Autonomous {

	public DriveForwardAndTurnRight(double time){
		addSequential(new Drive(0.0, 0.8	));//drives forward
		System.out.println("Time Length: " + time);
		addSequential(new WaitCommand(time));//waits
	//	addSequential(new WaitCommand(1));
		addSequential(new HaltRobot());//halts robot 
	}
	
}
