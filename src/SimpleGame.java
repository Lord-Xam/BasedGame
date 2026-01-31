import java.awt.*;
import java.awt.event.*;

public class SimpleGame extends Frame {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public int deltaTime;

	private Player VanHelsing = new Player();

	// enemies list
	public Enemy bat = new Enemy(new float[]{0,0},new float[]{0,0},100,5);
	public Enemy[] Enemies = {bat};




	public static void main(String[] args) {
		new SimpleGame().startGame();
	}

	public boolean wKey;
	public boolean aKey;
	public boolean sKey;
	public boolean dKey;

	public void startGame() {
		setTitle("Simple Game");
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		// Add additional setup like listeners here
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				//System.out.println("Keycode: " + e.getKeyCode());
				int currentKey = e.getKeyCode();
				if (currentKey == 87) {
					wKey = true;
//					System.out.println("w");
				}
				if (currentKey == 65) {
					aKey = true;
//					System.out.println("a");
				}
				if (currentKey == 83) {
					sKey = true;
//					System.out.println("s");
				}
				if (currentKey == 68) {
					dKey = true;
//					System.out.println("d");
				}
				}

			@Override
			public void keyReleased(KeyEvent e) {
				//System.out.println("Keycode: " + e.getKeyCode());
				int currentKey = e.getKeyCode();
				if (currentKey == 87) {
					wKey = false;
//					System.out.println("w released");
				}
				if (currentKey == 65) {
					aKey = false;
//					System.out.println("a released");
				}
				if (currentKey == 83) {
					sKey = false;
//					System.out.println("s released");
				}
				if (currentKey == 68) {
					dKey = false;
//					System.out.println("d released");
				}
			}


		}


		);
		gameLoop();
	}

	long last_time = System.nanoTime();

	public void gameLoop() {
		while (true) {

			long time = System.nanoTime();
			deltaTime = (int) (time - last_time) / 1000000;
			if (deltaTime < 16) {
				try {
					Thread.sleep(16);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			deltaTime = 16;

			//System.out.println(deltaTime);
			last_time = time;

			// update enemy following
			// for (int i = 0 ; i < Enemies.length; i++ ) {
			// Enemies[i].update(VanHelsing);
			// }
			
			// update player's velocity
			
			// check states of each key	
//			System.out.println("=------=");	
//			System.out.println("w pressed? " + wKey);	
//			System.out.println("a pressed? " + aKey);	
//			System.out.println("s pressed? " + sKey);	
//			System.out.println("d pressed? " + dKey);	
			// up and down
			if (wKey == true) {
				VanHelsing.velocity[1] = 1;
			} else if (sKey == true) {
				VanHelsing.velocity[1] = -1;
			} else VanHelsing.velocity[1] = 0;
			// left and right
			if (aKey == true) {
				VanHelsing.velocity[0] = -1;
			} else if (dKey == true) {
				VanHelsing.velocity[0] = 1;
			} else VanHelsing.velocity[0] = 0;


			for (Projectile p : Projectile.projectiles) {
				p.fly(deltaTime);
			}

			VanHelsing.move(deltaTime);

			updateGameState();
			repaint();
		}
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.BLUE);
		g.fillRect(Map.convertPos(VanHelsing.position)[0], Map.convertPos(VanHelsing.position)[1], 50, 50);

		// projectiles
		for (int i = 0; i < Projectile.projectiles.size(); i++) {
			g.setColor(Color.BLACK);
			g.fillRect(Map.convertPos(Projectile.projectiles.get(i).position)[0],
					Map.convertPos(Projectile.projectiles.get(i).position)[1], 40, 10);
		}
		// draw enemies
		// for(int i=0; i < Enemies.length ; i++) {
		// g.setColor(Color.RED);
		// g.fillRect(Map.convertPos(Enemies[i].position)[0],Map.convertPos(Enemies[i].position)[1],50,
		// 50); // Example for player or an
		// }
	}

//	private void handleKeyPress(int keyCode) {
//		System.out.println(VanHelsing);
//		if (keyCode == KeyEvent.VK_W)
//			VanHelsing.velocity[1] = 1;
//		if (keyCode == KeyEvent.VK_A)
//			VanHelsing.velocity[0] = -1;
//		if (keyCode == KeyEvent.VK_S)
//			VanHelsing.velocity[1] = -1;
//		if (keyCode == KeyEvent.VK_D)
//			VanHelsing.velocity[0] = 1;
//	}

	public void updateGameState() {
		// Update positions and check for collisions
		try {
			// game logic here
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
