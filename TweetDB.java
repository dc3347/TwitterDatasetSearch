import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap; 
import java.util.TreeMap; 
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.HashSet;
import java.util.SortedMap;


public class TweetDB {

    HashMap<String, List<Tweet>> tweetsPerUser;
    HashMap<String, List<Tweet>> tweetsPerKeyword;  
    TreeMap<DateTime, List<Tweet>> tweetsByTime;
    
    String[] fields;
   
    public TweetDB(String pathToFile) throws FileNotFoundException, IOException {
        tweetsPerUser = new HashMap<>();               
        tweetsPerKeyword = new HashMap<>();   
        tweetsByTime = new TreeMap<>();  
        CsvReader reader=new CsvReader(new BufferedReader(new FileReader(pathToFile)));
        
        while((fields=reader.nextLine()) != null) { //each line is a different index in field
            DateTime datetime=new DateTime(fields[2]);
            String user=fields[0];
            String content=fields[1];
            Tweet toAdd=new Tweet(user, datetime, content);
           
            //part 1
            List usersTweets=tweetsPerUser.get(user);
            
            if (usersTweets!=null){
                usersTweets.add(toAdd);
                tweetsPerUser.put(user,usersTweets);
            }
            else{
                usersTweets=new ArrayList<Tweet>();
                usersTweets.add(toAdd);
                tweetsPerUser.put(user, usersTweets);
            } 
  
            
            //part 2
            String[] wordsToAdd = stripTweet(content);
            for (int i=0; i<wordsToAdd.length; i++){
                String elem = wordsToAdd[i];
                //for the case when the keyword is not in the hashmap
                List keywordTweets=tweetsPerKeyword.get(elem);
               
                if (keywordTweets!=null){
                    keywordTweets.add(toAdd);
                    tweetsPerKeyword.put(elem, keywordTweets);
                }
                else{
                    keywordTweets=new ArrayList();
                    keywordTweets.add(toAdd);
                    tweetsPerKeyword.put(elem, keywordTweets);
                }
                
            }
            
            
            //part 3
            List datetimeTweets=tweetsByTime.get(datetime);
            
            if (datetimeTweets!=null){
                datetimeTweets.add(toAdd); 
            }
            else{
                datetimeTweets=new ArrayList<Tweet>();
                datetimeTweets.add(toAdd);
                tweetsByTime.put(datetime, datetimeTweets);
            } 
        }
    } 
    
    public List<Tweet> getTweetsByUser(String user) {   
        List <Tweet> empty=new ArrayList<>();
        HashSet hset=new HashSet(tweetsPerUser.get(user));
        List<Tweet> list=new ArrayList<>(hset);
        
        if (tweetsPerUser.get(user)== null){
            System.out.println("No such user, empty list returned.");
            return empty;
        }
        else
            return list; // replace this  
    }
    
   
    private String[] stripTweet(String content){
        //remove punctuation
        content =content.replace('!',' ');
        content =content.replace(',',' ');
        content =content.replace('.',' ');
        content =content.replace(';',' ');
        content =content.replace('?',' ');
        content =content.replace(':',' ');
        content =content.replace('"',' ');
        content =content.replace('#',' ');
        content =content.replace('@',' ');
        content =content.replace('(',' ');
        content =content.replace(')',' ');
        content =content.toLowerCase();
        String[] words = content.split(" ");
        
        return words;
    }
    
    public List<Tweet> getTweetsByKeyword(String kw) {    
        List <Tweet> empty=new ArrayList<>();
        HashSet hset=new HashSet(tweetsPerKeyword.get(kw));
        List<Tweet> list=new ArrayList<>(hset);
        
        if (tweetsPerKeyword.get(kw)== null){
            System.out.println("No such keyword, empty list returned.");
            return empty;
        }
        else
            return list;
    }
    
   
     public List<Tweet> getTweetsInRange(DateTime start, DateTime end) {
        List<Tweet> treeTweets=new ArrayList<Tweet>();
        SortedMap<DateTime, List<Tweet>> subTweet=new TreeMap<DateTime, List<Tweet>>();
        subTweet=tweetsByTime.subMap(start,end);
        
       
        for(List<Tweet> list_of_tweets: subTweet.values()){
            for(int i=0; i<list_of_tweets.size(); i++){
                treeTweets.add(list_of_tweets.get(i));
               
            }
        }
         
        HashSet hset=new HashSet(treeTweets);
        List<Tweet> list=new ArrayList<>(hset);
        
        return list;
    }  
    
    public static void main(String[] args) {
          
        try {
            TweetDB tdb = new TweetDB("coachella_tweets.csv");

           // Part 1: Search by username 
          /* for(Tweet t : tdb.getTweetsByUser("hannah_frog"))
              System.out.println(t);*/
              
            
         //Part 2: Search by keyword
           /*for(Tweet t : tdb.getTweetsByKeyword("dick"))
            System.out.println(t);*/
            
           
            //Part 3: Search by date/time interval          
            for(Tweet t : tdb.getTweetsInRange(new DateTime("1/6/15 05:00"), new DateTime("1/6/15 15:00")))
                System.out.println(t);
                
            
            
        } catch (FileNotFoundException e) {
            System.out.println(".csv File not found.");
        } catch (IOException e) {
            System.out.println("Error reading from .csv file.");
        }
        
         
    }
    
}