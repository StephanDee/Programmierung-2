//Im SS 13 in Pr1: Aufgabe 9, in WS 08 in IN3: Aufgabe 6
//1999-06-03  Knabe  javadoc-Kommentare
//1998-06-18  Knabe  Absicherung durch Ausnahmen
//1998-06-04  Knabe  Erstellung

/** Implementiert den Trait Queue (analog Java-Interface) in Java. */
class ScalaQueue(size: Int) extends Queue {

  /**
   * Das Feld, in welchem die Elemente abgelegt werden. Das älteste hat immer
   * den Index 0.
   */

  private val _inhalt = new Array[String](size)

  /** Anzahl eingetragener Elemente */
  private var _anzahl = 0

  def insert(element: String) {
    if (_anzahl >= _inhalt.length) {
      throw new Overflow(_anzahl)
    }
    _inhalt(_anzahl) = element
    _anzahl += 1
  }

  /**
   * Liefert das älteste, noch in der Warteschlange enthaltene Element (Informator).
   *  @throws Underflow Schlange leer
   */

  def read(): String = {
    _checkUnderflow()
    return _inhalt(0)
  }

  /**
   * Löscht das älteste Element aus der Warteschlange (Mutator)
   *  @throws Underflow Schlange leer
   */

  def delete() {
    _checkUnderflow()
    for (i <- 1 until _anzahl) {
      _inhalt(i - 1) = _inhalt(i)
    }
    _anzahl -= 1
    _inhalt(_anzahl) = null
  }

  private def _checkUnderflow() {
    if (_anzahl <= 0) {
      throw new Underflow
    }
  }
}