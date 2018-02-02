package org.usfirst.frc.team3807.robot.subsystems.scissorlift;

import org.usfirst.frc.team3807.robot.commands.CommandBase;

public class StopScissorlift extends CommandBase {

	double scissorliftSpeed;
	
	
	public StopScissorlift() {
		requires(scissorLift);
	}
	
	public void initialize() {
		scissorLift.stopScissorLift();
	}
	
	public void excecute() {
		
	}
	
	public void end() {
		
	}
	
	public boolean isFinished() {
		return true;
	}
	
}
