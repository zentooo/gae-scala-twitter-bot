package com.zentooo.snippet

import com.zentooo.bot.TwitterBot

import _root_.scala.xml.NodeSeq
import _root_.net.liftweb.util.Helpers

class CronSnippet {

  def randomUpdate() = TwitterBot.randomUpdate(0.3)

  def checkTimeline() = TwitterBot.checkTimeline(0.4)

  def checkFollow() = TwitterBot.checkFollow

  def checkRemove() = TwitterBot.checkRemove
}
