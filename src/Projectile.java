import java.util.ArrayList;

public class Projectile extends Entity {
	
	public static ArrayList<Projectile> projectiles = new ArrayList<Projectile>(); 

	private int lifespan = 100;
	private int velocity = 1;

	public Projectile(int[] pos) {
		position[0] = pos[0];
		position[1] = pos[1];
		System.out.print("new projectile at ");
		System.out.println(this);
		projectiles.add(this);
	}

	public static void main(String[] args) {
		// fly right
	}

	// 1:up 2:left 3:down 4:right
	public void move(int direction) {
		if (direction == 1 ) {
				position[1] += 1*velocity;
		}
		if (direction == 2 ) {
				position[0] -= 1*velocity;
		}
		if (direction == 3 ) {
				position[1] -= 1*velocity;
		}
		if (direction == 4 ) {
				position[0] += 1*velocity;
		}
	}
}
