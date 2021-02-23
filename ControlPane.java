package ParticleSimulation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ControlPane extends HBox{
	
	private Button play,stop,increase,decrease;
	private ParticlePane particlePane;
	//private ParticlePane particlePane;

	public ControlPane(ParticlePane particlePaneObj) {
		if(particlePaneObj == null) {
			System.out.println("Null pointer");
		}else {
			particlePane = particlePaneObj;
			System.out.println("Creating Button");
			play = new Button("Play");
			stop = new Button("Stop");
			increase = new Button("Increase");
			decrease = new Button("Decrease");
			
			//System.out.println("Styling");
			this.getChildren().add(play);
			this.getChildren().add(stop);
			this.getChildren().add(increase);
			this.getChildren().add(decrease);
			this.setPadding(new Insets(10,10,10,10));
			this.setStyle("-fx-border-color: green");
			this.setAlignment(Pos.CENTER);
			this.setSpacing(30);
			//setControl
			play.setOnAction(e -> particlePane.play());
			stop.setOnAction(e -> particlePane.pause());
			increase.setOnAction(e -> particlePane.increaseSpeed());
			decrease.setOnAction(e -> particlePane.decreaseSpeed());
		}
	}	
	
}
