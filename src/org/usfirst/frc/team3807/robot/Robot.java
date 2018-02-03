package org.usfirst.frc.team3807.robot;

import org.usfirst.frc.team3807.robot.commands.CommandBase;
import org.usfirst.frc.team3807.robot.commands.autonomous.DoNothingAuto;
import org.usfirst.frc.team3807.robot.commands.autonomous.DriveForward;
import org.usfirst.frc.team3807.robot.controllers.GripPipeline;
import org.usfirst.frc.team3807.robot.controllers.TalonSpeedController;
<<<<<<< HEAD
import org.usfirst.frc.team3807.robot.subsystems.SensorBase;
=======
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
>>>>>>> bed1e6e21936eacfe11da558c6bf0184f28de071

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;

public class Robot extends IterativeRobot{

	//The Joystick and XboxController are for controlling the movement of the robot.
	private Joystick joystick = new Joystick(RobotMap.JOYSTICK_PORT);
	private XboxController xbox = new XboxController(RobotMap.XBOX_CONTROLLER);

	Command autonomousCommand;
	SendableChooser autoChooser;
	SensorBase sensorbase;
	//SendableChooser controlChooser;
	
	@Override
	public void robotInit(){

		CommandBase.init();
		sensorbase = new SensorBase();
		
		autoChooser = new SendableChooser();
		autoChooser.addDefault("DriveForward", new DriveForward());
		autoChooser.addDefault("DoNothingAuto",new DoNothingAuto());
		
		SmartDashboard.putData("Autonomous mode chooser", autoChooser);
		
		//controlChooser = new SendableChooser();
		//controlChooser.addDefault("", null);
		
		new Thread(() -> {
			UsbCamera camera = CameraServer.getInstance().startAutomaticCapture("cam0",0);
            camera.setResolution(320, 240);
            camera.setFPS(11);
            GripPipeline.process(Camera);
        }).start();
		
	}

	@Override
	public void autonomousInit(){

		autonomousCommand = (Command) autoChooser.getSelected();
	
		if(autonomousCommand !=null){
			autonomousCommand.start();
		}
	}

	@Override
	public void autonomousPeriodic(){
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit(){
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	@Override
	public void teleopPeriodic(){
		//sensorbase.sendAccelerometerValues();
		//sensorbase.sendPotentiometerValues();
		//sensorbase.sendPDPValues();
		//sensorbase.robotPrefTest();
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic(){

	}

	public void disablePeriodic(){
//		Scheduler.getInstance().run();
	}

}
