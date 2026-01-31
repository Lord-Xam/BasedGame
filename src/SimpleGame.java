import java.awt.*;
import java.awt.event.*;

public class SimpleGame extends Frame {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	private Player VanHelsing = new Player();

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

	public void gameLoop() {
		while (true) {
			updateGameState();
			repaint();
			try {
				Thread.sleep(16); // Approx. 60 FPS
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(0,0,WIDTH,HEIGHT);
		g.setColor(Color.BLUE);
		g.fillRect(Map.convertPos(VanHelsing.position)[0],Map.convertPos(VanHelsing.position)[1],50, 50); 
		for (int i = 0; i < Projectile.projectiles.size(); i++) {
			g.setColor(Color.BLACK);
			g.fillRect(Map.convertPos(Projectile.projectiles.get(i).position)[0],Map.convertPos(Projectile.projectiles.get(i).position)[0], 40, 10);
		}
			}

	private void handleKeyPress(int keyCode) {
		System.out.println(VanHelsing);
		switch (keyCode) {
			case KeyEvent.VK_W:
				VanHelsing.move(1);
				break;
			case KeyEvent.VK_A:
				VanHelsing.move(2);
				break;
			case KeyEvent.VK_S:
				VanHelsing.move(3);
				break;
			case KeyEvent.VK_D:
				VanHelsing.move(4);
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
