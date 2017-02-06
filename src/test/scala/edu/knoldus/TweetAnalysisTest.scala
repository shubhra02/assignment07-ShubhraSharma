package edu.knoldus

import org.scalatest.FunSuite

import scala.concurrent.Await
import scala.concurrent.duration._
class TweetAnalysisTest extends FunSuite{
  val demoObj = new TweetAnalysis
  val tweetslength = Await.result(demoObj.querySearch("#Scala"), 10.second)
 test("Testing tweets retrieval"){

  assert(tweetslength == 100) }

  test("Testing if tweets are retrieved"){

    assert(tweetslength > 0) }
    val retweets =  Await.result(demoObj.getRetweets("#Scala"),10.second)
  test("Testing retweets"){

    assert(retweets >= 0) }
    val tweetLikes = Await.result(demoObj.getLikes("#Scala"),10.second)
  test("Testing likes"){

    assert(tweetLikes >=0) }

}

