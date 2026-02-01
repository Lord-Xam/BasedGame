import java.lang.Math;

public class HpBar {

	public int height;
	public int total;
	public int length;

	public HpBar(int len) {
		height = Math.round(SimpleGame.HEIGHT/300);
		total = len;
		length = len;
	}
	

}
