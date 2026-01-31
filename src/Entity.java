import java.lang.Math;

public class Entity {
	
	public int[] position = new int[2];

	public static void main(String[] args) {
	}

	public int hitbox;

	public static boolean withinHitbox(Entity a, Entity b) {
		if ( Math.abs(a.position[0] - b.position[0]) < a.hitbox + b.hitbox && Math.abs(a.position[1] - b.position[1]) < a.hitbox + b.hitbox) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		return "("+position[0] + ", " + position[1]+")";
	}
}
