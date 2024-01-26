package com.cavisson.ata.utils;

public class TweetNotFoundException extends RuntimeException {

	public TweetNotFoundException(String tweetId) {
		super("Tweet not found with id " + tweetId);
	}
}
