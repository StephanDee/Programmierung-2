import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**Testtreiber fuer Aufgabe 4 in Pr2 im WS13*/
@RunWith(classOf[JUnit4])
class MatrikelverwaltungListOfEintragTest extends MatrikelverwaltungCollectionsAbstractTest {

	override
	def testlingErzeugen: Matrikelverwaltung = new MatrikelverwaltungListOfEintragImpl
	
}