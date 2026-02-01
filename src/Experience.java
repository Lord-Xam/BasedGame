public class Experience {
	private int xp;
	public int xpNextLevel;
	private int level;
	private int levelIncrement;
	public int barLength;

	public void addXp(int x) {
		this.xp += x;
		barLength += ((float) xp) / xpNextLevel * SimpleGame.WIDTH;
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
		barLength = 0;
	}

	public Experience(int levelIncrement) {
		xp = 0;
		this.levelIncrement = levelIncrement;
		xpNextLevel = levelIncrement;
	}

	public int getXp() {
		return xp;
	}


}
