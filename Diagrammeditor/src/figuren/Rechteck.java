package figuren;

import java.awt.Graphics;

public class Rechteck extends Flaeche {

	@Override
	public void zeichnen(final Graphics graphics) {
		graphics.drawRect(this.x, this.y, this.dx, this.dy);
	}

}
