//Im WS 13 in Pr2: Aufgabe 2, war Aufgabe 9 in Pr1 im SS 13
//2013-10-04  Knabe  Portierung nach Scala
//1999-06-03  Knabe  javadoc-Kommentare
//1998-06-18  Knabe  Absicherung durch Ausnahmen
//1998-06-04  Knabe  Erstellung

/** Zweck: Verwaltet eine Warteschlange nach dem FIFO-Prinzip. Elementetyp ist String.
 *  Die Kapazität einer Implementierung soll mittels Konstruktorparameter wählbar sein. */
trait Queue {
    
  /** Die Queue ist schon leer. */
  class Underflow extends RuntimeException
  
  /** Die Queue ist mit anzahl Elementen schon voll. */
  class Overflow(anzahl: Int) extends RuntimeException(anzahl.toString)
  
  /** Hängt element an die Warteschlange an (Mutator).
  *  @throws Overflow kein Platz mehr in der Schlange */
  def insert(element: String): Unit
  
  /** Liefert das älteste, noch in der Warteschlange enthaltene Element (Informator).
   *  @throws Underflow Schlange leer */
  def read(): String

  /** Löscht das älteste Element aus der Warteschlange (Mutator)
   *  @throws Underflow Schlange leer */
  def delete(): Unit  

}