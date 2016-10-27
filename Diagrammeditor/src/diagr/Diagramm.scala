package diagr; //Diagramm.scala

//Pl�ne:
// ---

//�nderungshistorie:
//1999-02-22  Knabe  Informatoren/Mutatoren ohne Namenspraefix, stattdessen Kennzeichnung im Javadoc.
//2008-12-05  Knabe  Umgestellt auf Swing statt AWT
//2003-01-27  Knabe  Umgestellt auf MulTEx 3e
//2000-12-20  Knabe  Meldungstext im Ausnahmeobjekt
//2000-12-19  Knabe  Umgestellt auf MulTEx 1b
//1999-11-23  Knabe  mut_laden hier statt static in DiagrammImpl
//1999-03-23  Knabe  Umgestellt auf mit Object[] parametrierte lib.Exc
//1999-02-15  Knabe  Alle Figuren als aeussere Klassen in Paket figuren
//1999-01-15  Knabe  erweitert um speichern()
//1998-11-20  Knabe  umgestellt auf Markieren mittels Maus-Koordinaten
//1998-11-02  Knabe  erstellt

/** Ein Diagramm ist eine Menge von dazu geh�rigen Figuren.
* Zwecks Schnittstellenverbindlichkeit ist dies eine abstrakte Klasse.
* Sie soll durch eine Klasse DiagrammImpl implementiert werden.
*/
trait Diagramm {


	//Ausnahmen:
	class KeineFigurMarkiertExc extends RuntimeException("Zur Zeit ist keine Figur f�r Editoroperationen markiert")
	
	/** Informator: Zeichnet den Diagramminhalt auf den angegebenen Graphik-Kontext.
	  Redefiniert paintComponent() von JComponent.
	  <P>
	  Die aktuelle markierte Figur wird dabei von einem um ein Pixel gr��eren
	  farbigen Rechteck umgeben. An diesem Rechteck sind die beiden
	  Markierungspunkte je durch ein kleines Kreuz hervorgehoben: <UL>
	    <LI> der Ortspunkt durch ein achsenparalleles Kreuzchen
	    <LI> der Bildpunkt durch ein diagonalenparalleles Kreuzchen
	  </UL>
	  Der Markierungszweck wird noch wie folgt
	  hervorgehoben: <UL>
	    <LI>zwecks Verschieben: Farbe gr�n
	    <LI>zwecks Ver�ndern:   Farbe rot
	  <UL>
	  @param graphics der Grafikkontext, in den hinein gezeichnet wird.
	*/
	@Override
	def paintComponent(graphics: java.awt.Graphics): Unit
	
	/** Mutator: Traegt figur in das Diagramm ein. figur ist danach zwecksVerschieben markiert.*/
	def eintragen(figur: figuren.Figur): Unit
	
	/** Mutator: Loescht die markierte Figur aus dem Diagramm. 
	 *  @throws KeineFigurMarkiertExc Keine Figur im Diagramm ist markiert. Daher kann keine gel�scht werden.*/
	def loeschen(): Unit
	
	/** Liefert die markierte Figur.
	 *  @throws KeineFigurMarkiertExc Keine Figur im Diagramm ist markiert. Daher kann keine solche geliefert werden.*/
	def markierteFigur: figuren.Figur
	
	/** Mutator: Markiert eine Figur zwecksVerschieben bzw. zwecksSkalieren.
	  Bestimmt dazu den Markierungspunkt, der den Koordinaten (x, y) am
	  n�chsten ist. Jede Figur hat dazu zwei Markierungspunkte: <BR><UL>
	    <LI> den Ortspunkt zwecks Verschieben
	    <LI> den Bildpunkt zwecks Skalieren
	  </UL>
	  Je nachdem, welcher der beiden Markierungspunkte n�her ist, wird auch der
	  Markierungszweck entsprechend festgelegt und ist sp�ter mit dem Informator
	  <CODE> boolean isZwecksVerschieben(); </CODE> abfragbar.
	  <P>
	  Die markierte Figur wird in <CODE>paintComponent(Graphics)</CODE> hervorgehoben gezeichnet.
	*/
	def markieren(x: Int, y: Int): Unit
	
	/** Hebt eine eventuell vorhandene Markierung auf. */
	def entmarkieren(): Unit
	;
	/** Result: true,  falls Figur zwecks Verschieben markiert wurde,
	            false, falls Figur zwecks Skalieren   markiert wurde.
	*/
	def isZwecksVerschieben: Boolean


}