package ParticleSimulation;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ParticleSimulator extends BorderPane{
	private ParticlePane particlePane;
	private ControlPane controlPane;	
	private StaticsTrackPane staticsPane;
	private BackGroundControl backGroundControl;
	private ArrayList<Particle> particles;
	private ParticlesData particlesData = new ParticlesData();
	
	public ParticleSimulator() throws Exception {
		// TODO Auto-generated constructor stub
		particles = particlesData.getParticles();
		staticsPane = new StaticsTrackPane(particles);
		particlePane = new ParticlePane(particles,staticsPane);	
		controlPane = new ControlPane(particlePane);
		backGroundControl = new BackGroundControl(particlePane);
		
		this.setCenter(particlePane);
		this.setBottom(controlPane);
		this.setLeft(staticsPane.GetPane());
		this.setRight(backGroundControl.getPane());
		
	}
}
