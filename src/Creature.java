public class Creature extends Entity {
	
	public int health = 100;
	public int velocity = 5;

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
