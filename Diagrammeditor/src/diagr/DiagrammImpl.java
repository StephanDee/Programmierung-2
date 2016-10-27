package diagr;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import javax.swing.JComponent;
import figuren.Figur;

public class DiagrammImpl extends JComponent implements Diagramm  {
	
	LinkedList<Figur> _figuren = new LinkedList<Figur>();
	Figur markierteFigur = null;
	boolean isZwecksVerschieben;

	private static final long serialVersionUID = 2900697490292076507L;

	@Override
	public void eintragen(final Figur figur) {
		
		_figuren.add(figur);
		markierteFigur = figur;
		isZwecksVerschieben = true;
		
	}

	@Override
	public void loeschen() {
		
		if (markierteFigur == null) {
			throw new KeineFigurMarkiertExc();
		}
		
		for (Figur figur : _figuren) {
			
			if (figur.equals(markierteFigur)) {
				entmarkieren();
				_figuren.remove(figur);
				break;
			}
			
		}
		
	}

	@Override
	public Figur markierteFigur() {
		
		if (markierteFigur == null) {
			throw new KeineFigurMarkiertExc();
		}
		
		return markierteFigur;
	}

	@Override
	public void markieren(final int x, final int y) {
		
		int minAbstand = Integer.MAX_VALUE;
		isZwecksVerschieben = true;
		
		for (Figur figur : _figuren) {
			
			if (figur.abstandZumOrtspunkt(x, y) < minAbstand) {
				minAbstand = figur.abstandZumOrtspunkt(x, y);
				markierteFigur = figur;
				isZwecksVerschieben = true;
				figur.markierungMitteilen();
			}
			
			if (figur.abstandZumBildpunkt(x, y) < minAbstand) {
				minAbstand = figur.abstandZumBildpunkt(x, y);
				markierteFigur = figur;
				isZwecksVerschieben = false;
				figur.markierungMitteilen();
			}
			
		}
		
	}

	@Override
	public void entmarkieren() {
		markierteFigur.entmarkierungMitteilen();
		markierteFigur = null;
		
	}

	@Override
	public boolean isZwecksVerschieben() {
		return isZwecksVerschieben;
	}


	@Override
	public void paintComponent(Graphics graphics) {
		
			for (Figur figur : _figuren) {
				figur.zeichnen(graphics);
			}
		
			if (markierteFigur != null) {
		
				graphics.setColor(Color.RED);
				
				if (isZwecksVerschieben == true) {
					graphics.setColor(Color.GREEN);
				}
				
				/**
				 * Rechteck
				 */
				graphics.drawRect(markierteFigur.getX(), markierteFigur.getY(), (markierteFigur.getXR() - markierteFigur.getX()), (markierteFigur.getYU()- markierteFigur.getY()));
				
				/**
				 * achsenparalleles Kreuz (am Ortspunkt)
				 */
				graphics.drawLine(markierteFigur.getXL(), markierteFigur.getYO() - 20, markierteFigur.getXL(), markierteFigur.getYO() + 20);
				graphics.drawLine(markierteFigur.getXL() - 20, markierteFigur.getYO(), markierteFigur.getXL() + 20, markierteFigur.getYO());
	
				/**
				 * diagonalparalleles Kreuz (am Bildpunkt)
				 */
				graphics.drawLine(markierteFigur.getXR() - 20, markierteFigur.getYU() - 20, markierteFigur.getXR() + 20, markierteFigur.getYU() + 20);
				graphics.drawLine(markierteFigur.getXR() - 20, markierteFigur.getYU() + 20, markierteFigur.getXR() + 20, markierteFigur.getYU() - 20);
			
			}
		
	}

}
