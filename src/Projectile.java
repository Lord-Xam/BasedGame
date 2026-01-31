import java.util.ArrayList;

public class Projectile extends Entity {

	public static ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

	private int lifespan = 100;
	private int lifetime = 0;

	public Projectile(float[] pos) {
		position[0] = pos[0];
		position[1] = pos[1];
		velocity[0] = 1;
		velocity[1] = 0;
		System.out.print("new projectile at ");
		System.out.println(this);
		projectiles.add(this);
	}
	
	public void fly(int dt) {
		while (lifetime < lifespan) {
			move(dt);
			lifetime++;
		}
	}
}
