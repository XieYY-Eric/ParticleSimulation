package ParticleSimulation;

import javafx.animation.FillTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.animation.Transition;

public class PaneForLabel extends GridPane{
	private Label lbSpeed,lbXPostion,lbYPostion;
	private Label Speed,XPostion,YPostion;
	private Particle particle;
	private FillTransition animation;
	
	public PaneForLabel() {
		lbSpeed = new Label("Speed");
		lbXPostion = new Label("XPostion");
		lbYPostion = new Label("YPostion");
		Speed = new Label("?");
		XPostion = new Label("?");
		YPostion = new Label("?");
		Speed.setStyle("-fx-border-color: black");
		XPostion.setStyle("-fx-border-color: black");
		YPostion.setStyle("-fx-border-color: black");
		Speed.setPrefWidth(100);
		XPostion.setPrefWidth(100);
		YPostion.setPrefWidth(100);
		
		this.setPadding(new Insets(10,10,10,10));
		this.setStyle("-fx-border-color: black");
		this.add(lbXPostion, 0, 0);
		this.add(lbYPostion, 0, 1);
		this.add(lbSpeed, 0, 2);
		this.add(XPostion, 1, 0);
		this.add(YPostion, 1, 1);
		this.add(Speed, 1, 2);
		this.setHgap(50);
	}
	
	public void SetLabel(Particle p) {
		if(p == null) {
			Speed.setText("Dead");
			XPostion.setText("Dead");
			YPostion.setText("Dead");
			return;
		}
		particle = p;
		animation = new FillTransition(javafx.util.Duration.millis(1000), particle,Color.RED,Color.WHITE);
		animation.setCycleCount(1);
		double speedV = Math.round(particle.getSpeed()*100)/100;
		double Xpo = Math.round(particle.getX()*100)/100;
		double Ypo = Math.round(particle.getY()*100)/100;
		Speed.setText(Double.toString(speedV));
		XPostion.setText(Double.toString(Xpo));
		YPostion.setText(Double.toString(Ypo));
		SetColor(particle);
	}
	
	public void SetColor(Particle particle) {
		animation.play();
	}
}
