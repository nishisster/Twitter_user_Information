package com.tweet;
	import java.io.BufferedReader;
	import java.io.FileNotFoundException;
	import java.io.FileReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.util.Arrays;
	import com.google.gson.GsonBuilder;
	import com.google.gson.JsonElement;
	import com.google.gson.JsonObject;
	import com.google.gson.JsonParser;
	import com.google.gson.Gson;
	import twitter4j.IDs;
	import twitter4j.PagableResponseList;
	import twitter4j.Status;
	import twitter4j.Twitter;
	import twitter4j.TwitterException;
	import twitter4j.TwitterFactory;
	import twitter4j.User;
	import twitter4j.auth.AccessToken;
	import twitter4j.auth.RequestToken;
	import twitter4j.conf.ConfigurationBuilder;

	public class Json_format {
		
		static String consumerKey = "8P8swXdObRfebfD8UFGhLF33e";
		static String consumerSecret = "EBKES2ObNFoosOlcTVZI9riBF02FpRI0lWVycVcpCqOsbo3bYW";
		static String token;
		static String tokenSecret;
		

	    public static void main(String[] args) throws IOException {
	    	
	        try {
	        	ConfigurationBuilder cb = new ConfigurationBuilder();
	        	 cb.setDebugEnabled(true)
	             .setOAuthConsumerKey("8P8swXdObRfebfD8UFGhLF33e")
	             .setOAuthConsumerSecret("EBKES2ObNFoosOlcTVZI9riBF02FpRI0lWVycVcpCqOsbo3bYW")
	             .setOAuthAccessToken("109454161-szNU5HErMwwNtwGGYznBybjUCwkbkCPe3sxLWL10")
	             .setOAuthAccessTokenSecret("BXNvo9ulBb9yYJFPourZwTvmL32laErIGSx5gbkBqIwO7");
	           TwitterFactory tf = new TwitterFactory(cb.build());
	           Twitter twitter1 = tf.getInstance();
	           //String twitterscreenname = twitter.getScreenName();
	           PagableResponseList<User> twitterfollowers = twitter1.getFollowersList("shajuvk", -1);
	             System.out.println(twitterfollowers);
	             
	             
	      
	            String strValue = String.valueOf( twitterfollowers );           
	           Gson gson = new GsonBuilder().setPrettyPrinting().create();
	           System.out.println(gson);
	           JsonParser jp = new JsonParser();
	           JsonElement je = jp.parse(strValue);
	           String prettyJsonString = gson.toJson(je);
	           System.out.println(prettyJsonString);
	             
	             
	            
		    	// The factory instance is re-useable and thread safe.
		        Twitter twitter = TwitterFactory.getSingleton();
		       twitter.setOAuthConsumer(consumerKey, consumerSecret);
		        RequestToken requestToken = twitter.getOAuthRequestToken();
		        
		        AccessToken accessToken = null;
		        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		        while (null == accessToken) {
		          System.out.println("Open the following URL and grant access to your account:");
		          System.out.println(requestToken.getAuthorizationURL());
		          System.out.print("Enter the PIN(if aviailable) or just hit enter.[PIN]:");
		          String pin = br.readLine();
		         
		         
		          try{
		             if(pin.length() > 0){
		               accessToken = twitter.getOAuthAccessToken(requestToken, pin);
		             }else{
		               accessToken = twitter.getOAuthAccessToken();
		           
		             }
		          } catch (TwitterException te) {
		            if(401 == te.getStatusCode()){
		              System.out.println("Unable to get the access token.");
		            }else{
		              te.printStackTrace();
		            }
		          }
		        }
		        
	        	//persist to the accessToken for future reference.
	            storeAccessToken(twitter.verifyCredentials().getId() , accessToken);
	            Status status = twitter.updateStatus(args[0]);
	            System.out.println("Successfully updated the status to [" + status.getText() + "].");

	            String twitterScreenName = twitter.getScreenName();
	            
	            IDs followerIDs = twitter.getFollowersIDs(twitterScreenName, -1);
	            long[] ids = followerIDs.getIDs();
	            for (long id : ids) {
	               twitter4j.User user = twitter.showUser(id);
	               //here i am trying to fetch the followers of each id
	               String userScreenName = user.getScreenName();
	               System.out.println("Name: " + user.getScreenName());
	               System.out.println("Location:" + user.getLocation());

	               IDs followerIDsOfFollowers = twitter.getFollowersIDs(user.getScreenName(), -1);
	               long[]fofIDs = followerIDsOfFollowers.getIDs();
	               for(long subId : fofIDs) {
	                   twitter4j.User user1 = twitter.showUser(subId);
	                   System.out.println("Follower Master:" + userScreenName +" Follower of Follower Name: " + user1.getScreenName());
	                   System.out.println("Location:" + user1.getLocation());

	               }
	            }
	        } catch (TwitterException te) {
		            te.printStackTrace();
		            System.out.println("Failed to get timeline: " + te.getMessage());
		            System.exit(-1);
		        }
	        
	        System.exit(0);
	      }
	    
	      private static void storeAccessToken(long useId, AccessToken accessToken){
	       token = accessToken.getToken();
	        tokenSecret = accessToken.getTokenSecret();
	      }
	}


