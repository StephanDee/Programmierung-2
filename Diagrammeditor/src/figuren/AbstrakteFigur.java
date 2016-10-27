package figuren;

public abstract class AbstrakteFigur implements Figur {
	
	protected int x = 1;
	protected int y = 1;
	protected int dx = 50;
	protected int dy = 70;

	@Override
	public int getX() {
		return this.x;
	}

	@Override
	public int getY() {
		return this.y;
	}

	@Override
	public int getDX() {
		return this.dx;
	}

	@Override
	public int getDY() {
		return this.dy;
	}

	@Override
	public int getXR() {
		if (this.dx < 1) {
			return this.x;
		}
		return this.x + this.dx;
	}

	@Override
	public int getXL() {
		
		if (this.dx < 1) {
			return dx + this.x;
		}
		return this.x;
	}

	@Override
	public int getYO() {
		if (this.dy < 1) {
			return this.y + this.dy;
		}
		return this.y;
	}

	@Override
	public int getYU() {
		if (this.dy < 1) {
			return this.y;
		}
		return this.y + this.dy;
	}

	@Override
	public int abstandZumOrtspunkt(int x, int y) throws UnsupportedOperationException {
		
		return Math.abs((x - this.getXL()) * (y - this.getYO()) / 2);
	}

	@Override
	public int abstandZumBildpunkt(int x, int y) throws UnsupportedOperationException {

		return Math.abs((x - this.getXR()) * (y - this.getYU()) / 2);
	}

	@Override
	public void markierungMitteilen() throws UnsupportedOperationException {
		
	}

	@Override
	public void entmarkierungMitteilen() throws UnsupportedOperationException {
		
	}
	
	@Override
	public void setzen(final int x, final int y, final int dx, final int dy) {

		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		
	}

}
