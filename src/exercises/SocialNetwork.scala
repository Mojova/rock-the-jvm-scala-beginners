package exercises

import scala.annotation.tailrec

object SocialNetwork extends App {
  def add(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = network + (person -> Set())

  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] = {
    @tailrec
    def removeAux(friends: Set[String], networkAccumulator: Map[String, Set[String]]): Map[String, Set[String]] = {
      if (friends.isEmpty) networkAccumulator
      else removeAux(friends.tail, unfriend(networkAccumulator, person, friends.head))
    }

    val unfriended = removeAux(network(person), network)
    unfriended - person
  }

  def friend(network: Map[String, Set[String]], personA: String, personB: String): Map[String, Set[String]] = {
    val friendsA = network(personA)
    val friendsB = network(personB)
    network + (personA -> (friendsA + personB)) + (personB -> (friendsB + personA))
  }

  def unfriend(network: Map[String, Set[String]], personA: String, personB: String): Map[String, Set[String]] = {
    val friendsA = network(personA)
    val friendsB = network(personB)
    network + (personA -> (friendsA - personB)) + (personB -> (friendsB - personA))
  }

  val empty: Map[String, Set[String]] = Map()
  val network = add(add(empty, "Bob"), "Mary")
  println(network)
  val friendNetwork = friend(network, "Bob", "Mary")
  println(friendNetwork)
  println(unfriend(friendNetwork, "Mary", "Bob"))
  println(remove(friendNetwork, "Bob"))

  //  Jim, Bob, Mary
  val people = add(add(add(empty, "Bob"), "Mary"), "Jim")
  val jimBob = friend(people, "Bob", "Jim")
  val testNet = friend(jimBob, "Bob", "Mary")
  println(testNet)

  def nFriends(network: Map[String, Set[String]], person: String): Int = network(person).size
  println(nFriends(testNet, "Bob"))

  def mostFriends(network: Map[String, Set[String]]): String = {
    network.maxBy(pair => pair._2.size)._1
  }
  println(mostFriends(testNet))

  def nPeopleWithNoFriends(network: Map[String, Set[String]]): Int = {
    network.count(_._2.isEmpty)
  }

  println(nPeopleWithNoFriends(testNet))
  println(nPeopleWithNoFriends(people))

  def socialConnection(network: Map[String, Set[String]], personA: String, personB: String): Boolean = {
    @tailrec
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if (person == target) true
        else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
      }
    }
    bfs(personB, Set(), network(personA) + personA)
  }

  println(socialConnection(testNet, "Mary", "Jim"))
  println(socialConnection(network, "Mary", "Bob"))
}
