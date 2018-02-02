package org.usfirst.frc.team3807.robot.controllers;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.SpeedController;

public class TalonSpeedController implements SpeedController {
	
	private TalonSRX talon;
	private double talonSpeed;
	private boolean inverted;
	
	public TalonSpeedController(TalonSRX talon)
	{
		this.talon = talon;
		talonSpeed = 0;
		inverted = false;
	}

	@Override
	public void pidWrite(double arg0) {
		// TODO Auto-generated method stub
		//Needs defining
	}

	@Override
	public void disable() {
		// TODO Auto-generated method stub
		//Needs defining
	}

	@Override
	public double get() {
		// TODO Auto-generated method stub
		return talonSpeed;
	}

	@Override
	public boolean getInverted() {
		// TODO Auto-generated method stub
		return inverted;
	}

	@Override
	public void set(double arg0) {
		// TODO Auto-generated method stub
		talonSpeed = arg0;
		talon.set(ControlMode.Velocity, arg0);
		
	}

	@Override
	public void setInverted(boolean arg0) {
		// TODO Auto-generated method stub
		inverted = arg0;
		talon.setInverted(arg0);
		
	}

	@Override
	public void stopMotor() {
		// TODO Auto-generated method stub
		talon.set(ControlMode.Velocity, 0);
		talon.setInverted(false);
		
	}

}
