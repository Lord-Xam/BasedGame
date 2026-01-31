public class Enemy extends Creature {
	
	public static void main(String[] args) {
	}


	public Enemy(float[] position, float[] velocity, int hp, int hitbox) {
		health = hp;
		this.position = position;
		this.velocity = velocity;
		this.hitbox = hitbox;
	}

	public void followPlayer(Player p) {
		if (Entity.withinHitbox(p,this) ) {
			return;
		}

		float xDir = p.position[0] - this.position[0];
		float yDir = p.position[1] - this.position[1];

		float xUnitDir = xDir / ((float) Math.sqrt( xDir*xDir + yDir*yDir));
		float yUnitDir = yDir / ((float) Math.sqrt( xDir*xDir + yDir*yDir));

//		System.out.println("xunit"+ xUnitDir+ " yunit"+  yUnitDir);

		position[0] += velocity[0] * xUnitDir;
		position[1] += velocity[1] * yUnitDir;
	}

	public void update(Player p) {
		followPlayer(p);
	}
}
