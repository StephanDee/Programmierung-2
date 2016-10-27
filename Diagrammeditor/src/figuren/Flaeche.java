package figuren;

public abstract class Flaeche extends AbstrakteFigur {

	@Override
	public int getXL() {
		return this.x;
	}
	
	@Override
	public void setzen(final int x, final int y, final int dx, final int dy) {

		this.x = (x < 1) ? 1 : x;
		this.y = (y < 1) ? 1 : y;
		this.dx = (dx < 1) ? 1 : dx;
		this.dy = (dy < 1) ? 1 : dy;
		
	}
	
	public void symmetrischSetzen(final int x, final int y, int dx, final int dy) {
		
		if (dx > dy) {
			dx = dy;
		}
		
		this.x = (x < 1) ? 1 : x;
		this.y = (y < 1) ? 1 : y;
		this.dx = (dx < 1) ? 1 : dx;
		this.dy = (dy < 1) ? 1 : dx;
		
	}

}
