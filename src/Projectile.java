import java.util.ArrayList;

public class Projectile extends Entity {

	public static ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

	private int lifespan = 1000;
	private int lifetime = 0;
	public int dead;
	public static int cooldown = 100;
	public static int cooldowntimer;

	public Projectile(float[] pos) {
		position[0] = pos[0];
		position[1] = pos[1];
		velocity[0] = 2;
		velocity[1] = 0;
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
