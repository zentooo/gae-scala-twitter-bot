package com.zentooo.view

import _root_.net.liftweb.http._

import com.zentooo.bot.TwitterBot


class CronView extends LiftView {
  override def dispatch = {
    case "randomupdate" => randomUpdate _
    case "checktimeline" => checkTimeline _
    case "checkfollow" => checkFollow _
    case "checkremove" => checkRemove _
  }

  def randomUpdate() = TwitterBot.randomUpdate(0.3)

  def checkTimeline() = TwitterBot.checkTimeline(0.4)

  def checkFollow() = TwitterBot.checkFollow

  def checkRemove() = TwitterBot.checkRemove
}
