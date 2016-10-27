package diagr

import javax.swing.JFrame
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.junit.Assert.{assertEquals, fail}
import javax.swing.JComponent
import java.util.LinkedList
import java.util.Arrays
import java.util.ArrayList

/**
 * Testtreiber für DiagrammImpl. Dieser Testtreiber zeigt das bearbeitete Diagramm in einem Fenster an.
 * 
 * 
 * This object is responsible for setting up and tearing down a test class fixture.
 * Works well when running as ScalaTest in IntelliJ IDEA or as JUnit 4 test in Eclipse. 
 * Wrong behavior when running als JUnit 4 test in IntelliJ IDEA: @BeforeClass runs after all @Test methods!
 * 
 * @version 2014-01-16 Unempfindlich gegen Reihenfolge der Ausführungen der Testmethoden.
 * @author Christoph Knabe
 * @since 2013-11-19
 */
object DiagrammTest {
  
  import org.junit.{ BeforeClass, AfterClass}

  private val _name = getClass.getName
  private val _displayName = if(_name endsWith "$") _name.substring(0,_name.length-1) else _name
  
  private val _figuren = FigurAnzeige.gesicht(368, 273) // Breite,Höhe
  private lazy val (_diagramm, _zeichenbereich) = _sichtenAufteilen(new DiagrammImpl)
  private var _gefuellt = false
  private val _frame = new JFrame(_displayName)
  private val _contentPane: java.awt.Container = _frame.getContentPane
  private val _kopf = _figuren(0)

  /**Liefert 2 Sichten auf die Diagramm-Implementierung: als Diagramm und als JComponent.
   * Dieses setzt voraus, dass DiagrammImpl beide Typen erweitert oder implementiert.
   * Das Diagramm wird gefüllt mit den Figuren aus _figuren.*/
  private def _sichtenAufteilen(impl: DiagrammImpl): (Diagramm, JComponent) = {
    (impl, impl)
  }
  
  /**Ein Test-Schritt ist eine parameterlose Funktion, die nichts liefert.*/
  type Schritt = ()=>Unit
  
  /**Bequeme Verpackung für javax.swing.SwingUtilities.invokeLater*/
  def invokeLater(schritt: Schritt){
    //Plane einen Auftrag für den Ereignisbehandler:
    //Erzeugen und Anzeigen der Benutzungsoberfläche für dieses Programm.
    javax.swing.SwingUtilities.invokeLater(new Runnable(){
      def run(){
	    schritt.apply()
      }
    });    
  }
  
  private def _repaint(){
      invokeLater(()=>_zeichenbereich.repaint())
      Thread.sleep(500) //halbe Sekunde warten    
  }
    
  @BeforeClass
  def setUpClass() { 
    println(_displayName + ": Setting up test class using @BeforeClass.") 
    invokeLater{ () =>
	  _frame.setSize(400, 300)
      _contentPane.add(_zeichenbereich, java.awt.BorderLayout.CENTER) // möglichst groß
      _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
      _frame.setVisible(true)
    }
  }

  @AfterClass
  def tearDownClass() { 
    println(_displayName + ": Tearing down test class using @AfterClass.") 
	Thread.sleep(2000) //5 Sekunden Nachanzeigedauer
  }
  
}

@RunWith(classOf[JUnit4])
class DiagrammTest {

  import DiagrammTest._
  import org.junit.{ After, Before, Test, Ignore }

  @Before
  def setUp() { println(_displayName + ": Setting up test using @Before.") }

  @After
  def tearDown() { println(_displayName + ": Tearing down test using @After.") }
  
  private def _fuellungGewaehrleisten(){    
    if(_gefuellt)return;
    for(figur <- _figuren){
      _diagramm.eintragen(figur)
      _repaint()
      assertEquals(figur, _diagramm.markierteFigur)
      assertEquals(true, _diagramm.isZwecksVerschieben)
    }
    _gefuellt = true
  }

  @Test def demoScalaList(){
    import scala.collection.immutable.List
    import figuren._
    val keineFiguren = List[Figur]()
    val kreis = new Kreis;   kreis.setzen(100, 40, 20, 20)
    val ellipse = new Ellipse;   ellipse.setzen(20, 40, 70, 15)
    val rechteck = new Rechteck;   rechteck.setzen(50,40, 30, 60)
    val raute = new Raute;   raute.setzen(40, 70, 25, 50)
    
    //Eine Liste mit 3 Figuren erzeugen:
    val dreiFiguren = List(kreis, ellipse, rechteck)
    var alleFiguren = dreiFiguren
    
    //Eine Figur hinzufügen:
    alleFiguren = raute :: alleFiguren
    assertEquals(List("Raute", "Kreis", "Ellipse", "Rechteck"), alleFiguren.map(_.getClass.getSimpleName))
    
    //Alle Figuren durchlaufen:
    for(figur <- alleFiguren) println("Figurart: " + figur.getClass.getSimpleName)
    
    //Eine Figur, hier die Ellipse, aus der Liste löschen:
    alleFiguren = alleFiguren.filter(_ != ellipse)
    assertEquals(List("Raute", "Kreis", "Rechteck"), alleFiguren.map(_.getClass.getSimpleName))
  }

