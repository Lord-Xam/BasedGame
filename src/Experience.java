public class Experience {
	private int xp;
	private int xpNextLevel;
	private int level;
	private int levelIncrement;

	public void addXp(int x) {
		this.xp += x;
		if (xp >= xpNextLevel ) {
			levelUp();
		}

		System.out.println("Xp : " + xp + " / " + xpNextLevel);

	}

	public void levelUp(){
		level++;
		xp =0;
		xpNextLevel+= levelIncrement;
		System.out.println("level up");
	}

	public Experience(int levelIncrement) {
		xp = 0;
		this.levelIncrement = levelIncrement;
		xpNextLevel = levelIncrement;
	}

}
