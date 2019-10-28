package com.edpena.alphabetSoup;

public class PositionHelper {

	private int x;
	private int y;

	public PositionHelper(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof PositionHelper || obj != null) {

			PositionHelper positionHelper = (PositionHelper) obj;

			if (this.x == positionHelper.x && this.y == positionHelper.y) {
				return true;
			} else {
				return false;
			}

		} else {
			return false;
		}
	}

}
