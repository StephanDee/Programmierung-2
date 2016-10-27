package figuren;

public class Quadrat extends Rechteck {

	@Override
	public void setzen(final int x, final int y, final int dx, final int dy) {
		
		super.symmetrischSetzen(x, y, dx, dy);
	}

}
