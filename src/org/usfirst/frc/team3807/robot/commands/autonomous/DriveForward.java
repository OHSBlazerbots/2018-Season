package org.usfirst.frc.team3807.robot.commands.autonomous;

import org.usfirst.frc.team3807.robot.commands.Drive;
import org.usfirst.frc.team3807.robot.commands.HaltRobot;

import edu.wpi.first.wpilibj.command.WaitCommand;

public class DriveForward extends Autonomous {
	public DriveForward(){
		addSequential(new Drive(.6, 0.175	));
		addSequential(new WaitCommand(6.0));
		addSequential(new HaltRobot());
	}
}
