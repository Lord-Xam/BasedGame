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

	public static int cooldown = 50;
	public static int cooldowntimer;
	private float angle;

	public Projectile(float[] pos) {
		position[0] = pos[0];
		position[1] = pos[1]-10;
		Random rand = new Random();
		angle = rand.nextFloat();
		velocity[0] = 5*(float)Math.cos(angle);
		velocity[1] = 5*(float)Math.sin(angle);
		scale = 10f;
		System.out.print("new projectile at ");
		System.out.println(this);
		projectiles.add(this);
	}
	
	public void update(int dt) {
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

	public void hitEnemy(Enemy e) {
		if ((Math.abs(this.position[0] - e.position[0]) < this.width + e.hitbox) && (Math.abs(this.position[1] - e.position[1]) < this.height+ e.hitbox)) {
			System.out.println("enemy hit");
			e.removeHp(damage);
		}

	}



}
