package com.zentooo.bot


object Config {

  val consumer_key = "your consumer key"
  val consumer_secret = "your consumer secret"

  val oauth_token = "your oauth token (access token)"
  val oauth_token_secret = "your oauth token secret (access token secret)"

  // greeting for this bot, update when followed by others
  val greeting = "Thank you for following!"

  // tweets for random update
  val tweets = """tweet1
tweet3
tweet2"""

  // describe replies with format "regex:string".
  // In reply string, %username% will be replaced with user's name.
  val replies = """regex1:sample reply
regex2:Hello, %username%!
regex3:sample reply"""
}
