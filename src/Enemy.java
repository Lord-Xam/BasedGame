import java.lang.Math;

public class Enemy extends Creature {
		
	public static void main(String[] args) {
		System.out.println("hello player");
	}

	public Enemy(int xPos, int yPos, int hp, int vel, int hitbox) {
		health = hp;
		velocity = vel;
		position[0] = xPos;
		position[1] = yPos;
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

		position[0] += velocity * xUnitDir;
		position[1] += velocity * yUnitDir;
	}
}
