import java.util.ArrayList;

public class Projectile extends Entity {
	
	public static ArrayList<Projectile> projectiles = new ArrayList<Projectile>(); 

	private int lifespan = 100;
	private int velocity = 10;

	public Projectile() {
		System.out.println(projectiles.size() + " projectiles");
		projectiles.add(this);
	}
	public void main(String[] args) {	
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
