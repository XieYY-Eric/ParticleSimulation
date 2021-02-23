package ParticleSimulation;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Test extends Application{
	private double height = 800;
	private double width = 1200;
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub

		ParticleSimulator pane = new ParticleSimulator();
		stage.setTitle("Particle Simulator");
		stage.setScene(new Scene(pane,width,height));
		stage.show();
	}
	
}
