package com.tweet;


	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.net.MalformedURLException;
	import java.net.URL;
	import java.net.URLConnection;
	import java.util.List;

	import twitter4j.IDs;
	import twitter4j.PagableResponseList;
	import twitter4j.Status;
	import twitter4j.Twitter;
	import twitter4j.TwitterException;
	import twitter4j.TwitterFactory;
	import twitter4j.User;
	import twitter4j.auth.Authorization;
	import twitter4j.conf.Configuration;
	import twitter4j.conf.ConfigurationBuilder;


			public class Particular_user{
			    public static void main(String[] args) throws IOException {
			        // gets Twitter instance with default credentials
			        Twitter twitter = new TwitterFactory().getInstance();
			        String usr;
			        try {
			            ConfigurationBuilder cb = new ConfigurationBuilder();
			            cb.setDebugEnabled(true)
			              .setOAuthConsumerKey("t0dMXIFB3DTNgXguibSWjcOqt")
			              .setOAuthConsumerSecret("nJOzHk6ENVmJPPF9o5elG00Etw0FnSyTx8aZSjYdvdHATkE6NE")
			              .setOAuthAccessToken("724341148743954433-BFb5S6KYmfX0ReLxesJ46P8CD8Fw3TX")
			              .setOAuthAccessTokenSecret("or9p3699R93xWGqWVLejJcvqGY9yANbgIYIRJ67MarsmG");
			            TwitterFactory tf = new TwitterFactory(cb.build());
			            Twitter twitter1 = tf.getInstance();
			            //String twitterscreenname = twitter.getScreenName();
		                PagableResponseList<User> twitterfollowers = twitter.getFollowersList("Nishitha", -1);
		                  System.out.println(twitterfollowers);
			              //Configuration config =  twitter.getConfiguration();
			            IDs followerids = twitter.getFollowersIDs("Nishitha", -1);
			            long[] ids = followerids.getIDs();
			            usr = twitter1.verifyCredentials().getScreenName();
		                String urlAdd = "https://api.twitter.com/1.1/followers/list.json?cursor=-1&screen_name=Nishitha&skip_status=true&include_user_entities=false";
		                URL url = new URL(urlAdd);
		                System.out.println(url);
		                URLConnection urlConnection = url.openConnection();
		                System.out.println(urlConnection);
		            
		                
			            for(long id : ids){
			            	twitter4j.User user = twitter.showUser(id);
			           	System.out.println("Name :" + user.getScreenName());
			            	System.out.println("Location :" + user.getLocation());
			            
			            }
			        }
			            catch (TwitterException te) {
				            te.printStackTrace();
				          //  System.out.println("Failed to get timeline: " + te.getMessage());
				            System.exit(-1);
				        }
                
				    
}
}