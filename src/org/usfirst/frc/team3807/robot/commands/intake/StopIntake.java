package org.usfirst.frc.team3807.robot.commands.intake;

import org.usfirst.frc.team3807.robot.commands.CommandBase;

public class StopIntake extends CommandBase {

	public StopIntake() {
		requires(wheelIntake);

	}

	protected void initialize() {
		wheelIntake.StopWheelIntake();
	}

	protected void execute() {

	}

	protected void end() {

	}

	protected void interruted() {

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
