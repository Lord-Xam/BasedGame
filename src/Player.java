public class Player extends Creature {
	
	public void main(String[] args) {
		System.out.println("hello player");
	}

	public void attack() {
		new Projectile(position); //create a projectile at the players position
	}
}
