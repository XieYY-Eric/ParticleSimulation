package ParticleSimulation;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;

public class ParticlePane extends Pane{
	private final double radius = 20;
	private Timeline animation;
	private int numberOfParticles;
	private ArrayList<Particle> particle;
	public double width = 800, height = 400;
	//three setting
	private boolean collisionToggle = false;
	private boolean energyToggle = false;
	private boolean destoryToggle = false;
	private FileInputStream inputstream;
	private Image backgroundImage;
	public ParticlePane() throws IOException {
		//initial
		inputstream = new FileInputStream("img/space.jpg");
		backgroundImage = new Image(inputstream);
		particle = new ArrayList<Particle>();
		for(int i=0;i<numberOfParticles;i++) {
			this.particle.add(new Particle());
			this.getChildren().add(particle.get(i));
		}
		
		for (int i = 0; i < numberOfParticles; i++) {
			this.UpdateParticle(particle.get(i));
		}
		animation = new Timeline(new KeyFrame(javafx.util.Duration.millis(50), e-> moveBall()));
		animation.setCycleCount(Timeline.INDEFINITE);
		BackgroundImage myBI= new BackgroundImage(backgroundImage,
		        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
		//then you set to your node
		this.setBackground(new Background(myBI));
	}
	
	public ParticlePane(ArrayList<Particle> obj, StaticsTrackPane obj2) throws IOException {
		inputstream = new FileInputStream("img/space.jpg");
		backgroundImage = new Image(inputstream);
		particle = obj;
		numberOfParticles = particle.size();
		for(int i=0;i<numberOfParticles;i++) {
		//	this.particle.add(new Particle());
			this.getChildren().add(particle.get(i));
		}
		for (int i = 0; i < numberOfParticles; i++) {
			this.UpdateParticle(particle.get(i));
		}
		animation = new Timeline(new KeyFrame(javafx.util.Duration.millis(50), e-> moveBall()));
		animation.setCycleCount(Timeline.INDEFINITE);
		BackgroundImage myBI= new BackgroundImage(backgroundImage,
		        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
		this.setBackground(new Background(myBI));
	}
	
	public void play() {
		animation.play();
	}	
	public void pause() {
		animation.pause();
	}
	public void increaseSpeed() {
		animation.setRate(animation.getRate() + 0.1);
	}
	public void decreaseSpeed() {
		animation.setRate( animation.getRate() > 0?  animation.getRate() - 0.1 : 0);
	}
	
	protected void moveBall() {
		//hit wall, bounce back
		for(int i=0;i<numberOfParticles;i++) {
			if(particle.get(i).getX() < radius || particle.get(i).getX() > getWidth() -radius ) {
				particle.get(i).setDx(particle.get(i).getDx()*-1);
			}
			if(particle.get(i).getY() < radius || particle.get(i).getY() > getHeight() -radius ) {
				particle.get(i).setDy(particle.get(i).getDy()*-1);
			}
		}
		
		//interaction
		if(this.collisionToggle) {
			for(int i=0;i<numberOfParticles -1;++i) {
				for(int j=i+1;j<numberOfParticles;++j) {			
					//if they are in contact, interact
					if(particle.get(i).contacts(particle.get(j))) {
						collided(particle.get(i),particle.get(j));
					}
					//else do nothing
				}
			}
		}

		//update new position
		for(int i=0;i<numberOfParticles;i++) {
			double newX = particle.get(i).getX() + particle.get(i).getDx();
			double newY = particle.get(i).getY() + particle.get(i).getDy();
			particle.get(i).setX(newX);
			particle.get(i).setY(newY);
			particle.get(i).setCenterX(newX);
			particle.get(i).setCenterY(newY);
		}
	}
		
	public boolean isCollisionToggle() {
		return collisionToggle;
	}



	public void setCollisionToggle(boolean collisionToggle) {
		this.collisionToggle = collisionToggle;
	}



	public boolean isEnergyToggle() {
		return energyToggle;
	}



	public void setEnergyToggle(boolean energyToggle) {
		this.energyToggle = energyToggle;
	}



	private void collided(Particle first,Particle second) {
		if(energyToggle) {
			first.energyDisplay();
			second.energyDisplay();
		}
		if(this.destoryToggle) {
			this.particle.remove(first);
			this.particle.remove(second);
			this.getChildren().remove(first);
			this.getChildren().remove(second);
			this.numberOfParticles -=2;
			return;
		}
		double firstVelocityX = first.getDx(),firstVelocityY = first.getDy();
		double secondVelocityX = second.getDx(),secondVelocityY = second.getDy();
		double ydifference = Math.abs(second.getY() - first.getY());
		double xdifference = Math.abs(second.getX() - first.getX());
		double angle = Math.atan(ydifference/xdifference);
		double firstxRes = firstVelocityX/Math.cos(angle);
		double secondxRes = secondVelocityX/Math.cos(angle);
		double firstyRes = firstVelocityY/Math.sin(angle);
		double secondyRes = secondVelocityY/Math.sin(angle);
		
		firstVelocityX = Math.cos(angle)*secondxRes;
		firstVelocityY = Math.sin(angle)*secondyRes;
		
		secondVelocityX = Math.cos(angle)*firstxRes;
		secondVelocityY = Math.sin(angle)*firstyRes;

		first.setDx(firstVelocityX);
		first.setDy(firstVelocityY);
		second.setDx(secondVelocityX);
		second.setDy(secondVelocityY);
	}

	public double SqurDistance(double x, double y, double x2,double y2) {
		double xDiff = Math.abs(x - x2);
		double yDiff = Math.abs(y - y2);
		return Math.sqrt(xDiff*xDiff + yDiff*yDiff);
	}
	
	public ArrayList<Particle> getParticle(){
		return this.particle;
	}
	
	public void UpdateParticle(Particle p) {
		Random rand = new Random();
		double xVelocity = Math.random()*5;
		double yVelocity = Math.random()*5;
		double randx;
		double randy;
		boolean dir;
		boolean overlap = false;
		do {
			randx = Math.random()*(width - radius*2) + radius;
			randy = Math.random()*(height - radius*2 ) + radius;
			for(int j=0;j<numberOfParticles;j++) {
				if(this.SqurDistance(randx, randy, particle.get(j).getX(), particle.get(j).getY()) <= radius*2 ) {
					overlap = true;
				}
			}
			overlap = false;
		}while(overlap);
		
		//direction Setting
		dir = rand.nextBoolean();
		if(dir) {
			xVelocity*=-1;
		}
		dir = rand.nextBoolean();
		if(dir) {
			yVelocity*=-1;
		}
		//update the currentPostion and speed
		p.setX(randx);
		p.setY(randy);
		p.setDx(xVelocity);
		p.setDy(yVelocity);
		p.setCenterX(p.getX());
		p.setCenterY(p.getY());
	}



	public boolean isDestoryToggle() {
		return destoryToggle;
	}



	public void setDestoryToggle(boolean destoryToggle) {
		this.destoryToggle = destoryToggle;
	}
}
