package org.usfirst.frc.team3807.robot;

import org.usfirst.frc.team3807.robot.commands.CommandBase;
import org.usfirst.frc.team3807.robot.commands.DriveWithJoystick;
import org.usfirst.frc.team3807.robot.commands.DriveWithLeftHandXbox;
import org.usfirst.frc.team3807.robot.commands.autonomous.DoNothingAuto;
import org.usfirst.frc.team3807.robot.commands.autonomous.DriveForward;
//import org.usfirst.frc.team3807.robot.controllers.vision.GripPipeline;
import org.usfirst.frc.team3807.robot.controllers.TalonSpeedController;
import org.usfirst.frc.team3807.robot.controllers.vision.GripPipeline;
//import org.usfirst.frc.team3807.robot.controllers.vision.VisionGetter;
import org.usfirst.frc.team3807.robot.subsystems.SensorBase;
import org.usfirst.frc.team3807.robot.subsystems.scissorlift.StopScissorlift;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.VisionPipeline;
import edu.wpi.first.wpilibj.vision.VisionRunner;
import edu.wpi.first.wpilibj.vision.VisionRunner.Listener;
import edu.wpi.first.wpilibj.vision.VisionThread;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Preferences;

public class Robot extends IterativeRobot {

	// The Joystick and XboxController are for controlling the movement of the
	// robot.
	private Joystick joystick = new Joystick(RobotMap.JOYSTICK_PORT);
	private XboxController xbox = new XboxController(RobotMap.XBOX_CONTROLLER);

	Command autonomousCommand;

	SendableChooser autoChooser;
	SendableChooser<String> driverControllerChooser;
	SensorBase sensorbase;
	// SendableChooser controlChooser;

	private static final int IMG_WIDTH = 320;
	private static final int IMG_HEIGHT = 240;

	private VisionThread visionThread;
	private double centerX = 0;

	private final Object imgLock = new Object();

	@Override
	public void robotInit() {
		System.out.println("in robotInit()");
		
		RobotValues.controlType = getControlScheme();
		
		CommandBase.init();
		sensorbase = new SensorBase();

		autoChooser = new SendableChooser();
		autoChooser.addObject("DriveForward", new DriveForward(sensorbase.getDriveForwardTime()));
		autoChooser.addDefault("DoNothingAuto", new DoNothingAuto());
		SmartDashboard.putData("Autonomous mode chooser", autoChooser);

		driverControllerChooser = new SendableChooser();
		// driverControllerChooser.addDefault("Controller - Left Hand",
		// (RobotValues.useController && !RobotValues.rightHandController));
		// driverControllerChooser.addDefault("Controller - Right Hand",
		// (RobotValues.useController && RobotValues.rightHandController));
		driverControllerChooser.addObject("Controller - Left Hand", "1");
		driverControllerChooser.addObject("Controller - Right Hand", "2");
		driverControllerChooser.addObject("Joystick", "3");
		driverControllerChooser.addDefault("Joystick", "3");

		SmartDashboard.putData("Driver Controller Chooser", driverControllerChooser);

		// controlChooser = new SendableChooser();
		// controlChooser.addDefault("", null);

		UsbCamera camera0 = CameraServer.getInstance().startAutomaticCapture("cam0", 0);
		camera0.setResolution(IMG_WIDTH, IMG_HEIGHT);

		SmartDashboard.putBoolean("test", false);
		VisionThread visionThread = new VisionThread(camera0, new GripPipeline(), pipeline -> {
			SmartDashboard.putBoolean("test", true);
			if (!pipeline.filterContoursOutput().isEmpty()) {
				Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
				synchronized (imgLock) {
					centerX = r.x + (r.width / 4);
					SmartDashboard.putNumber("CENTERX", centerX);
				}
			}
		});
		//visionThread.start();

		// //Test code for potentiometer
		// AnalogInput potAnalogIn;
		// AnalogPotentiometer stringPotentiometer;
		// double potValue;
		//
		//
		// potAnalogIn = new AnalogInput(1);
		// stringPotentiometer = new AnalogPotentiometer(potAnalogIn);
		//
		// potValue = stringPotentiometer.pidGet();
		//// SmartDashboard.putString("StringPotentiometerPosition",
		// String.format("%.4f", potValue*1000));
		// SmartDashboard.putString("StringPotentiometerPosition", ""+ potValue*1000);
		//
		System.out.println("out robotInit()");
		
	}

	@Override
	public void autonomousInit() {
		autonomousCommand = (Command) autoChooser.getSelected();
		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}
	
	public int getControlScheme() {
		Preferences prefs = Preferences.getInstance();
		return(prefs.getInt("CONTROL", 3));
	}

	@Override
	public void teleopInit() {
		System.out.println("in teleopInitCommand()");
		//RobotValues.controlType = getControlScheme();
		
		System.out.println("RobotValues.controlType="+RobotValues.controlType);
		CommandBase.chassis.initDefaultCommand();
		// SensorBase.getRobotPreferences();
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		
		System.out.println("out teleopInitCommand()");

	}

	@Override
	public void teleopPeriodic() {
		// sensorbase.sendAccelerometerValues();
		// sensorbase.sendPotentiometerValues();

		// sensorbase.sendPDPValues();
		// sensorbase.robotPrefTest();
		// WPI_TalonSRX potent= RobotMap.STRING_POT;
		// SmartDashboard.putString("StringPotentiometerPosition", ""+ potent.get());
		sensorbase.sendHallEffectValues();
//		if (sensorbase.fullyExtended() && RobotValues.extending) {
//			new StopScissorlift().initialize();
//		}
//
//		if (sensorbase.fullyRetracted() && RobotValues.retracting) {
//			new StopScissorlift().initialize();
//		}
		CommandBase.chassis.updateEncoder();
		

		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {

	}

	public void disablePeriodic() {
		// Scheduler.getInstance().run();
	}

}
