import java.util.ArrayList;
import java.util.Random;
import java.util.random.*;

public class Projectile extends Entity {

	public static ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

	private int lifespan = 1000;
	private int lifetime = 0;
	public int dead;

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
}
