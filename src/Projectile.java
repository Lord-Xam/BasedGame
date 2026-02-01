import java.util.ArrayList;
import java.util.Random;
import java.util.random.*;

public class Projectile extends Entity {

	public static ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

	private int lifespan = 1000;
	private int lifetime = 0;
	public int dead;
	public int width =10;
	public int height = 5;
	public int damage = 100;
	public int rotation;

	public static int cooldown = 50;
	public static int cooldowntimer;

	public Projectile(float[] pos) {
		position[0] = pos[0];
		position[1] = pos[1]-10;
		speed = 100;
		Random rand = new Random();
		angle = rand.nextFloat(0f,2*(float)Math.PI);
//		System.out.println(angle);
//		System.out.print("new projectile at ");
//		System.out.println(this);
		projectiles.add(this);
	}

	public static void decreaseCooldown() {
		if (cooldown > 5)
			cooldown -= 5;
		else if (cooldown > 1)
			cooldown--;
	}
	
	public void update(double dt) {
		if (lifetime < lifespan) {
			move(dt);
			lifetime++;
		}
		else
			kill();
	}

	public void kill(){
		dead = 1;
	}

	public boolean hitEnemy(Enemy e, Player p) {
		if ((Math.abs(this.position[0] - e.position[0]) < this.width + e.hitbox) && (Math.abs(this.position[1] - e.position[1]) < this.height+ e.hitbox)) {
			//System.out.println("enemy hit");
			return e.removeHp(damage,p);
		}
		return false;

	}



}
