public class Map {
	public static void main(String[] args) {
		System.out.println("Map");
	}

	// convert to screen postition for painting
	 public static int[] convertPos(int[] pos) {
		int[] screenPos = new int[2];
		screenPos[0] = pos[0] + SimpleGame.WIDTH/2;
		screenPos[1] = -pos[1] + SimpleGame.HEIGHT/2;
		return screenPos; 
	}
}
