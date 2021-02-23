package ParticleSimulation;

import java.util.ArrayList;

public class ParticlesData {
	
	private ArrayList<Particle> particles;
	private int numberOfParticles =100;
	
	public ParticlesData() {
		particles = new ArrayList<Particle>();
		for(int i=0;i<numberOfParticles;i++) {
			this.particles.add(new Particle());
		}
	}
	
	public ArrayList<Particle> getParticles(){
		return particles;
	}
}
