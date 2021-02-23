package ParticleSimulation;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

public class BackGroundControl {
	private ParticlePane particlepane;
	private CheckBox cbCollision;
	private CheckBox cbEnergy;
	private CheckBox cbDestroy;
	
	public BackGroundControl(ParticlePane obj) {
		particlepane = obj;
		cbCollision = new CheckBox("Collision");
		cbEnergy = new CheckBox("Energy");
		cbDestroy = new CheckBox("Destroy");
	}
	
	public VBox getPane() throws Exception {
		if(particlepane == null) {
			throw new Exception();
		}
		VBox pane = new VBox(50);
		//styling
		pane.setStyle("-fx-border-color: green");
		pane.setPadding(new Insets(10,10,10,10));
		pane.setAlignment(Pos.CENTER);
		//set
		pane.getChildren().addAll(cbCollision,cbEnergy,cbDestroy);
		cbCollision.setOnAction( e-> handler());
		cbEnergy.setOnAction( e-> handler());
		cbDestroy.setOnAction( e-> handler());
		return pane;
	}
	
	protected void handler() {
		if(cbCollision.isSelected()) {
			particlepane.setCollisionToggle(true);
		}else {
			particlepane.setCollisionToggle(false);
		}
		
		if(cbDestroy.isSelected() && cbCollision.isSelected()) {
			particlepane.setDestoryToggle(true);
		}else {
			particlepane.setDestoryToggle(false);
		}
		
		if(cbEnergy.isSelected()) {
			particlepane.setEnergyToggle(true);
		}else {
			particlepane.setEnergyToggle(false);
		}
	}
}
