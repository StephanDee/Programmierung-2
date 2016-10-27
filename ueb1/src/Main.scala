

object Main extends App {
  for (c <- 'A' to 'H') {
    for (i <- 1 to 8) print(c.toString + i + ' ')
    println()
  }
}