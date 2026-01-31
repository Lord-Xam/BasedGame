public class Enemy extends Creature {
	
	public static void main(String[] args) {
	}

	public Enemy(float[] position, float speed, int hp, int hitbox) {
		health = hp;
		this.position = position;
		this.hitbox = hitbox;
	}


	public void followPlayer(Player p,int dt) {
		if (Entity.withinHitbox(p,this) ) {
		//	System.out.println("hitbox");
			return;
		}

		float dx = p.position[0] - position[0];
		float dy = p.position[1] - position[1];
		double dir = Math.atan2(dy,dx);
		
		position[0] += speed*Math.cos(dir);
		position[1] += speed*Math.sin(dir);
	}

	public void update(Player p,int dt) {
		followPlayer(p,dt);
	}
}
