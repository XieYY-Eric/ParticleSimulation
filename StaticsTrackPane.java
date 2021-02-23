package ParticleSimulation;

import java.util.ArrayList;

import com.sun.prism.paint.Color;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class StaticsTrackPane{
	public PaneForList particlesListPane;
	public PaneForLabel paneForLabel;
	private VBox pane;
	private ArrayList<Particle> particles;
	
	
	public StaticsTrackPane(ArrayList<Particle> particlesArraySource) {
		particles = particlesArraySource;
		paneForLabel = new PaneForLabel();
		particlesListPane = new PaneForList(particles, paneForLabel);
		pane = new VBox(20);
	}
	
	public VBox GetPane() {
		pane.getChildren().add(particlesListPane);
		pane.getChildren().add(paneForLabel);
		pane.setAlignment(Pos.CENTER);
		pane.setStyle("-fx-border-color: green");
		return pane;
	}
	
}
