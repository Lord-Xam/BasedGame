public class Player extends Creature {
	
	public float[] velocity = new float[2];

	public Player(){
		speed = 50;
		System.out.println("hello player");
	}

	public void attack() {
		new Projectile(position); //create a projectile at the players position
	}

	public void move(int dt) {
		// if moving too fast
		if (Math.pow(velocity[0],2) > 1 || Math.pow(velocity[1],2) > 1) {
			position[0] += 0.707f*speed*velocity[0]*(float)dt/1000;
			position[1] += 0.707f*speed*velocity[1]*(float)dt/1000;
		} else {
			position[0] += speed*velocity[0]*(float)dt/1000;
			position[1] += speed*velocity[1]*(float)dt/1000;
		}
	}
}
