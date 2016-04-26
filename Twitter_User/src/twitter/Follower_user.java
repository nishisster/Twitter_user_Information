package twitter;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import twitter4j.PagableResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

public class Follower_user {

	public static void main(String[] args) throws TwitterException, IOException {
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
          .setOAuthConsumerKey("kQuekE6EIavJNhx4JeU6logvO")
          .setOAuthConsumerSecret("JtwwRB6EjPMfXHtjgJPmcVFRcOyScYmTn2qUSnWEq2obO0wEq6")
          .setOAuthAccessToken("109454161-szNU5HErMwwNtwGGYznBybjUCwkbkCPe3sxLWL10")
          .setOAuthAccessTokenSecret("BXNvo9ulBb9yYJFPourZwTvmL32laErIGSx5gbkBqIwO7");
        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter4j.Twitter twitter1 = tf.getInstance();
        List<Status> st = twitter1.getHomeTimeline();
       PagableResponseList<User> user= twitter1.getFollowersList("shajuvk",109454161 );
       System.out.println(user.getAccessLevel());
        for(Status st1 : st)
        {
        	System.out.println(st1.getUser().getName()+ " ***" +st1.getText() + "  " + st1.getSource());
        	System.out.println(st1.getUser().getFollowersCount());
        	System.out.println(st1.getUser().getScreenName() +" " + st1.getUser().getAccessLevel());
        	System.out.println(st1.getUser().getURL());
        	
        }
	}

}
