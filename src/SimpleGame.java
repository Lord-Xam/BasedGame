import java.awt.*;
import java.awt.event.*;

public class SimpleGame extends Frame {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public int deltaTime;

	private Player VanHelsing = new Player();


	// enemies list
	public Enemy bat = new Enemy(5,5,100,2,5);
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
			deltaTime = (int) ((time - last_time) / 1000000);
			last_time = time;

			// update enemy following
			for (int i = 0 ; i < Enemies.length; i++ ) {
				Enemies[i].update(VanHelsing);
			}

			for (Projectile p : Projectile.projectiles) {
				p.fly(deltaTime);
			}

			updateGameState();
			repaint();
		}
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(0,0,WIDTH,HEIGHT);
		g.setColor(Color.BLUE);
		g.fillRect(Map.convertPos(VanHelsing.position)[0],Map.convertPos(VanHelsing.position)[1],50, 50); 

		// projectiles
		for (int i = 0; i < Projectile.projectiles.size(); i++) {
			g.setColor(Color.BLACK);
			g.fillRect(Map.convertPos(Projectile.projectiles.get(i).position)[0],Map.convertPos(Projectile.projectiles.get(i).position)[1], 40, 10);
		}
		// draw enemies
		for(int i=0; i < Enemies.length ; i++) {
			g.setColor(Color.RED);
			g.fillRect(Map.convertPos(Enemies[i].position)[0],Map.convertPos(Enemies[i].position)[1],50, 50); // Example for player or an 	
		}
			}

	private void handleKeyPress(int keyCode) {
		System.out.println(VanHelsing);
		switch (keyCode) {
			case KeyEvent.VK_W:
				VanHelsing.move(deltaTime);
				break;
			case KeyEvent.VK_A:
				VanHelsing.move(deltaTime);
				break;
			case KeyEvent.VK_S:
				VanHelsing.move(deltaTime);
				break;
			case KeyEvent.VK_D:
				VanHelsing.move(deltaTime);
				break;
			case KeyEvent.VK_SPACE:
				VanHelsing.attack();
		}
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
