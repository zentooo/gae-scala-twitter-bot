package com.zentooo.bot

import scala.util.Random

import org.specs._


class TweetsSpec extends SpecificationWithJUnit("Spec for Tweets") {

  "it" should {
    "have the list of tweets" in {
       Tweets.tweetList must notBeEmpty
     }
   }

  "getRandomTweet" should {
    "return one of tweets" in {
       Tweets.getRandomTweet mustMatch(".+")
     }
   }

}
