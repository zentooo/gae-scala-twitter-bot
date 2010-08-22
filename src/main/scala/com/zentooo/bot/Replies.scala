package com.zentooo.bot

import scala.util.Random
import scala.util.matching._

import twitter4j._

import com.zentooo.bot.Config

object Replies {

  val random = new Random()

  val replyMap: Map[Regex, String] = tupleListToMap(Config.replies.split("\n").map(lineToRegexAndString).toList)

  def getReply(status: Status): Option[String] = {
    val regexAndString = replyMap.find(regexAndReply => regexAndReply._1.findFirstIn(status.getText) != None)
    regexAndString match {
      case o: Some[(Regex, String)] => Some(selectReply(o.get._2))
      case None => None
    }
  }

  def selectReply(repliesString: String): String = {
    val replies = repliesString.split(";").toList
    return replies(random.nextInt(replies.size))
  }

  def tupleListToMap[T1, T2](list: List[(T1, T2)]): Map[T1, T2] = {
    list match {
      case x :: xs => Map(x._1 -> x._2) ++ tupleListToMap(xs)
      case Nil => Map()
    }
  }

  def lineToRegexAndString(line: String) = {
    val lineParts = line.stripLineEnd.split(":")
    (new Regex(lineParts(0)), lineParts(1))
  }
}
