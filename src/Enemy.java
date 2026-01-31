import java.util.ArrayList;
public class Enemy extends Creature {

	public static ArrayList<Enemy> enemies = new ArrayList<Enemy>();

	public int hitCooldown =50;
	public int timeLeft;
	
	public static void main(String[] args) {
	}

	public Enemy(float[] position, int speed, int hp, int hitbox) {
		health = hp;
		this.position = position;
		this.speed = speed;
		this.hitbox = hitbox;
		enemies.add(this);
	}

	public void kill() {
		System.out.println("killed");
	};

	public void removeHp(int hp) {
		if (timeLeft == 0) {
			health -= hp;
			System.out.println("damage");
			timeLeft = hitCooldown;
		} 
		if (health <= 0) {
			kill();
		}
	}

	private void cooldownTick() {
		if (timeLeft > 0) {
			System.out.println(timeLeft);
			timeLeft--;
		}
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
		cooldownTick();
	}
}
