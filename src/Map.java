public class Map {
	public static void main(String[] args) {
		System.out.println("Map");
	}

	// convert to screen postition for painting
	 public static int[] convertPos(int x, int y, int width, int height) {
		int[] screenPos = new int[2];
		screenPos[0] = x + width/2;
		screenPos[1] = -y + height/2;
		return screenPos; 
	}
}
