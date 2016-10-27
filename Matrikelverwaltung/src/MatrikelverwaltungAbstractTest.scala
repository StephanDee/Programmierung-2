import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.fail

/**Zweck: JUnit4-Testtreiber fuer Implementierung des Interfaces Matrikelverwaltung mit Kapazität 4 (z.B. durch einen Array implementiert).
 * Fuer Uebung 03/04 in Pr 2 im WS 13.
 * @author Christoph Knabe 
 * @version 2013-10-11 Portiert nach Scala.*/
abstract class MatrikelverwaltungAbstractTest {

  /**Wird in Java als Zugriffsmethode testling() angeboten.*/
  val testling: Matrikelverwaltung = testlingErzeugen
    
  /**Erzeugt und liefert eine Matrikelverwaltung-Implementierung. Muss in konkreten Unterklassen definiert werden.*/
  def testlingErzeugen: Matrikelverwaltung
    
  @Test def testLeereMatrikelverwaltung {
    assertEquals(0, testling.letzteVergebeneMatrikelnr)
	 try{
	    	testling.name(1)
	    	fail("Matrikelverwaltung.MatrikelnrUnbekannt expected")
	} catch {case expected: testling.MatrikelnrUnbekannt =>}
	try{
	    	testling.matrikelnr("a")
	    	fail("Matrikelverwaltung.NameUnbekannt expected")
	} catch {case expected: testling.NameUnbekannt =>}
	assertEquals(0, testling.letzteVergebeneMatrikelnr)
  }
	
  /** Fuellt den _testling mit 4 Namen. */
  protected def _eintragen4(){
    testling.eintragen("Knabe")
    testling.eintragen("Schimkat")
    testling.eintragen("Grude")
    testling.eintragen("Pavlista")
  }

  /** Fuellt den _testling mit 4 Namen und testet dabei die Argumentpruefung von eintragen sowie die Vergabe der Matrikelnummern. */
  @Test def testEintragen4(){
    testling.eintragen("Knabe")
    assertEquals(1, testling.letzteVergebeneMatrikelnr)
    testling.eintragen("Schimkat")
    assertEquals(2, testling.letzteVergebeneMatrikelnr)
    testling.eintragen("Grude")
    assertEquals(3, testling.letzteVergebeneMatrikelnr)
    testling.eintragen("Pavlista")
    assertEquals(4, testling.letzteVergebeneMatrikelnr)
    try{
      testling.eintragen(null)
	  fail("IllegalArgumentException expected")
	} catch {case expected: IllegalArgumentException =>}
	try{
	  testling.eintragen("")
	  fail("IllegalArgumentException expected")
	} catch {case expected: IllegalArgumentException =>}
	try{
	  testling.eintragen(new String())
	  fail("IllegalArgumentException expected")
	} catch {case expected: IllegalArgumentException =>}
  }

  @Test def testNameMatrikelnr(){
    _eintragen4()
		
	val grude = new String("Grude") //Neue Objektidentitaet
	val schimkat = new String("Schimkat")  //Neue Objektidentitaet
	val knabe = new String("Knabe")  //Neue Objektidentitaet
	val pavlista = new String("Pavlista")  //Neue Objektidentitaet

	//Diese Testfaelle sind auch bei Vergleich mit == (Objektidentitaet) erfolgreich:
	assertEquals("Grude", testling.name(3))
	assertEquals("Schimkat", testling.name(2))
	assertEquals("Knabe", testling.name(1))
	assertEquals("Pavlista", testling.name(4))
	
	assertEquals(grude, testling.name(3))
	assertEquals(schimkat, testling.name(2))
	assertEquals(knabe, testling.name(1))
	assertEquals(pavlista, testling.name(4))

	assertEquals(2, testling.matrikelnr("Schimkat"))
	assertEquals(1, testling.matrikelnr("Knabe"))
	assertEquals(3, testling.matrikelnr("Grude"))
	assertEquals(4, testling.matrikelnr("Pavlista"))
		
	assertEquals(2, testling.matrikelnr(schimkat))
	assertEquals(1, testling.matrikelnr(knabe))
	assertEquals(3, testling.matrikelnr(grude))
	assertEquals(4, testling.matrikelnr(pavlista))

	try{
	  testling.matrikelnr(null)
	  fail("IllegalArgumentException expected")
	} catch {case expected: IllegalArgumentException =>}
	try{
	  testling.matrikelnr("")
	  fail("IllegalArgumentException expected")
    } catch {case expected: IllegalArgumentException =>}
	try{
	  testling.matrikelnr(new String())
	  fail("IllegalArgumentException expected")
	} catch {case expected: IllegalArgumentException =>}
  }
	
  @Test def testAustragen2(){
    _eintragen4()
		
	testling.austragen(2)  //Schimkat
	try{
	  testling.name(2)
	  fail("Matrikelverwaltung.MatrikelnrUnbekannt expected")
	} catch {case expected: testling.MatrikelnrUnbekannt =>}
	try{
	  testling.matrikelnr("Schimkat")
	  fail("Matrikelverwaltung.NameUnbekannt expected")
	} catch{case expected: testling.NameUnbekannt =>}
	//Letzte vergebene Matrikelnr. ist 4, obwohl nur noch 3 Namen eingetragen sind:
	assertEquals(4, testling.letzteVergebeneMatrikelnr)
  }

  @Test def testEintragen5anStelle2(){
    _eintragen4()
	testling.austragen(2);  //Schimkat
		
	testling.eintragen("Steyer")
	assertEquals("Steyer", testling.name(5))
	assertEquals(5, testling.matrikelnr("Steyer"))
	assertEquals(5, testling.letzteVergebeneMatrikelnr)
  }
	
  @Test def testEintragen5anStelle4(){
    _eintragen4()
	assertEquals(4, testling.letzteVergebeneMatrikelnr)
	testling.austragen(4)  //Pavlista
	assertEquals(4, testling.letzteVergebeneMatrikelnr)
		
	testling.eintragen("Steyer")
	assertEquals("Steyer", testling.name(5))
	assertEquals(5, testling.matrikelnr("Steyer"))
	assertEquals(5, testling.letzteVergebeneMatrikelnr)
  }

		  
}