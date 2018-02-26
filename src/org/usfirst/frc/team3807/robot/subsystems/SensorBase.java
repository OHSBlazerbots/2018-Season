package org.usfirst.frc.team3807.robot.subsystems;

import org.usfirst.frc.team3807.robot.RobotMap;
import org.usfirst.frc.team3807.robot.RobotValues;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SensorBase extends Subsystem {

	// RoboRios Built in Accelerometer. This is reportedly inaccurate
	Accelerometer internalAccelerometer;
	double xAcceleration;
	double yAcceleration;
	double zAcceleration;

	// String Potentiometer
	AnalogInput potAnalogIn;
	AnalogPotentiometer stringPotentiometer;
	double potValue;

	// Power Distribution Panel Sensors
	PowerDistributionPanel pdp;

	// Hall Effect Sensors
	 DigitalInput maxHallInput;
	 DigitalInput minHallInput;

	// Robot Preferences
	Preferences prefs;

	public SensorBase() {
		prefs = Preferences.getInstance();
		
		pdp = new PowerDistributionPanel();

		// int port = (int) SmartDashboard.getNumber("PROT", 6);
		int port = 0;
		internalAccelerometer = new BuiltInAccelerometer(Accelerometer.Range.k4G);
		potAnalogIn = new AnalogInput(port);
		stringPotentiometer = new AnalogPotentiometer(potAnalogIn);
		// pdp = new PowerDistributionPanel();

		 maxHallInput = new DigitalInput(RobotMap.maxHallPort);
		 minHallInput = new DigitalInput(RobotMap.minHallPort);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

	public void sendAccelerometerValues() {
		xAcceleration = internalAccelerometer.getX();
		yAcceleration = internalAccelerometer.getY();
		zAcceleration = internalAccelerometer.getZ();
		SmartDashboard.putString("InternalAccelerometerX", String.format("%.1f", xAcceleration * 1000));
		SmartDashboard.putString("InternalAccelerometerY", String.format("%.2f", yAcceleration * 1000));
		SmartDashboard.putString("InternalAccelerometerZ", String.format("%.4f", zAcceleration * 1000));
	}

	// commented out for testing in robot class
	public void sendPotentiometerValues() {
		potValue = stringPotentiometer.get();
		SmartDashboard.putString("StringPotentiometerPosition", String.format("%.4f", potValue * 1000));

		System.out.println("STRING POTENTIOMETER INPUT: " + potValue);
	}

	public boolean withinPotentiometerLimit() {

		if (0.5 < potValue && potValue < 0.9) {// Check if the potentiometer value is in the good range
			return true;
		}

		return false; // Return false to keep the code running.
	}
	
	public boolean fullyExtended() {
		return !maxHallInput.get();
	}
	public boolean fullyRetracted() {
		return !minHallInput.get();
	}
	
	public boolean extending() {
		return false;
	}
	public boolean retracting() {
		return false;
	}
	
	public void sendHallEffectValues() {
		SmartDashboard.putBoolean("HallLeft", !maxHallInput.get());
		SmartDashboard.putBoolean("HallRight", !minHallInput.get());
	}

	public void robotPrefTest() throws NullPointerException {
		Boolean boals = prefs.getBoolean("BOOLEANA", true);
		SmartDashboard.putBoolean("BOAJFD", boals);
		int port = prefs.getInt("PROT", 1);
		SmartDashboard.putNumber("PORT", port);
	}
	
	public double getDriveForwardTime() {
		System.out.println("DriveForwardTime Ran");
		return prefs.getDouble("DriveForwardTime", 3);
	}

}
