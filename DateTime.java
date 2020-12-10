/**
 * Represent a timestamp consisting of a date (day/month/year) and time 
 * in hours and minutes (24h format.
 */
public class DateTime implements Comparable<DateTime>{  //For part 4
    
    public int year;
    public int month;
    public int day; 
    public int hours;
    public int minutes;    
    public int seconds;
    public boolean pm; 

    
    public DateTime(int year, int day, int month, int h, int m) {        
        this.year = year; 
        this.month = month; 
        this.day = day;     
        this.hours = h; 
        this.minutes = m;                
    }
    
    public DateTime(String datetime) {
        String[] fields = datetime.split(" ");
        String[] dateFields = fields[0].split("/");
        month = Integer.parseInt(dateFields[0]);
        day = Integer.parseInt(dateFields[1]);
        year = Integer.parseInt(dateFields[2]);
        
        String[] timeFields = fields[1].split(":"); 
        hours = Integer.parseInt(timeFields[0]);
        minutes = Integer.parseInt(timeFields[1]);;
    }
                       
    
    public int compareTo(DateTime other){
        if(this.year<other.year){
            return -1;
        }
        if(this.year>other.year){
            return 1;
        }
        if(this.month<other.month){
            return -1;
        }
        if(this.month>other.month){
            return 1;
        }
        if(this.day<other.day){
            return -1;
        }
        if(this.day>other.day){
            return 1;  
        }
        if(this.hours<other.hours){
            return -1; 
        }
        if(this.hours>other.hours){
            return 1;
        }
        if(this.minutes<other.minutes){
             return -1;
        }
        if (this.minutes>other.minutes){
             return 1;
        } 
        return 0;
    }
    
    
    @Override
    public boolean equals(Object o){
        
        if(!(o instanceof DateTime)){
            return false;
        }
        DateTime other=(DateTime) o;
        return (this.year==other.year && this.year==other.year &&
               this.month==other.month && this.day==other.day
               && this.hours==other.hours && this.minutes==other.minutes);
       
    }
    
    @Override 
    public int hashCode(){
        int hashYear=this.year*127;
        int hashMonth=this.month*1901;
        int hashDay=this.day*839;
        int hashHour=this.hours*769;
        int hashMin=this.minutes*373;
        
        return(hashYear+hashMonth+hashDay+hashHour+hashMin);
    }
    
    public String toString() {
        return Integer.toString(month)+"/"+Integer.toString(day)+"/"+Integer.toString(year)+"  "+
            String.format("%02d" , hours)+":"+String.format("%02d", minutes);
    }   
    
}