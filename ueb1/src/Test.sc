object Test extends App {
  val n = 5 //Deklaration eines Wertes ( wie in Java: final int n = 5; )
  var sum = 0 //Deklaration einer Variablen ( wie in Java: int sum = 0; )
  for (i <- 1 to n) sum += i //Zählschleife von 1 bis n
  sum //Abfrage des Wertes von sum
  val studis = List("Hans", "Margarete", "Ivo", "Ina", "Klaus") //Listenkonstruktion
  studis.filter(_.length > 3).sorted //Lange Elemente herausfiltern und dann sortieren
  studis.partition(_.length <= 3) //Aufteilen in kurze und lange Elemente
}