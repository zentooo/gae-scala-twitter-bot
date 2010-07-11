package com.zentooo.snippet

import org.specs._
import _root_.scala.xml.NodeSeq

import com.zentooo.snippet.CronSnippet


class CronSnippetSpec extends SpecificationWithJUnit("test for CronSnippet") {

  "sub test" should {
    "return Test" in {
       var cronSnippet = new CronSnippet
       var nodeseq = cronSnippet.randomUpdate
       nodeseq must beLike { case n: NodeSeq => true }
     }
   }

}
