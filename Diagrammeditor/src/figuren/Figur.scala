package figuren

/**
 * Gemeinsamer Trait/Interface fuer alle 2D-Figuren, die in einem Diagramm auftreten
 * können. Jede Figur wird durch 2 Punkte bestimmt:
 * <ul>
 * <li> einen Ortspunkt und</li>
 * <li> einen Bildpunkt.</li>
 * </ul>
 * <p>
 * Der Ortspunkt (x,y) legt die x,y-Koordinaten der Figur und damit ihre
 * Position im 2D-Kontext fest. Es gilt wie in java.awt die Einschränkung: x>=0 &&
 * y>=0. Dabei entspricht linker Bildrand: x==0, oberer Bildrand: y==0.</p>
 * <p>
 * Der Bildpunkt wird durch seine Distanzen in x,y-Richtung (dx,dy) vom
 * Ortspunkt angegeben. Er legt das Bild (Aussehen) der Figur fest.
 * Nötige Einschränkungen des Wertebereichs von (dx,dy) sollten in der jeweiligen
 * Redefinition der Methode setzen(x, y, dx, dy) durchgesetzt werden.</p>
 * <p>
 * Wie die Figur aussieht, hängt vom Bildpunkt und der Figurart ab und wird in
 * der jeweiligen Redefinition der Methode zeichnen(Graphics) beschrieben.</p>
 * <p>
 * Die im Entwurf gefundene abstrakte Klasse Figur wird zwecks Arbeitsteilung
 * aufgespalten in das von mir (Knabe) gelieferte Interface Figur und die von
 * Ihnen zu liefernde abstrakte Klasse AbstrakteFigur. Diese muss dann von den
 * verschiedenen Figurarten beerbt werden. Alle Figur-Klassen gehören zum Paket
 * figuren.</p>
 */
trait Figur {

	/** x-Koordinate des Ortspunktes der Figur. Nur lesend. */
	def getX(): Int

	/** y-Koordinate des Ortspunktes der Figur. Nur lesend. */
	def getY(): Int

	/** x-Anteil des Vektors vom Ortspunkt zum Bildpunkt. Nur lesend. */
	def getDX(): Int

	/** y-Anteil des Vektors vom Ortspunkt zum Bildpunkt. Nur lesend. */
	def getDY(): Int

	/**
	 * Setzt die entsprechenden Attributwerte. Dabei werden die Werte derartig normiert,
	 * dass die graphischen Figur-Eigenschaften eingehalten werden. Ändernd.
	 */
	def setzen(x: Int,
		y: Int,
		dx: Int,
		dy: Int): Unit

	/** x-Koordinate des rechten Randes des umgebenden Rechtecks. Nur lesend. */
	def getXR(): Int

	/** x-Koordinate des linken Randes des umgebenden Rechtecks. Nur lesend. */
	def getXL(): Int

	/** y-Koordinate des oberen Randes des umgebenden Rechtecks. Nur lesend. */
	def getYO(): Int

	/** y-Koordinate des unteren Randes des umgebenden Rechtecks. Nur lesend. */
	def getYU(): Int

	/**
	 * Zeichnet die Figur in den Graphik-Kontext graphics mit dessen
	 * aktuellen Einstellungen. Nur lesend.
	 */
	def zeichnen(graphics: java.awt.Graphics): Unit

	/**
	 * Liefert nichtnegative Maßzahl für den Abstand des Punktes (x,y) zum
	 * Ortspunkt der Figur. Je kleiner der Betrag, desto näher dran. Nur lesend.
	 */
	def abstandZumOrtspunkt(x: Int, y: Int): Int

	/**
	 * Liefert nichtnegative Maßzahl für den Abstand des Punktes (x,y) zum
	 * Bildpunkt der Figur. Je kleiner der Betrag, desto näher dran. Nur lesend.
	 */
	def abstandZumBildpunkt(x: Int, y: Int): Int

	/**
	 * Soll aufgerufen werden, wenn diese Figur markiert worden ist. Ändernd.
	 *  Nur nötig, wenn ein figurspezifischer Editierdialog erscheinen soll.
	 */
	def markierungMitteilen(): Unit

	/** Soll aufgerufen werden, wenn diese Figur entmarkiert worden ist. Ändernd. Nur nötig, wenn ein figurspezifischer Editierdialog verschwinden soll.*/
	def entmarkierungMitteilen(): Unit

}