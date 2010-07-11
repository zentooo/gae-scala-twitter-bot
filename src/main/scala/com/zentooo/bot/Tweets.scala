package com.zentooo.bot

import scala.util.Random

import com.zentooo.bot.Config


object Tweets {

  val random = new Random()

  val tweetList = Config.tweets.split("\n").toList

  val listSize = tweetList.size

  def getRandomTweet(): String = tweetList(random.nextInt(listSize))
}
