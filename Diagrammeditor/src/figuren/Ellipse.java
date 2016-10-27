package figuren;

import java.awt.Graphics;

public class Ellipse extends Flaeche {

	@Override
	public void zeichnen(Graphics graphics) {
		graphics.drawOval(this.x, this.y, this.dx, this.dy);
	}

}
