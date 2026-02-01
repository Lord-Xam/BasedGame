public class Player extends Creature {

	public boolean alive = true;

	public final int defaultLevelIncrement = 100;

	public Experience xp = new Experience(100);
	
	public float[] velocity = new float[2];

	public Player(){
		speed = 100;
		System.out.println("hello player");
	}

	public void attack() {
		new Projectile(position); //create a projectile at the players position
	}

	public void move(double dt) {
		// if moving too fast
		if (Math.pow(velocity[0],2) + Math.pow(velocity[1],2) > 1) {
			position[0] += 0.707f*speed*velocity[0]*dt;
			position[1] += 0.707f*speed*velocity[1]*dt;
		} else {
			position[0] += speed*velocity[0]*dt;
			position[1] += speed*velocity[1]*dt;
		}
	}

	public void hit(int damage) {
		health -= damage;

		if (health <=0 ) {
			alive = false;
			System.out.println("dead");

		}
	}

}
