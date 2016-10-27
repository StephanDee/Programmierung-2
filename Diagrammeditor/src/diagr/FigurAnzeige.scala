package diagr

//Historie:
//2013-11-01  Knabe  Portiert von Java nach Scala
//2008-11-24  Knabe  Umstellung auf Swing, ohne Datenfluss-Präfixe i_, o_, io_
//2000-03-10  Knabe  Ohne Behandlung von figuren.Figur.UnzulaessigerAttributwert
//1999-11-23  Knabe  Frame-Titel mittels .class.getName() ermittelt
//1999-02-22  Knabe  Auch 3 Strecken als Haare, 2 Ellipsen als Ohren
//1999-10-12  Knabe  Bestimmung einer Figur durch Ortspunkt und Bildpunkt
//1999-02-15  Knabe  Alle Figuren als aeussere Klassen in Paket figuren
//1999-01-28  Knabe  Informatoren ohne Praefix 'i_', zeichnen(Graphics)
//1998-11-26  Knabe  Umstellung auf figuren.Figur, figuren.Rechteck, ...
//1998-11-06  Knabe  exportiert gesicht(b,h): Zusammenstellung des Gesichts
//1998-11-02  Knabe  erstellt aus GraphicsTest

import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.JComponent
import javax.swing.JFrame

/**Öffnet ein Fenster mit einem Bild aus Figur-en. Diese stellen ein Gesicht dar.*/
object FigurAnzeige extends App {
  
  val name = getClass.getName
  val title = if(name endsWith "$") name.substring(0,name.length-1) else name
  
  //Plane einen Auftrag für den Ereignisbehandler:
  //Erzeugen und Anzeigen der Benutzungsoberfläche für dieses Programm.
  javax.swing.SwingUtilities.invokeLater(new Runnable(){
    def run(){
      val frame = new JFrame(title)
      frame.setSize(300, 200);
	  frame.getContentPane().add(new _Zeichenbereich());
      frame.addWindowListener(new WindowAdapter(){
        override def windowClosing(ev: WindowEvent){
          System.err.println("Fenster " + frame.getTitle + " wird geschlossen. Programmende ...");
          System.exit(0);
        }
      });
      frame.setVisible(true);
    }
  });
  
  private class _Zeichenbereich extends JComponent {
    override def paintComponent(graphics: java.awt.Graphics) {
      val r: java.awt.Rectangle = getBounds();
      val breite = r.width - 1;
      val hoehe = r.height - 1;
      val alleFiguren: Array[figuren.Figur] = FigurAnzeige.gesicht(breite, hoehe);
      for (figur <- alleFiguren) {
        figur.zeichnen(graphics);
      }
    }
  }//_Zeichenbereich
  
  /**
   * Liefert Figuren, die ein Gesicht bilden. Im Einzelnen: 1 Ellipse als
   * Kopf, 2 Kreise als Augen, 1 Raute als Nase, 1 Rechteck als Mund, 2
   * Ellipsen als Ohren, 3 Strecken als Haare
   */
  def gesicht(breite: Int, hoehe: Int): Array[figuren.Figur] = {
    val kopf = new figuren.Ellipse();
    val kopfY = hoehe / 10;
    kopf.setzen(breite / 10, kopfY, breite * 8 / 10, hoehe * 8 / 10);

    val augeY = kopf.getY() + kopf.getDY() / 5;
    val augeB = Math.min(kopf.getDX(), kopf.getDY()) / 4;
    val linkesAuge = new figuren.Kreis();
    linkesAuge.setzen(kopf.getX() + kopf.getDX() / 10, augeY, augeB, kopf.getDY()/*Soll auf augeB reduziert werden!*/ );

    val rechtesAuge = new figuren.Kreis();
    rechtesAuge.setzen(kopf.getX() + kopf.getDX() * 9 / 10 - augeB, augeY, kopf.getDY()/*Soll auf augeB reduziert werden!*/, augeB);

    val nase = new figuren.Raute();
    nase.setzen(kopf.getX() + kopf.getDX() * 4 / 10, kopf.getY() + kopf.getDY() / 3, kopf.getDX() * 2 / 10, kopf.getDY() / 3);

    val mund = new figuren.Rechteck();
    mund.setzen(kopf.getX() + kopf.getDX() / 4, kopf.getY() + kopf.getDY() * 7 / 10, kopf.getDX() / 2, kopf.getDY() / 8);

    val linkerZahn = new figuren.Quadrat();
    val zahnB = mund.getDX()/10;
    linkerZahn.setzen(mund.getX()+zahnB, mund.getY(), zahnB, mund.getDX()/*Soll auf zahnB reduziert werden!*/ );

    val rechterZahn = new figuren.Quadrat();
    rechterZahn.setzen(mund.getX()+8*zahnB, mund.getY(), mund.getDX()/*Soll auf zahnB reduziert werden!*/, zahnB );

    val ohrBreite = kopf.getDX() / 20;
    val ohrHoehe = kopf.getDY() * 3 / 10;
    val ohrY = kopf.getY() + (kopf.getDY() - ohrHoehe) / 2;
    val linkesOhr = new figuren.Ellipse();
    linkesOhr.setzen(kopf.getX() - ohrBreite, ohrY, ohrBreite, ohrHoehe);
    val rechtesOhr = new figuren.Ellipse();
    rechtesOhr.setzen(kopf.getXR(), ohrY, ohrBreite, ohrHoehe);

    val haarBreite = kopf.getDX() / 10;
    val haarHoehe = kopf.getDY() / 10;

    val linkesHaar = new figuren.Strecke();
    linkesHaar.setzen(kopf.getX() + haarBreite, kopf.getY(), haarBreite, haarHoehe);

    val rechtesHaar = new figuren.Strecke();
    rechtesHaar.setzen(kopf.getXR() - 2 * haarBreite, kopf.getY() + haarHoehe, haarBreite, -haarHoehe);

    val mittelHaar = new figuren.Strecke();
    mittelHaar.setzen(kopf.getX() + kopf.getDX() / 2, kopf.getY() - haarHoehe, 0, haarHoehe);

    val result = Array[figuren.Figur](
      kopf, linkesAuge, rechtesAuge, nase,
      mund, linkerZahn, rechterZahn,
      linkesOhr, rechtesOhr,
      linkesHaar, rechtesHaar, mittelHaar
    )
    result
  }//gesicht

}//FigurAnzeige

