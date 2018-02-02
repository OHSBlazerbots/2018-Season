package org.usfirst.frc.team3807.robot.commands;

import org.usfirst.frc.team3807.robot.OI;
import org.usfirst.frc.team3807.robot.RobotMap;
import org.usfirst.frc.team3807.robot.subsystems.Chassis;
import org.usfirst.frc.team3807.robot.subsystems.ScissorLift;
import org.usfirst.frc.team3807.robot.subsystems.WheelIntake;

import edu.wpi.first.wpilibj.command.Command;

//CommandBase is the parent class for all other custom robot commands.
public abstract class CommandBase extends Command{
	public static OI oi;
	public static Chassis chassis;
	public static WheelIntake wheelIntake;
	public static ScissorLift scissorLift;
	
	public static void init(){
		chassis = new Chassis (RobotMap.CHASSIS_LEFT, RobotMap.CHASSIS_RIGHT);
		wheelIntake = new WheelIntake(RobotMap.INTAKE_WHEEL_ONE, RobotMap.INTAKE_WHEEL_TWO);
		scissorLift = new ScissorLift(RobotMap.SCISSOR_LIFT_MOTOR);
		
		
		oi = new OI(); //Always instantiate OI last.
	}
	
	public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }

}
