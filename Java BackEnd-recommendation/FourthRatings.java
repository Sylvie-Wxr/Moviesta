import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FourthRatings {
    
    private double getAverageByID(String id, int minimalRaters){
        double totalRatings = 0;
        int count = 0;
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
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
    
    private double dotProduct(Rater me, Rater r){
        double product = 0.0;
        ArrayList<String> myRatedMovies = me.getItemsRated();
        ArrayList<String> rRatedMovies = r.getItemsRated();
        for (String movieId : myRatedMovies){
            if (rRatedMovies.contains(movieId)){
                product += (me.getRating(movieId)-5) * (r.getRating(movieId)-5);
            }
        }
        return product;
    }
    
    private ArrayList<Rating> getSimilarities(String id){
        ArrayList<Rating> similarities = new ArrayList<Rating>();
        ArrayList<Rater> raters = RaterDatabase.getRaters();
        Rater me = RaterDatabase.getRater(id);
        for (Rater r : raters){
            if (!r.equals(me)){
                double similarity = dotProduct(me, r);
                if (similarity > 0){
                    similarities.add(new Rating(r.getID(), similarity));
                }
            }
        }
        Collections.sort(similarities, Collections.reverseOrder());
        return similarities;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){
        ArrayList<Rating> weightedMovieRatings = new ArrayList<Rating>();
        ArrayList<String> movieList = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> raterSimilarities = getSimilarities(id);
        int sampleSize = 0;
        if (raterSimilarities.size() > numSimilarRaters){
            sampleSize = numSimilarRaters;
        }
        else{
            sampleSize = raterSimilarities.size();
        }
        for (String movieId : movieList){
            double weightedRating = 0.0;
            int countRating = 0;
            for (int k=0; k<sampleSize; k++){
                Rating r = raterSimilarities.get(k);
                String raterId = r.getItem();
                double similarity = r.getValue();
                if (RaterDatabase.getRater(raterId).hasRating(movieId)){
                    weightedRating += RaterDatabase.getRater(raterId).getRating(movieId) * similarity;
                    countRating += 1;
                }
            }
            if (countRating >= minimalRaters){
                weightedMovieRatings.add(new Rating(movieId, weightedRating/countRating));
            }
        }
        return weightedMovieRatings;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> weightedMovieRatings = new ArrayList<Rating>();
        ArrayList<String> movieList = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> raterSimilarities = getSimilarities(id);
        int sampleSize = 0;
        if (raterSimilarities.size() > numSimilarRaters) {
            sampleSize = numSimilarRaters;
        } else {
            sampleSize = raterSimilarities.size();
        }
        for (String movieId : movieList) {
            double weightedRating = 0.0;
            int countRating = 0;
            for (int k = 0; k < sampleSize; k++) {
                Rating r = raterSimilarities.get(k);
                String raterId = r.getItem();
                double similarity = r.getValue();
                if (RaterDatabase.getRater(raterId).hasRating(movieId)) {
                    weightedRating += RaterDatabase.getRater(raterId).getRating(movieId) * similarity;
                    countRating += 1;
                }
            }
            
            if (countRating >= minimalRaters) {
                weightedMovieRatings.add(new Rating(movieId, weightedRating/countRating));
            }
        }
        return weightedMovieRatings;
    
    }
            
        
    
}