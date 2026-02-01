import java.util.Random;

public class EnemySpawner {

	private static int spawnchance = 100; //higher number = lower chance

	public static void trySpawn(int screenWidth,int screenHeight, int speed, int hp, int hitbox, int damage) {
		Random r = new Random();
		int i = r.nextInt(spawnchance);

		float w = r.nextInt(screenWidth);
		float h = r.nextInt(screenHeight);
		float[] pos = new float[] {w,h};
		float[] mapPos = new float[2] ;
		mapPos[0] = pos[0] - SimpleGame.WIDTH/2;
		mapPos[1] = pos[1] - SimpleGame.HEIGHT/2;
		
		if (i==0) {
			new Enemy(mapPos, speed, hp, hitbox, damage);
//			System.out.println("new enemy at " + mapPos[0] +", " + mapPos[1]);
		}

	}

}
