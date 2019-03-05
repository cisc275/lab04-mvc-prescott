import java.awt.Color;

/**
 * Model: Contains all the state and logic
 * Does not contain anything about images or graphics, must ask view for that
 *
 * has methods to
 *  detect collision with boundaries
 * decide next direction
 * provide direction
 * provide location
 **/
public class Model {
	int xloc = 0;
	int yloc = 0;
	Direction facing = Direction.SOUTHEAST;
	int worldWidth;
	int worldHeight;
	int imgWidth;
	int imgHeight;

	final int xIncr = 8;
	final int yIncr = 2;
	
	public Model(int worldWidth, int worldHeight, int imgWidth, int imgHeight) {
		this.worldWidth = worldWidth;
		this.worldHeight = worldHeight;
		this.imgWidth = imgWidth;
		this.imgHeight = imgHeight;
	}
	/**
	 * This does exactly what it says on the tin
	 */
	public void updateLocationAndDirection() {
		xloc += xIncr * facing.getDX();
		yloc += yIncr * facing.getDY();
		if (xloc < 0)
			facing = Direction.dirTo(1, facing.getDY());
		if (xloc + imgWidth > worldWidth)
			facing = Direction.dirTo(-1, facing.getDY());
		if (yloc < 0)
			facing = Direction.dirTo(facing.getDX(), 1);
		if (yloc + imgHeight > worldHeight)
			facing = Direction.dirTo(facing.getDX(), -1);	
	}
	public int getX() {
		return xloc;
	}
	public int getY() {
		return yloc;
	}
	public Direction getDirect() {
		return facing;
	}
}