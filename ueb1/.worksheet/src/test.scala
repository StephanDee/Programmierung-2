object Test extends App {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(98); 
  val n = 5;System.out.println("""n  : Int = """ + $show(n ));$skip(74);  //Deklaration eines Wertes ( wie in Java: final int n = 5; )
  var sum = 0;System.out.println("""sum  : Int = """ + $show(sum ));$skip(56);  //Deklaration einer Variablen ( wie in Java: int sum = 0; )
  for (i <- 1 to n) sum += i;$skip(35); val res$0 =  //Zählschleife von 1 bis n
  sum;System.out.println("""res0: Int = """ + $show(res$0));$skip(85);  //Abfrage des Wertes von sum
  val studis = List("Hans", "Margarete", "Ivo", "Ina", "Klaus");System.out.println("""studis  : List[String] = """ + $show(studis ));$skip(87); val res$1 =  //Listenkonstruktion
  studis.filter(_.length > 3).sorted;System.out.println("""res1: List[String] = """ + $show(res$1));$skip(74); val res$2 =  //Lange Elemente herausfiltern und dann sortieren
  studis.partition(_.length <= 3) //Aufteilen in kurze und lange Elemente;System.out.println("""res2: (List[String], List[String]) = """ + $show(res$2))}
}
