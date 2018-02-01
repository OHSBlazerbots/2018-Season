package org.usfirst.frc.team3807.robot.subsystems.scissorlift;

import org.usfirst.frc.team3807.robot.commands.CommandBase;

public class ReduceScissorlift extends CommandBase {

	double scissorliftSpeed;
	
	
	public ReduceScissorlift(double spd) {
		scissorliftSpeed = spd;
		requires(scissorLift);
	}
	
	public void initialize() {
		scissorLift.reduceScissorLift(scissorliftSpeed);
	}
	
	public void excecute() {
		
	}
	
	public void end() {
		
	}
	
	public boolean isFinished() {
		return true;
	}
	
}
