import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

/**
 * Write a description of class FirstRatings here.
 *
 * @author xinrui wu
 * @version (a version number or a date)
 */
public class FirstRatings
{
    
    public ArrayList<Movie> loadMovies(String filename){
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        ArrayList<Movie> mList = new ArrayList<>();
        for (CSVRecord rec : parser){
            String id = rec.get("id");
            String title = rec.get("title");
            String year = rec.get("year");
            String country = rec.get("country");
            String genre = rec.get("genre");
            String director = rec.get("director");
            int minutes = Integer.parseInt(rec.get("minutes"));
            String poster = rec.get("poster");
            Movie m = new Movie(id, title, year, genre, director, country, poster, minutes);
            mList.add(m); 
        }
        return mList;
    }
    
    public ArrayList<Rater> loadRaters(String filename){
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        ArrayList<Rater> raterList = new ArrayList<>();
        ArrayList<String> idList = new ArrayList<>();
        for (CSVRecord rec : parser){
            String raterId = rec.get(0);
            String movieId = rec.get(1);
            Double rating = Double.parseDouble(rec.get(2));
            String time = rec.get(3);
            if (!idList.contains(raterId)){
                Rater raters = new EfficientRater(raterId);
                raterList.add(raters);
                raters.addRating(movieId, rating);
                idList.add(raterId);
            }
            else{
                for (Rater rater : raterList){
                    if (rater.getID().equals(raterId)){
                        rater.addRating(movieId, rating);
                    }
                }
            }
        }
        return raterList;
    }
    
    public void testLoadMovies(){
        ArrayList<Movie> mList = loadMovies("data/ratedmoviesfull.csv");
        //ArrayList<Movie> mList = loadMovies("data/ratedmoviesfull.csv");
        ArrayList<Movie> comedy = new ArrayList<Movie>();
        ArrayList<Movie> Minutes150 = new ArrayList<Movie>();
        HashMap<String,Integer> map = new HashMap<String, Integer>(); 
        int largestValue = 0;
        ArrayList<String> largestDirectors = new ArrayList<String>();
        System.out.println("the number of movies is "+ mList.size());
        System.out.println("Movies are :");
        for (Movie film : mList){
            //System.out.println(film.toString());
            //System.out.println(film.getTitle());
            String genre = film.getGenres();
            int minutes = film.getMinutes();
            if (genre.indexOf("Comedy") != -1){
                comedy.add(film);
            }
            if (minutes > 150){
                Minutes150.add(film);
            }
            String director = film.getDirector();
            if (!map.containsKey(director)){
                map.put(director, 1);
            }
            else{
                map.put(director, map.get(director)+1);
            }   
        }
        for (String directorName: map.keySet()){
            int value = map.get(directorName);
            if (value > largestValue){
                largestValue = value;
                largestDirectors.clear();
                largestDirectors.add(directorName);
            }
            else if (value == largestValue){
                largestDirectors.add(directorName);
            }
        }
        System.out.println("the num of comedy is "+comedy.size());
        System.out.println("the num of greater than 150 minutes is "+Minutes150.size());
        System.out.println("the maximum num of movie directed by any director is " + largestValue);
        System.out.println("the director(s) = ");
        for (String director : largestDirectors){
            System.out.println(director);
        }
    }

    public void testLoadRaters(){
        ArrayList<Rater> raterList = loadRaters("data/ratings.csv");
        System.out.println("the num of raters are "+ raterList.size());
        
        //for specific rate ID
        String raterId = "193";
        //for maximum num of ratings
        int largestNumOfRatings = 0;
        ArrayList<String> largestRateIds = new ArrayList<>();
        //for num of movies a specific movie has
        int countRatersOfMovie = 0;
        String movieId = "1798709";
        //for total movies rated
        HashSet<String> set = new HashSet<String>();
        
        for (Rater r : raterList){
            //print rateID and the num of rates
            //System.out.println("raterID = "+ r.getID() +" gives "+r.numRatings() + " rates");
            ArrayList<String> items = r.getItemsRated();
            for (String item : items){
                //print movie ID and their ratings
                //System.out.println("movie ID ="+ item + "\t ratings = "+r.getRating(item));
                //count the number of ratins a particular movie has
                if (item.equals(movieId)){
                    countRatersOfMovie += 1;
                }
                //count total movies rated
                if (!set.contains(item)){
                    set.add(item);
                }
            }
            //number of ratings for a particular rater
            if (r.getID().equals(raterId)){
                System.out.println("rater "+raterId+" rated "+r.numRatings()+" movies ");
            }
            //maximum number of ratings by any rater
            if (r.numRatings() > largestNumOfRatings){
                largestRateIds.clear();
                largestNumOfRatings = r.numRatings();
                largestRateIds.add(r.getID());
            }
            else if (r.numRatings() == largestNumOfRatings){
                largestRateIds.add(r.getID());
            }
        }
        System.out.println("the maximum num of ratings is "+largestNumOfRatings+" and made by rater " + largestRateIds.toString());
        // print the number of ratings a particular movie has
        System.out.println("Movie ID "+movieId+" has "+ countRatersOfMovie+" ratings");
        // print total movied rated
        System.out.println("totol num of movies rated are "+ set.size());
    }
}

