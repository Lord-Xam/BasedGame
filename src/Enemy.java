public class Enemy extends Creature {
	
	public static void main(String[] args) {
	}

	public Enemy(float[] position, float speed, int hp, int hitbox) {
		health = hp;
		this.position = position;
		this.scale = speed;
		this.hitbox = hitbox;
	}


	public void followPlayer(Player p,int dt) {
		if (Entity.withinHitbox(p,this) ) {
		//	System.out.println("hitbox");
			return;
		}

		float xDir = p.position[0] - this.position[0];
		float yDir = p.position[1] - this.position[1];

		float xUnitDir = xDir / ((float) Math.sqrt( xDir*xDir + yDir*yDir));
		float yUnitDir = yDir / ((float) Math.sqrt( xDir*xDir + yDir*yDir));

//		System.out.println("xunit"+ xUnitDir+ " yunit"+  yUnitDir);

		position[0] += scale* xUnitDir*dt /1000;
		position[1] +=  scale* yUnitDir*dt/1000;
	}

	public void update(Player p,int dt) {
		followPlayer(p,dt);
	}
}
