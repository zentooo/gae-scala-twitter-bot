package com.zentooo.bot

import scala.util.Random
import scala.util.matching._

import twitter4j._

import org.specs._


class RepliesSpec extends SpecificationWithJUnit("Spec for Replies") {

  "lineToRegexAndString" should {
    "make Regex and String from String" in {
      val pair = Replies.lineToRegexAndString("regex:reply\n")
      pair must beLike { case (a, b) => true }
      pair._1.toString must_== "regex"
      pair._2 must_== "reply"

      val pair2 = Replies.lineToRegexAndString("ほげ:ふが\n")
      pair2 must beLike { case (a, b) => true }
      pair2._1.toString must_== "ほげ"
      pair2._2 must_== "ふが"
     }
   }

   "tupleListToMap" should {
     "transform List[Tuple[A, B]] to Map[A,B]" in {
       val map = Replies.tupleListToMap(List((1,2), (3,4)))
       map must notBeNull
       map(1) must_== 2
       map(3) must_== 4
     }
   }

   "replyMap" should {
     "be map of Regex -> String" in {
       val map = Replies.replyMap
       map must notBeEmpty
       map must beLike { case m: Map[Regex, String] => true }
     }
   }

   "getReply" should {
     "return Option" in {
       val random = new Random
       val tweet = "getReply test " + random.nextInt(5000).toString
       val status = TwitterBot.update(tweet)
       val result = Replies.getReply(status)
       result must notBeNull
       result must beLike { case a: Option[String] => true }
     }
   }

   "selectReply" should {
     "return random reply" in {
       var replies = "reply1;reply2;reply3"
       var result = Replies.selectReply(replies)
       result must beLike { case a: String => true }
       result must beLike {
         case "reply1" => true
         case "reply2" => true
         case "reply3" => true
         case _ => false
       }
     }
   }

}
