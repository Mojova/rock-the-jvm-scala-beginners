package lectures.part3fp

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure extends App {
  // create success and failure
  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("Super failure"))
  println(aSuccess)
  println(aFailure)

  def unsafeMethod(): String = throw new RuntimeException("No string for you buster!")

  // Try objects via the apply method
  val potentialFailure = Try(unsafeMethod())
  println(potentialFailure)

  // syntax sugar
  val anotherPotentialFailure = Try {
    // code that might throw
  }

  // utilities
  println(potentialFailure.isSuccess)

  // orElse
  def backupMethod(): String = "A valid result"

  val fallbackTry = Try(unsafeMethod()) orElse Try(backupMethod())
  println(fallbackTry)

  // if you design the API
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException)

  def betterBackupMethod(): Try[String] = Success("A valid result")

  val betterFallback = betterUnsafeMethod() orElse betterBackupMethod()
  println(betterFallback)

  // map, flatMap, filter
  println(aSuccess.map(_ * 2))
  println(aSuccess.flatMap(x => Success(x * 10)))
  println(aSuccess.filter(_ > 10))

  val hostname = "localhost"
  val port = "8080"

  def renderHTML(page: String) = println(page)

  class Connection {
    val random = new Random(System.nanoTime())

    def get(url: String): String = {
      if (random.nextBoolean()) "<html>…</html>"
      else throw new RuntimeException("Connection interrupted")
    }
  }

  object HttpService {
    val random = new Random(System.nanoTime())
    def getConnection(host: String, port: String): Connection = {
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("Someone else took the port")
    }
  }
  Try(HttpService.getConnection(hostname, port)).flatMap(c => Try(c.get("/foobar"))).foreach(renderHTML)

  // for comprehension
  for {
    connection <- Try(HttpService.getConnection(hostname, port))
    html <- Try(connection.get("/foobar"))
  } renderHTML(html)
}
