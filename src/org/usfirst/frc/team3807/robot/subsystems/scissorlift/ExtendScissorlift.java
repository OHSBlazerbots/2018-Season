package org.usfirst.frc.team3807.robot.subsystems.scissorlift;

import org.usfirst.frc.team3807.robot.commands.CommandBase;

public class ExtendScissorlift extends CommandBase {

	double scissorliftSpeed;	
	
	public ExtendScissorlift(double spd) {
		scissorliftSpeed = spd;
		requires(scissorLift);
	}
	
	public void initialize() {
		scissorLift.extendScissorLift(scissorliftSpeed);
	}
	
	public void excecute() {
		
	}
	
	public void end() {
		
	}
	
	public boolean isFinished() {
		return true;
	}
	
}
