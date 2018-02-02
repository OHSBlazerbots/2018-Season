package org.usfirst.frc.team3807.robot.commands.intake;

import org.usfirst.frc.team3807.robot.commands.CommandBase;

public class ReverseIntake extends CommandBase {
	double speed;

	public ReverseIntake(double spd) {
	speed= spd;
	requires(wheelIntake);

}

	protected void initialize() {
		wheelIntake.RunWheelIntake(-speed);

	}

	protected void execute() {

	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
	}

	protected void interrupted() {

	}
}