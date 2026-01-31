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

	public void startGame() {
		setTitle("Simple Game");
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		// Add additional setup like listeners here
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				handleKeyPress(e.getKeyCode());
			}
		});
		gameLoop();
	}

	long last_time = System.nanoTime();

	public void gameLoop() {
		while (true) {

			long time = System.nanoTime();
			deltaTime = (int) (time - last_time) / 1000000;
			if (deltaTime <= 2 || deltaTime > 100) {
				deltaTime = 16;
				try {
					Thread.sleep(16);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			last_time = time;

			// update enemy following
			// for (int i = 0 ; i < Enemies.length; i++ ) {
			// Enemies[i].update(VanHelsing);
			// }

			for (int i = 0; i < Projectile.projectiles.size(); i++) {
				if (Projectile.projectiles.get(i).dead == 0)
					Projectile.projectiles.get(i).update(deltaTime);
				else {
					Projectile.projectiles.remove(i);
					i++;
				}
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
		g.drawString("fps: "+1000/(float)deltaTime,20,60);

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

	private void handleKeyPress(int keyCode) {
		System.out.println(VanHelsing);
		if (keyCode == KeyEvent.VK_W)
			VanHelsing.velocity[1] = 1;
		if (keyCode == KeyEvent.VK_A)
			VanHelsing.velocity[0] = -1;
		if (keyCode == KeyEvent.VK_S)
			VanHelsing.velocity[1] = -1;
		if (keyCode == KeyEvent.VK_D)
			VanHelsing.velocity[0] = 1;
		if (keyCode == KeyEvent.VK_SPACE)
			VanHelsing.attack();
	}

	public void updateGameState() {
		// Update positions and check for collisions
		try {
			// game logic here
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
