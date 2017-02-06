package edu.knoldus


import twitter4j.Twitter
import twitter4j.TwitterFactory
import twitter4j.conf.ConfigurationBuilder
import twitter4j.Query
import collection.JavaConversions._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class TweetAnalysis {
  val consumerKey = "AopUk7XBn03juGnBFU1T6Mirh"
  val consumerSecret = "qL3K3Mmixer0TjD7qRh3zuzOryivZnbUIA9YqF0UwMvsaj80pG"
  val accessToken = "823572760370757633-Qcsnb4x41Jgej7C3uHvpwx66ONKqOEb"
  val accessSecret = "2lLG0RT3rpnFZa9dh75AG7mvod4NlTfRICSHqYtFCiB7B"
  val cb = new ConfigurationBuilder
  cb.setDebugEnabled(true)
    .setOAuthConsumerKey(consumerKey)
    .setOAuthConsumerSecret(consumerSecret)
    .setOAuthAccessToken(accessToken)
    .setOAuthAccessTokenSecret(accessSecret)
  val tf = new TwitterFactory(cb.build)
  val twitter: Twitter = tf.getInstance()
  val num = 100

  /*
    *This method will search the query
     */
  def querySearch(query1: String): Future[Int] = Future {

    val query = new Query(query1)
    query.setCount(num)
    val list = twitter.search(query)
    val tweets = list.getTweets.toList
    tweets.length
  }

  /*
  *This method will get the retweets on the query
   */
  def getRetweets(query1: String): Future[Int] = Future {
    val query = new Query(query1)
    query.setCount(num)
    val list = twitter.search(query)
    val tweets = list.getTweets.toList
    val retweets = tweets.map(_.getRetweetCount) //retweets
    val totalOfRetweets = retweets.fold(0) {(defaultValue,retweetsElement) => defaultValue + retweetsElement}
    totalOfRetweets/ 100
  }

  /*
    *This method will get the likes on the query
     */
  def getLikes(query1: String): Future[Int] = Future {
    val query = new Query(query1)
    query.setCount(num)
    val list = twitter.search(query)
    val tweets = list.getTweets.toList
    val likes = tweets.map(_.getFavoriteCount)
    val totalOfLikes = likes.fold(0) {(defaultValue,likesElement) => defaultValue + likesElement}
    totalOfLikes / 100
  }
}