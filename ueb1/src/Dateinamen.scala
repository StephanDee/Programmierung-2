

object Dateinamen extends App {
  val dir = new java.io.File(".")
  val files = dir.list.sorted
  for (f <- files) println(f)
}