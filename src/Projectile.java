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

	public Projectile(float[] pos) {
		position[0] = pos[0];
		position[1] = pos[1]-10;
		speed = 100;
		Random rand = new Random();
		angle = rand.nextFloat(0f,2*(float)Math.PI);
		System.out.println(angle);
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
