import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("data/ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile){
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRaters(ratingsfile);
    }
    
    
    public int getRaterSize(){
        return myRaters.size();
    }
    
    private double getAverageByID(String id, int minimalRaters){
        double totalRatings = 0;
        int count = 0;
        for (Rater r : myRaters){
            if (r.getRating(id) != -1){
                totalRatings += r.getRating(id);
                count += 1;
            }
        }
        if (count >= minimalRaters){
            return totalRatings/count;
        }
        return 0.0;       
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        ArrayList<String> myMovies = MovieDatabase.filterBy(new TrueFilter());
        for (String movieId : myMovies){
            double avgRating = getAverageByID(movieId, minimalRaters);
            if (avgRating != 0.0){
                Rating r = new Rating(movieId, avgRating);
                ratings.add(r);
            }
        }
        return ratings;
    }
    
    public ArrayList<Rating> getAverageRatingByFilter(int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> ratingList = new ArrayList<Rating>();
        ArrayList<String> myMovies = MovieDatabase.filterBy(filterCriteria);
        for (String movieId : myMovies){
            double avgRating = getAverageByID(movieId, minimalRaters);
            if (avgRating != 0.0){
                ratingList.add(new Rating(movieId, avgRating));
            }
        }
        return ratingList;
    }
    
            
        
    
}