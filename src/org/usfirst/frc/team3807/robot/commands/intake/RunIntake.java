package org.usfirst.frc.team3807.robot.commands.intake;

import org.usfirst.frc.team3807.robot.commands.CommandBase;

public class RunIntake extends CommandBase {
	double speed;

	public RunIntake(double spd1) {

		speed = spd1;
		requires(wheelIntake);

	}

	protected void initialize() {//runs wheel intake at set speed 
		wheelIntake.RunWheelIntake(speed);
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
