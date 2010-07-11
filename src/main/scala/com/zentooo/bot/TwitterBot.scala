package com.zentooo.bot

import scala.io._
import scala.util.Random
import scala.collection.jcl.Conversions.convertList
import scala.xml._

import twitter4j._
import twitter4j.http._

import com.zentooo.bot.Config
import com.zentooo.bot.Tweets


object TwitterBot {

  val twitterFactory = new TwitterFactory
  val twitter = twitterFactory.getOAuthAuthorizedInstance(Config.consumer_key, Config.consumer_secret, new AccessToken(Config.oauth_token, Config.oauth_token_secret))

  val random = new Random

  def update(tweet: String) = twitter.updateStatus(tweet)

  def update(tweet: String, inReplyToStatusId: Long) = twitter.updateStatus(tweet, inReplyToStatusId)

  def reply(user: User, tweet: String) = update("@" + user.getScreenName + " " + tweet.replace("%username%", user.getName))

  def reply(user: User, tweet: String, inReplyToStatusId: Long) = update("@" + user.getScreenName + " " + tweet.replace("%username%", user.getName), inReplyToStatusId)

  def randomUpdate(updateRate: Double): NodeSeq = {
    if (random.nextDouble > updateRate) Text("randomUpdate canceled")
    else Text(update(Tweets.getRandomTweet).getText)
  }

  def checkTimeline(replyRate: Double): NodeSeq = {
    val list = List(twitter.getId)
    for {
      status <- twitter.getFriendsTimeline
      user = status.getUser
      userId = user.getId
      replyOption = Replies.getReply(status)
      inReplyToStatusId = status.getInReplyToStatusId
      if ! list.contains(userId)
      if random.nextDouble < replyRate
    } {
      //replyOption.foreach(replyString => reply(user, replyString, inReplyToStatusId))
      replyOption.foreach(replyString => reply(user, replyString))
      user :: list
    }
    Text("checkTimeline done")
  }

  def makeGreeting(user: User) = reply(user, Config.greeting)

  def checkFollow(): NodeSeq = {
    applyDiff(getFollowers, getFollowings, doFollow).foreach(user => user.foreach(makeGreeting))
    Text("checkFollow done")
  }

  def checkRemove(): NodeSeq = {
    applyDiff(getFollowings, getFollowers, doRemove)
    Text("checkRemove done")
  }

  def applyDiff(idList1: List[Int], idList2: List[Int], func: Int => Option[User]): List[Option[User]] = (idList1 -- idList2).map(func)

  def changeFriendShip(id: Int, changeFunc: Int => User): Option[User] =
    try {
      Some(changeFunc(id))
    }
    catch {
      case e: TwitterException => None
    }

  def getFollowings: List[Int] = twitter.getFriendsIDs.getIDs.toList

  def getFollowers: List[Int] = twitter.getFollowersIDs.getIDs.toList

  def doFollow(id: Int): Option[User] = changeFriendShip(id, twitter.createFriendship)

  def doRemove(id: Int): Option[User] = changeFriendShip(id, twitter.destroyFriendship)

}
