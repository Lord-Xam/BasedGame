import java.util.ArrayList;
public class EnemyProjectile {
	public static void collide(ArrayList<Enemy> enemies, ArrayList<Projectile> projectiles, Player p) {
		for (int i = 0; i < projectiles.size(); i++) {
			boolean dead = false;
			for(int j =0; j<enemies.size(); j++) {
				dead = projectiles.get(i).hitEnemy(enemies.get(j), p);
				if (dead) enemies.remove(j);
			}

		}
	}
}
