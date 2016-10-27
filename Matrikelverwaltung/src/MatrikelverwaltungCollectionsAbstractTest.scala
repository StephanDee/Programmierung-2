import org.junit.Assert.assertEquals
import org.junit.Test

abstract class MatrikelverwaltungCollectionsAbstractTest extends MatrikelverwaltungAbstractTest {
  

  /** Nur die Implementierung der Matrikelverwaltung durch ein Feld kennt eine Gr��enbeschr�nkung. 
   * Daher hier ein Test, in dem doppelt so viele Eintr�ge wie bei der MatrikelverwaltungArrayOfEintragImpl
   * eingetragen werden.
  */
  @Test def vieleEintragen() {
    testEintragen4()
    assertEquals(4, testling.letzteVergebeneMatrikelnr)
    _eintragen4()
    assertEquals(8, testling.letzteVergebeneMatrikelnr)
  }
  

}