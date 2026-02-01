import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class SimpleGame extends JFrame {

	public static final int WIDTH = 1200;
	public static final int HEIGHT = 900;
	private GamePanel panel;
	public double deltaTime;

	public int difficulty = 0;

	Image player_sprite = Toolkit.getDefaultToolkit().getImage("../sprites/vanhelsing.png");
	Image enemy_sprite = Toolkit.getDefaultToolkit().getImage("../sprites/bat.png");
	Image weapon_sprite = Toolkit.getDefaultToolkit().getImage("../sprites/weapon.png");
	// Image bg_sprite =
	// Toolkit.getDefaultToolkit().getImage("../sprites/grass.jpg");

	private Player VanHelsing = new Player();

	private int spawnchance = 100; // higher number = lower chance
	private int speedboost = 0; // for enemies
	private long start_time = System.currentTimeMillis();

	// enemies list

	public static void main(String[] args) {
		new SimpleGame().startGame();
	}

	public boolean wKey;
	public boolean aKey;
	public boolean sKey;
	public boolean dKey;

	private Color bg = new Color(0, 128, 32);

	public void startGame() {
		setTitle("Simple Game");
		setSize(WIDTH, HEIGHT);
//Kai (start)
		panel = new GamePanel();
		add(panel);
		panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		pack();
		panel.setFocusable(true);
		panel.requestFocusInWindow();
//Due to swapping from Jframe to panel there might be some other inconsistencies, i.e. only worked with Jframe, so you might need to make other swaps.
//End
		setVisible(true);
		// Add additional setup like listeners here
		panel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// System.out.println("Keycode: " + e.getKeyCode());
				int currentKey = e.getKeyCode();
				if (currentKey == 87) {
					wKey = true;
					// System.out.println("w");
				}
				if (currentKey == 65) {
					aKey = true;
					// System.out.println("a");
				}
				if (currentKey == 83) {
					sKey = true;
					// System.out.println("s");
				}
				if (currentKey == 68) {
					dKey = true;
					// System.out.println("d");
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// System.out.println("Keycode: " + e.getKeyCode());
				int currentKey = e.getKeyCode();
				// System.out.println(e.getKeyCode());
				if (currentKey == 87) {
					wKey = false;
					// System.out.println("w released");
				}
				if (currentKey == 65) {
					aKey = false;
					// System.out.println("a released");
				}
				if (currentKey == 83) {
					sKey = false;
					// System.out.println("s released");
				}
				if (currentKey == 68) {
					dKey = false;
					// System.out.println("d released");
				}
			}

		}

		);
		gameLoop();
	}

	long old_time = System.nanoTime();

	public void gameLoop() {
		while (VanHelsing.alive == true) {
			long new_time = System.nanoTime();
			deltaTime = (new_time - old_time) / 1000000000; // in seconds
			if (deltaTime <= 0 || deltaTime > 1) {
				deltaTime = 0.016;
			}
			// System.out.println(deltaTime);

			updateGameState(deltaTime);

			old_time = new_time;
//Used to be just repaint()
			panel.repaint();
			try {
				Thread.sleep((int) (deltaTime * 1000)); // in miliseconds
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
//added this class GamePanel apparently should stop stuttering
class GamePanel extends JPanel {
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		// draw background
		g2d.setColor(bg);
		g2d.fillRect(0, 0, getWidth(), getHeight());

		// draw player
		if (VanHelsing.alive == true) {
			g2d.drawImage(player_sprite, Map.convertPos(VanHelsing.position)[0], Map.convertPos(VanHelsing.position)[1],
					60, 80, null);

			g2d.setColor(Color.BLACK);
			g2d.fillRect(-20+Map.convertPos(VanHelsing.position)[0], Map.convertPos(VanHelsing.position)[1], VanHelsing.hp.total, VanHelsing.hp.height);
			g2d.setColor(Color.BLUE);
			g2d.fillRect(-20+Map.convertPos(VanHelsing.position)[0], Map.convertPos(VanHelsing.position)[1], VanHelsing.hp.length, VanHelsing.hp.height);

		} else
			g2d.drawString("game over", WIDTH / 2, HEIGHT / 2);

		// projectiles
		for (int i = 0; i < Projectile.projectiles.size(); i++) {
			Projectile proj = Projectile.projectiles.get(i);
			g2d.drawImage(weapon_sprite, Map.convertPos(proj.position)[0], Map.convertPos(proj.position)[1], 40, 40,
					null);
		}

		// enemies
		for (int i = 0; i < Enemy.enemies.size(); i++) {
			Enemy enemy = Enemy.enemies.get(i);
			g2d.drawImage(enemy_sprite, Map.convertPos(enemy.position)[0], Map.convertPos(enemy.position)[1], 50, 50, null);
			g2d.setColor(Color.BLACK);
			g2d.fillRect(20+Map.convertPos(enemy.position)[0], Map.convertPos(enemy.position)[1], enemy.hpbar.total, enemy.hpbar.height);
			g2d.setColor(Color.RED);
			g2d.fillRect(20+Map.convertPos(enemy.position)[0], Map.convertPos(enemy.position)[1], enemy.hpbar.length, enemy.hpbar.height);
		}

		// xp bar
		g2d.setColor(Color.BLUE);
		//Swapped HEIGHT and WIDTH with getHeight() and getWidth()
		g2d.fillRect(0, 0, VanHelsing.xp.barLength, getHeight() / 10);


		// death screen
		if (VanHelsing.alive == false) {
			g2d.setColor(Color.BLACK);
			g2d.fillRect(0, HEIGHT/4, WIDTH, HEIGHT/2);
			g2d.setColor(Color.RED);
			g2d.setFont(new Font("Adwaita Mono", Font.BOLD, 50));
			g2d.drawString("YOU DIED", WIDTH/3 +90, HEIGHT/2);
		}
	}
}

	public void updateGameState(double deltaTime) {

		// increase difficulty
		if ((System.currentTimeMillis() - start_time) % 10000 <= 20) {
			difficulty++;
			System.out.println("DIFFICULTY "+difficulty);
			if (spawnchance > 5)
				spawnchance -= 5;
			else if (spawnchance > 1)
				spawnchance--;
			if (speedboost < 50)
				speedboost += 5;
		}

		// update enemy following
		for (int i = 0; i < Enemy.enemies.size(); i++) {
			Enemy.enemies.get(i).update(VanHelsing, deltaTime);
		}

		// add new enemies
		
		for (int i = 0; i < 1+difficulty/4; i++) {
			EnemySpawner.trySpawn(WIDTH, HEIGHT, Enemy.defaultSpeed+speedboost, Enemy.defaultHp, Enemy.defaultHitbox,
				Enemy.defaultDamage);
		}
		
		// update player's velocity
		// up and down
		if (wKey == true) {
			VanHelsing.velocity[1] = 1;
		} else if (sKey == true) {
			VanHelsing.velocity[1] = -1;
		} else
			VanHelsing.velocity[1] = 0;
		// left and right
		if (aKey == true) {
			VanHelsing.velocity[0] = -1;
		} else if (dKey == true) {
			VanHelsing.velocity[0] = 1;
		} else
			VanHelsing.velocity[0] = 0;

		// attack
		if (Projectile.cooldowntimer == 0 && VanHelsing.alive == true) {
			Projectile.cooldowntimer = Projectile.cooldown; // start cooldown
			for (int i = 0; i < 1+difficulty/6; i++) {
				VanHelsing.attack();
			}
		} else
			Projectile.cooldowntimer--;

		EnemyProjectile.collide(Enemy.enemies, Projectile.projectiles, VanHelsing);

		for (int i = 0; i < Projectile.projectiles.size(); i++) {
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
		
		// player i frames
		VanHelsing.cooldownTick();
	}

}
