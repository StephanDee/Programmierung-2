//2013-10-11  Knabe  Portiert nach Scala
//2009-01-15  Knabe  Ohne Parameternamenskonvention i_, o_
//1999-10-01  Knabe  maxMatrikelnr umbenannt zu letzteMatrikelnr
//1999-07-07  Knabe  Ausnahmen parametriert
//1999-07-07  Knabe  Namenskonvention mut_, javadoc-Format
//1998-10-06  Knabe  Umstellung auf interface
//1998-07-07  Knabe  Erstellung

/** Zweck: Verwaltung einer eindeutigen Matrikelnummer je Name. */
trait Matrikelverwaltung {
  
  class Ueberlauf(anzahlEintraege: Int) extends RuntimeException(anzahlEintraege.toString)
  class NameUnbekannt(name: String) extends RuntimeException(name)
  class MatrikelnrUnbekannt(matrikelnr: Int) extends RuntimeException(matrikelnr.toString)
  private class _Eintrag(name: String, matrikelnr: Int)

  /** Traegt name ein und ordnet ihm eine eindeutige, bisher noch
  * nie vergebene Matrikelnummer >= 1 zu.
  * @return Die zugeordnete Matrikelnr. Diese werden ab 1 dicht aufsteigend vergeben.
  * @throws IllegalArgumentException name ist null oder leer
  * @throws Ueberlauf kein Platz (mehr)
  */
  def eintragen(name: String): Int
  
  /** Liefert den der der Matrikelnr. zugeordneten Namen.
  * @throws MatrikelnrUnbekannt matrikelnr ist keinem Namen zugeordnet
  */
  def name(matrikelnr: Int): String
  
  /** Liefert die erste, name zugeordnete Matrikelnr.
  * @throws IllegalArgumentException name ist null oder leer
  * @throws NameUnbekannt Dem name ist keine Matrikelnr. zugeordnet
  */
  def matrikelnr(name: String): Int
  
  /**Liefert die zuletzt vergebene Matrikelnr. */
  def letzteVergebeneMatrikelnr: Int
  
  /** Loescht den Namen, dem matrikelnr zugeordnet war. Diese 
   * Matrikelnr. wird dennoch nie wieder vergeben.
   * Der Platz wird aber wieder freigegeben.
   * @throws MatrikelnrUnbekannt matrikelnr ist keinem Namen zugeordnet
  */
  def austragen(matrikelnr: Int)
  
}