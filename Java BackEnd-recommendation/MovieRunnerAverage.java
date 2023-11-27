import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class MovieRunnerAverage
{
    private String moviefile = "data/ratedmoviesfull.csv";
    private String ratingsfile = "data/ratings.csv";
    
    public void printAverageRatings(){
        SecondRatings sr = new SecondRatings(moviefile, ratingsfile);
        System.out.println("the num of movies is "+sr.getMovieSize());
        System.out.println("the num of ratings is "+sr.getRaterSize());
        int minimalRaters = 12;
        ArrayList<Rating> avgRatingList = sr.getAverageRatings(minimalRaters);
        System.out.println("there are "+avgRatingList.size()+" ratings");
        Collections.sort(avgRatingList);
        for (Rating r : avgRatingList){
            System.out.println(r.getValue()+"\t"+sr.getTitle(r.getItem()));
        }
    }
    
    public void getAverageRatingOneMovie(){
        SecondRatings sr = new SecondRatings(moviefile, ratingsfile);
        String title = "Vacation";
        String id = sr.getID(title);
        int minimalRaters = 1;
        /* if getAverageByID is public
        double avgRating = sr.getAverageByID(id, minimalRaters);
        System.out.println(avgRating);
        */
        // when private
        ArrayList<Rating> avgRatingList = sr.getAverageRatings(minimalRaters);
        for (Rating r : avgRatingList){
            if (r.getItem().equals(id)){
                System.out.println("the avg rating of "+title+" is "+r.getValue());
            }
        }
        
    }
}
