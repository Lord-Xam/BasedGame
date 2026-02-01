import java.awt.*;
import java.awt.event.*;

public class SimpleGame extends Frame {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public int deltaTime;

	Image player_sprite = Toolkit.getDefaultToolkit().getImage("../sprites/vanhelsing.png");

	private Player VanHelsing = new Player();

	// enemies list
	final int monsterSpeed =  10;




	public static void main(String[] args) {
		new SimpleGame().startGame();
	}

	public boolean wKey;
	public boolean aKey;
	public boolean sKey;
	public boolean dKey;
	public boolean space;

	public void startGame() {
		setTitle("Simple Game");
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		new Enemy(new float[]{0,0},monsterSpeed,100,5);
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
				if (currentKey == 32) //space
					space = true;
				}

			@Override
			public void keyReleased(KeyEvent e) {
				//System.out.println("Keycode: " + e.getKeyCode());
				int currentKey = e.getKeyCode();
				//System.out.println(e.getKeyCode());
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
				if (currentKey == 32) //space
					space = false;
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
			 for (int i = 0 ; i < Enemy.enemies.size(); i++ ) {
			 Enemy.enemies.get(i).update(VanHelsing,deltaTime);
			 }
			
			// update player's velocity
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

			if (space == true ) {
				if (Projectile.cooldowntimer == 0) {
					Projectile.cooldowntimer = Projectile.cooldown; //start cooldown
					VanHelsing.attack();
				}
				else
					Projectile.cooldowntimer--;
			}

			EnemyProjectile.collide(Enemy.enemies,Projectile.projectiles);



			updateGameState();
			repaint();
		}
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.BLUE);
		//g.fillRect(Map.convertPos(VanHelsing.position)[0], Map.convertPos(VanHelsing.position)[1], 50, 50);
		g.drawImage(player_sprite, Map.convertPos(VanHelsing.position)[0], Map.convertPos(VanHelsing.position)[1],60,80, null);
		g.drawString("fps: "+1000/(float)deltaTime,20,60);

		// projectiles
		for (int i = 0; i < Projectile.projectiles.size(); i++) {
			g.setColor(Color.BLACK);
			g.fillRect(Map.convertPos(Projectile.projectiles.get(i).position)[0],
					Map.convertPos(Projectile.projectiles.get(i).position)[1], Projectile.projectiles.get(i).width, Projectile.projectiles.get(i).height);
		}
		// draw enemies
		 for(int i=0; i < Enemy.enemies.size() ; i++) {
		 g.setColor(Color.RED);
		 g.fillRect(Map.convertPos(Enemy.enemies.get(i).position)[0],Map.convertPos(Enemy.enemies.get(i).position)[1],50, 50); // Example for player or an
		 }
	}

	public void updateGameState() {
		// Update positions and check for collisions
			for (int i = 0; i<Projectile.projectiles.size(); i++ ) {		
				if (Projectile.projectiles.get(i).dead == 0)
					Projectile.projectiles.get(i).update(deltaTime);
				else {
					Projectile.projectiles.remove(i);
					i++;
				}
			}

			VanHelsing.move(deltaTime);
		try {
			// game logic here
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
