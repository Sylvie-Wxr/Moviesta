import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile){
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(ratingsfile);
    }
    
    public int getMovieSize(){
        return myMovies.size();
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
        for (Movie m : myMovies){
            String movieId = m.getID();
            double avgRating = getAverageByID(movieId, minimalRaters);
            if (avgRating != 0.0){
                Rating r = new Rating(movieId, avgRating);
                ratings.add(r);
            }
        }
        return ratings;
    }
    
    public String getTitle(String id){
        for (Movie m : myMovies){
            if (m.getID().equals(id)){
                return m.getTitle();
            }
        }
        return "the ID was not found";
    }
    
    public String getID(String title){
        for (Movie m : myMovies){
            if (m.getTitle().equals(title)){
                return m.getID();
            }
        }
        return "NO SUCH FILE";
    }
            
        
    
}