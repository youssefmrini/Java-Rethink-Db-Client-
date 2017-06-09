package hello.maven;
import java.awt.List;
import java.util.ArrayList;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.gen.ast.Db;
import com.rethinkdb.gen.ast.ForEach;
import com.rethinkdb.gen.exc.ReqlError;
import com.rethinkdb.gen.exc.ReqlQueryLogicError;
import com.rethinkdb.model.Arguments;
import com.rethinkdb.model.MapObject;
import com.rethinkdb.net.Connection;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;


public class hello {
	
	
	public static void main(String[] args){
    final RethinkDB r = RethinkDB.r;
	Connection conn = r.connection().hostname("localhost").port(28015).connect();
     r.dbCreate("socialmediass").run(conn);
	r.db("socialmediass").tableCreate("wannacrys").run(conn);
	
	System.out.println("hello and welcome Mr Mrini");
	ConfigurationBuilder cb = new ConfigurationBuilder();
	cb.setOAuthConsumerKey("S1qrt0hxaRljo1Ecv9Gld8hWz");
	cb.setOAuthConsumerSecret("BAe3SHIDhJAzILRGuZ1GOev6ZSo6pB3kyAaZrBwoziA224eSA5");
	cb.setOAuthAccessToken("125108625-plfLq20dc0emL8dwFoz5wcFkF3SJ0lIFr3O5JGWa");
	cb.setOAuthAccessTokenSecret("RxTT9dDcC12l8tBMi07hTnMgv9nEdlSFrGsaXhumHJRWk");

	

	TwitterFactory tf = new TwitterFactory(cb.build());
	Twitter twitter = tf.getInstance();
    Query query = new Query("wannacry");
    QueryResult result = null;
    int i=0;
  for(i=0;i<1000;i++){
	try {
		result = twitter.search(query);
		query.setCount(100);
	} catch (TwitterException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    for (Status status : result.getTweets()) {
        System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
        r.db("socialmediass").table("wannacrys").insert(r.hashMap("user", status.getUser().getScreenName()).with("tweet", status.getText()).with("Lang", status.getUser().getLang()).with("Location", status.getUser().getLocation()).with("id", status.getUser().getId())).run(conn);
    }
    i++;
    }
 
  
  
      
 
	}}

                    





