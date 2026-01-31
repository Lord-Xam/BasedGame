public class Entity {

	public float[] position = new float[2];
	public float[] velocity = new float[2];
	public float scale = 10f; //scale movement speed

	public int hitbox;

	public static void main(String[] args) {
	}

	public static boolean withinHitbox(Entity a, Entity b) {
		if (Math.abs(a.position[0] - b.position[0]) < a.hitbox + b.hitbox
				&& Math.abs(a.position[1] - b.position[1]) < a.hitbox + b.hitbox) {
			return true;
		}
		return false;
	}

	public String toString() {
		return "(" + position[0] + ", " + position[1] + ")";
	}

	public void move(int dt) {
		if (velocity[0] == 0 ) {
			position[1] += scale*velocity[1]*dt/1000;
		} else if (velocity[1] == 0 ) {
			position[0] += scale*velocity[0]*dt/1000;
		} else {
			position[0] += Math.sqrt(1f/2)*scale*velocity[0]*dt/1000;
			position[1] += Math.sqrt(1f/2)*scale*velocity[1]*dt/1000;
		}
	}

}
