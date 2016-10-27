package figuren;

import java.awt.Graphics;

public class Raute extends Flaeche {

	@Override
	public void zeichnen(Graphics graphics) {
		
		final int x2 = this.getXL() + (this.dx / 2);
		final int x3 = this.getXR();
		
		final int y1 = (this.getYO() + this.getYU()) / 2;
		final int y2 = this.getYO();
		final int y3 = y1;
		final int y4 = this.getYU();
		
		final int xpoints[] = {this.getXL(), x2, x3, x2};
		final int ypoints[] = {y1, y2, y3, y4};
		
		graphics.drawPolygon(xpoints, ypoints, xpoints.length);
		
	}
}
