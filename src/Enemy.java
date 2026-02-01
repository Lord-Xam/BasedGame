import java.util.ArrayList;
public class Enemy extends Creature {

	public static ArrayList<Enemy> enemies = new ArrayList<Enemy>();

	public int hitCooldown =50;
	public int timeLeft;
	public int damage;

	public static int defaultHp = 10;
	public static int defaultSpeed = 10;
	public static int defaultHitbox = 3;
	public static int defaultDamage= 50;
	public static int defaultXpValue = 10;

	
	public static void main(String[] args) {
	}

	public Enemy(float[] position, int speed, int hp, int hitbox, int damage) {
		health = hp;
		this.position = position;
		this.speed = speed;
		this.hitbox = hitbox;
		this.damage = damage;
		enemies.add(this);
	}
		


	public boolean removeHp(int hp, Player p) {
		if (timeLeft == 0) {
			health -= hp;
			p.xp.addXp(defaultXpValue);
			//System.out.println("damage");
			timeLeft = hitCooldown;
		} 
		if (health <= 0) {
			return true;
		}
		return false;
	}

	private void cooldownTick() {
		if (timeLeft > 0) {
			System.out.println(timeLeft);
			timeLeft--;
		}
	}


	public void followPlayer(Player p,double dt) {
		if (Entity.withinHitbox(p,this) ) {
		//	System.out.println("hitbox");
			return;
		}

		float dx = p.position[0] - position[0];
		float dy = p.position[1] - position[1];
		double dir = Math.atan2(dy,dx);
		
		position[0] += speed*Math.cos(dir)* dt;
		position[1] += speed*Math.sin(dir)* dt;
	}
	public void tryHitPlayer(Player p) {
		if (withinHitbox(p,this)) {
			System.out.println("Hit player");
			p.hit(this.damage);

		}

	}
	public void update(Player p,double dt) {
		followPlayer(p,dt);
		cooldownTick();
		tryHitPlayer(p);
	}

	
}
