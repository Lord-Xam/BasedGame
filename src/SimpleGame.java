import java.awt.*;
import java.awt.event.*;

public class SimpleGame extends Frame {

	private final int WIDTH = 800;
	private final int HEIGHT = 600;
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

	public void gameLoop() {
		while (true) {
			// update enemy following
			for (int i = 0 ; i < Enemies.length; i++ ) {
				Enemies[i].followPlayer(VanHelsing);
			}


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
		g.fillRect(Map.convertPos(VanHelsing.position[0],VanHelsing.position[1],WIDTH,HEIGHT)[0],Map.convertPos(VanHelsing.position[0],VanHelsing.position[1],WIDTH,HEIGHT)[1],50, 50); // Example for player or an 	

		// draw enemies
		for(int i=0; i < Enemies.length ; i++) {
			g.setColor(Color.RED);
			g.fillRect(Map.convertPos(Enemies[i].position[0],Enemies[i].position[1],WIDTH,HEIGHT)[0],Map.convertPos(Enemies[i].position[0],Enemies[i].position[1],WIDTH,HEIGHT)[1],50, 50); // Example for player or an 	
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
