package org.usfirst.frc.team3807.robot.commands.autonomous;

import org.usfirst.frc.team3807.robot.commands.Drive;
import org.usfirst.frc.team3807.robot.commands.HaltRobot;

import edu.wpi.first.wpilibj.command.WaitCommand;

public class DriveForward extends Autonomous {
	
	
	public DriveForward(double time){
		addSequential(new Drive(0.6, 0.175	));//drives forward
		System.out.println("Time Length: " + time);
		addSequential(new WaitCommand(time));//waits 
		addSequential(new HaltRobot());//halts robot 
	}
	
	public DriveForward(double time, double speed, double turn) {
		addSequential(new Drive(speed,turn));
		addSequential(new WaitCommand(time));
		addSequential(new HaltRobot());
	}
	
	
}
