package org.gwtproject.tutorial.server;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class TwitterServiceImpl {
	
	public static void main(String[] args) throws TwitterException {
		TwitterServiceImpl impl = new TwitterServiceImpl();
		ResponseList<Status> resList = impl.getUserTimeline("ianchesnut");
		
		for(Status status : resList) {
			System.out.println(status.getCreatedAt() + ": " + status.getText());
		}
	}
	
	public ResponseList<Status> getUserTimeline(String screenName) throws TwitterException {
		Twitter twitter = new TwitterFactory().getInstance();
		return twitter.getUserTimeline(screenName);
	}
}
