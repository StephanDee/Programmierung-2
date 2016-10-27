package figuren;

import java.awt.Graphics;

public class Strecke extends AbstrakteFigur {

	@Override
	public void zeichnen(Graphics graphics) {
		
		graphics.drawLine(this.x, this.y, this.x + this.dx, this.y + this.dy);
		
	}

}