  @Test def demoJavaList(){
    import java.util.List
    import figuren._
    val keineFiguren: List[Figur] = new LinkedList[Figur]()
    val kreis = new Kreis;   kreis.setzen(100, 40, 20, 20)
    val ellipse = new Ellipse;   ellipse.setzen(20, 40, 70, 15)
    val rechteck = new Rechteck;   rechteck.setzen(50,40, 30, 60)
    val raute = new Raute;   raute.setzen(40, 70, 25, 50)
    
    //Eine unveränderliche Liste mit 3 Figuren erzeugen:
    val dreiFiguren = Arrays.asList(kreis, ellipse, rechteck)
    //Den Inhalt in eine veränderliche LinkedList überehmen:
    val alleFiguren: List[Figur] = new ArrayList[Figur](dreiFiguren)
    
    //Eine Figur hinzufügen:
    alleFiguren.add(raute)
    import scala.collection.JavaConverters._
    assertEquals(Arrays.asList("Kreis", "Ellipse", "Rechteck", "Raute").asScala, alleFiguren.asScala.map(_.getClass.getSimpleName))
    
    //Alle Figuren durchlaufen:
    //Geht in Java direkt mit einer for-each-Schleife. 
    //Die entsprechende Scala-Schleife kann ich hier erst nach Konversion zu einer Scala-List anwenden.
    for(figur <- alleFiguren.asScala) println("Figurart: " + figur.getClass.getSimpleName)
    
    //Eine Figur, hier die Ellipse, aus der Liste löschen:
    alleFiguren.remove(ellipse)
    assertEquals(scala.List("Kreis", "Rechteck", "Raute"), alleFiguren.asScala.map(_.getClass.getSimpleName))
  }
  
  @Test def testEintragen() {
    _fuellungGewaehrleisten()
  }

  @Test def testMarkieren() {
    _fuellungGewaehrleisten()
    val linkesAuge = _figuren(1)

    _diagramm.markieren(70, 77)//"linkes Auge", true
    _repaint()
    assertEquals(linkesAuge, _diagramm.markierteFigur)
	assertEquals(true, _diagramm.isZwecksVerschieben)

    _diagramm.markieren(114, 115)//"linkes Auge", false
    _repaint()
    assertEquals(linkesAuge, _diagramm.markierteFigur)
	assertEquals(false, _diagramm.isZwecksVerschieben)

	val rechtesAuge = _figuren(2)
    _diagramm.markieren(245, 79)//"rechtes Auge", true
    _repaint()
    assertEquals(rechtesAuge, _diagramm.markierteFigur)
    assertEquals(true, _diagramm.isZwecksVerschieben)

    _diagramm.markieren(295, 118)//"rechtes Auge", false
    _repaint()
    assertEquals(rechtesAuge, _diagramm.markierteFigur)
	assertEquals(false, _diagramm.isZwecksVerschieben)

	val nase = _figuren(3)
    _diagramm.markieren(157, 106)//"Nase", true
    _repaint()
    assertEquals(nase, _diagramm.markierteFigur)
	assertEquals(true, _diagramm.isZwecksVerschieben)

    _diagramm.markieren(207, 159)//"Nase", false
    _repaint()
    assertEquals(nase, _diagramm.markierteFigur)
	assertEquals(false, _diagramm.isZwecksVerschieben)

	_diagramm.entmarkieren()
    _repaint()
	try {
	  _diagramm.markierteFigur
	  fail("Diagramm.KeineFigurMarkiertExc")
	} catch {
	  case expected: _diagramm.KeineFigurMarkiertExc =>
	}

	val mund = _figuren(4)
    _diagramm.markieren(115, 180)//"Mund", true
    _repaint()
    assertEquals(mund, _diagramm.markierteFigur)
	assertEquals(true, _diagramm.isZwecksVerschieben)

    _diagramm.markieren(252, 204)//"Mund", false
    _repaint()
    assertEquals(mund, _diagramm.markierteFigur)
	assertEquals(false, _diagramm.isZwecksVerschieben)

    _diagramm.markieren(33, 29)//"Kopf", true
    _repaint()
    assertEquals(_kopf, _diagramm.markierteFigur)
	assertEquals(true, _diagramm.isZwecksVerschieben)

    _diagramm.markieren(326, 245)//"Kopf", false
    _repaint()
    assertEquals(_kopf, _diagramm.markierteFigur)
	assertEquals(false, _diagramm.isZwecksVerschieben)
  }

  @Test def testLoeschen() {
    _fuellungGewaehrleisten()
    _diagramm.markieren(326, 245)//"Kopf", false
    _repaint()
    assertEquals(_kopf, _diagramm.markierteFigur)
	assertEquals(false, _diagramm.isZwecksVerschieben)
	_diagramm.loeschen()
    _repaint()
	try {
	  _diagramm.markierteFigur
	  fail("Diagramm.KeineFigurMarkiertExc")
	} catch {
	  case expected: _diagramm.KeineFigurMarkiertExc =>
	}
	try {
	  _diagramm.loeschen()
	  fail("Diagramm.KeineFigurMarkiertExc")
	} catch {
	  case expected: _diagramm.KeineFigurMarkiertExc =>
	}
	//Wiederherstellen des Kopfes:
	_diagramm.eintragen(_kopf)
    _repaint()
    _diagramm.entmarkieren()
    _repaint()
  }
  
	
}