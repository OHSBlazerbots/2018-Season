package org.usfirst.frc.team3807.robot.subsystems;

import org.usfirst.frc.team3807.robot.RobotMap;

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
	// PowerDistributionPanel pdp;
	double totalPDPCurrent;

	// Hall Effect Sensors
	 DigitalInput maxHall;
	 DigitalInput minHall;

	// Robot Preferences
	Preferences prefs;

	public SensorBase() {
		prefs = Preferences.getInstance();

		// int port = (int) SmartDashboard.getNumber("PROT", 6);
		int port = 0;
		internalAccelerometer = new BuiltInAccelerometer(Accelerometer.Range.k4G);
		potAnalogIn = new AnalogInput(port);
		stringPotentiometer = new AnalogPotentiometer(potAnalogIn);
		// pdp = new PowerDistributionPanel();

		 maxHall = new DigitalInput(RobotMap.maxHallPort);
		 minHall = new DigitalInput(RobotMap.minHallPort);
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
	
	//Normally returns true
	public boolean getMaxHall() {
		return maxHall.get();
	}
	
	//Normally returns true
	public boolean getMinHall() {
		return minHall.get(); 
	}
	
	/*
	 * public void sendPDPValues() { totalPDPCurrent = pdp.getTotalCurrent();
	 * SmartDashboard.putString("TotalPDPCurrent", String.format("%.4f Amps",
	 * totalPDPCurrent)); SmartDashboard.putString("PDPTOTAMP",
	 * pdp.getTotalCurrent() + ""); }
	 */

	public void robotPrefTest() throws NullPointerException {
		Boolean boals = prefs.getBoolean("BOOLEANA", true);
		SmartDashboard.putBoolean("BOAJFD", boals);
		int port = prefs.getInt("PROT", 1);
		SmartDashboard.putNumber("PORT", port);
	}

}
