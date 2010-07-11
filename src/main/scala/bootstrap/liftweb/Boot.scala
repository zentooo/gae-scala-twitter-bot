package bootstrap.liftweb

import _root_.net.liftweb.common._
import _root_.net.liftweb.util._
import _root_.net.liftweb.http._
import _root_.net.liftweb.sitemap._
import _root_.net.liftweb.sitemap.Loc._
import Helpers._


/**
  * A class that's instantiated early and run.  It allows the application
  * to modify lift's environment
  */
class Boot {
  def boot {
    // where to search snippet
    LiftRules.addToPackages("com.zentooo")
    
    // Build SiteMap
    val entries = Menu(Loc("Home", List("index"), "Home")) ::
      Menu(Loc("randomUpdate", List("cron", "randomupdate"), "randomUpdate")) ::
      Menu(Loc("checkTimeline", List("cron", "checktimeline"), "checkTimeline")) ::
      Menu(Loc("checkFollow", List("cron", "checkfollow"), "checkFollow")) ::
      Menu(Loc("checkRemove", List("cron", "checkremove"), "checkRemove")) ::
      Nil
    LiftRules.setSiteMap(SiteMap(entries:_*))
  }
}

