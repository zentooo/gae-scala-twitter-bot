package com.zentooo.bot

import scala.util.Random
import scala.xml._

import twitter4j._
import twitter4j.http._

import org.specs._


class TwitterBotSpec extends SpecificationWithJUnit("Spec for TwitterBot") {

  "update with 1 arg" should {
    "return the posted status text" in {
       val random = new Random
       val tweet = random.nextInt(5000).toString
       TwitterBot.update(tweet).getText must_==/ tweet
     }
   }

  "update with 2 args" should {
    "return the posted status text" in {
       val random = new Random
       val tweet = "@zentooo " + random.nextInt(5000).toString + "update with reply id"
       TwitterBot.update(tweet, 18182440915L).getText must_==/ tweet
     }
   }

  "randomUpdate" should {
    "return Text with rate = 1.0" in {
       TwitterBot.randomUpdate(1.0) must beLike { case s: Text => true }
     }
    "return Text with rate = 0.0" in {
       TwitterBot.randomUpdate(0.0) must beLike { case Text("randomUpdate canceled") => true }
     }
    "return Text with rate = 0.3" in {
       TwitterBot.randomUpdate(0.3) must beLike { case s: Text => true }
     }
   }

  "changeFriendShip" should {
    "return Option[User]" in {
      val user = TwitterBot.changeFriendShip(-1, TwitterBot.twitter.createFriendship)
      user must beLike { case u: Option[User] => true }
    }
    "return Option[User]" in {
      val user = TwitterBot.changeFriendShip(-1, TwitterBot.twitter.destroyFriendship)
      user must beLike { case u: Option[User] => true }
    }
  }

  "getFollowings" should {
    "return followings IDs" in {
      TwitterBot.getFollowings must beLike { case s: List[Int] => true }
    }
  }

  "getFollowers" should {
    "return followers IDs" in {
      TwitterBot.getFollowers must beLike { case s: List[Int] => true }
    }
  }


  "doFollow" should {
    "return None with nonexisting id" in {
      TwitterBot.doFollow(-1) must beLike { case None => true }
    }
  }

  "doRemove" should {
    "return None with nonexisting id" in {
      TwitterBot.doRemove(-1) must beLike { case None => true }
    }
  }

  "applyDiff" should {
    "apply given function to diff of two lists" in {
      TwitterBot.applyDiff(List(1,2,3,4), List(1,3), TwitterBot.doFollow) must beLike { case s: List[Option[User]] => true }
    }
  }

}
