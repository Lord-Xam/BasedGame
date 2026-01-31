public class Entity {

	public float[] position = new float[2];
	public int speed;
	public float angle;

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
		position[0] += speed*Math.cos(angle)*(float)dt/1000f;
		position[1] += speed*Math.sin(angle)*(float)dt/1000f;
	}

}
