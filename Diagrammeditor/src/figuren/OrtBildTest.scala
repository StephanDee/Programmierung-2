package figuren

import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Zweck: JUnit4-Testtreiber für die Funktionalität von Ortspunkt und Bildpunkt einer Figur.
 * Wird getestet anhand der konkreten Figurarten Raute und Strecke.
 * Für Diagrammeditor in Pr 2 im WS 13.
 * @author Christoph Knabe
 * @version 2013-11-01 Erstellt in Scala.
 */
@RunWith(classOf[JUnit4])
class OrtBildTest {

	@Test def testRauteDefault {
		val figur: Figur = new Raute
		assertEquals(1, figur.getX)
		assertEquals(1, figur.getY)
		assertEquals(50, figur.getDX)
		assertEquals(70, figur.getDY)
	}

	@Test def testRauteSetzen {
		val figur: Figur = new Raute
		figur.setzen(2, 4, 8, 16)
		assertEquals(2, figur.getX)
		assertEquals(4, figur.getY)
		assertEquals(8, figur.getDX)
		assertEquals(16, figur.getDY)
		assertEquals(2, figur.getXL)
		assertEquals(10, figur.getXR)
		assertEquals(4, figur.getYO)
		assertEquals(20, figur.getYU)
	}

	@Test def testFlaecheSetzenNegativ { //TODO Dito für andere flächige Figurarten
		val flaechen: Array[Flaeche] = Array(new Ellipse, new Kreis, new Rechteck, new Quadrat, new Raute)
		for (flaeche <- flaechen) {
			flaeche.setzen(2, 4, -8, -16)
			val hinweis = flaeche.getClass.getSimpleName
			assertEquals(hinweis, 2, flaeche.getX)
			assertEquals(hinweis, 4, flaeche.getY)
			assertEquals(hinweis, 1, flaeche.getDX)
			assertEquals(hinweis, 1, flaeche.getDY)
			assertEquals(hinweis, 2, flaeche.getXL)
			assertEquals(hinweis, 3, flaeche.getXR)
			assertEquals(hinweis, 4, flaeche.getYO)
			assertEquals(hinweis, 5, flaeche.getYU)
		}
	}

	@Test def testStreckeDefault {
		val figur: Figur = new Strecke
		assertEquals(1, figur.getX)
		assertEquals(1, figur.getY)
		assertEquals(50, figur.getDX)
		assertEquals(70, figur.getDY)
	}

	@Test def testStreckeSetzenPositiv {
		val figur: Figur = new Strecke
		figur.setzen(2, 4, 8, 16)
		assertEquals(2, figur.getX)
		assertEquals(4, figur.getY)
		assertEquals(8, figur.getDX)
		assertEquals(16, figur.getDY)
		assertEquals(2, figur.getXL)
		assertEquals(10, figur.getXR)
		assertEquals(4, figur.getYO)
		assertEquals(20, figur.getYU)
	}

	@Test def testStreckeSetzenNegativ {
		val figur: Figur = new Strecke
		figur.setzen(2, 4, -8, -16)
		assertEquals(2, figur.getX)
		assertEquals(4, figur.getY)
		assertEquals(-8, figur.getDX)
		assertEquals(-16, figur.getDY)
		assertEquals(-6, figur.getXL)
		assertEquals(2, figur.getXR)
		assertEquals(-12, figur.getYO)
		assertEquals(4, figur.getYU)
	}

	@Test def testKreisSetzen {
		val figur: Figur = new Kreis
		figur.setzen(2, 4, 8, 16)
		assertEquals(2, figur.getX)
		assertEquals(4, figur.getY)
		assertEquals(8, figur.getDX)
		//Ein Kreis muss rotationssymmetrisch sein:
		assertEquals(8, figur.getDY)
		assertEquals(2, figur.getXL)
		assertEquals(10, figur.getXR)
		assertEquals(4, figur.getYO)
		assertEquals(12, figur.getYU)
	}

}
