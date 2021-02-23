package ParticleSimulation;

import java.util.ArrayList;

import com.sun.prism.paint.Color;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

public class PaneForList extends Pane{
	private ListView<String> particlesListView;
	private ArrayList<String> particlesListId;
	private ArrayList<Particle> particles;
	private PaneForLabel paneForLabel;
	private Timeline animation;

	
	public PaneForList(ArrayList<Particle> obj, PaneForLabel obj2) {
		// TODO Auto-generated constructor stub
		animation = new Timeline(new KeyFrame(javafx.util.Duration.millis(50), e-> UpdateTheLabel()));
		animation.setCycleCount(Timeline.INDEFINITE);
		this.particles = obj;
		particlesListId = GeneratedList();
		paneForLabel = obj2;
		particlesListView = new ListView<>(FXCollections.observableArrayList(particlesListId));
		particlesListView.getSelectionModel().selectedItemProperty().addListener(
				ov -> {
					int index = particlesListView.getSelectionModel().getSelectedIndex();
					try {
						paneForLabel.SetLabel(particles.get(index));
						animation.play();
					}catch(Exception e) {
						paneForLabel.SetLabel(null);
					}	
				}
				);
		this.getChildren().add(particlesListView);

	}
	
	
	public ArrayList<String> GeneratedList() {
		ArrayList<String> result = new ArrayList<String>();
		for(int i=0;i<this.particles.size();i++) {
			result.add("Particle " + Integer.toString(i+1));
		}
		return result;
	}
	
	public void UpdateTheLabel() {
		int index = particlesListView.getSelectionModel().getSelectedIndex();
		try {
			Particle temp = particles.get(index);
			paneForLabel.SetLabel(temp);
		}catch(Exception e) {
			paneForLabel.SetLabel(null);
		}
	}


}
