/**
 * Represent a tweet, including the content, author's username
 * and time when it was posted. 
 */
public class Tweet {
    
    public String user;
    public DateTime datetime; 
    public String content;
    
    public Tweet(String user, DateTime datetime, String content) {
            this.user = user; 
            this.datetime = datetime;
            this.content = content;       
    }
  
    
     @Override
    public boolean equals(Object o){
        
        if(!(o instanceof Tweet)){
            return false;
        }
        Tweet other=(Tweet) o;
        return (this.user.equals(other.user)&&this.datetime.equals(other.datetime)&&this.content.equals(other.content));
       
    }
    
    @Override 
    public int hashCode(){
       return this.user.hashCode()*127+this.datetime.hashCode()*1901+this.content.hashCode()*307;
        
    }
    
    public String toString(){
        return "@"+this.user+" ["+datetime.toString()+"]: "+content;
    }
    
}