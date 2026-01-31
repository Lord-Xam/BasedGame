public class Player {
	
	public int[] position = {400,300};
	private int velocity = 1;

	public static void main(String[] args) {
		System.out.println("hello");
	}

	// 1:up 2:left 3:down 4:right
	public void move(int direction) {
		if (direction == 1 ) {
				position[1] += 1*velocity;
		}
		if (direction == 2 ) {
				position[0] -= 1*velocity;
				System.out.println("moving left");
		}
		if (direction == 3 ) {
				position[1] -= 1*velocity;
		}
		if (direction == 4 ) {
				position[0] += 1*velocity;
				System.out.println("moving right");
		}
		System.out.println(position[0]);
	}
}
