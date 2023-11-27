import edu.duke.*;
import java.util.*;

public class MovieRunnerSimilarRatings
{
    private String ratingsfile = "data/ratings.csv";
    private String moviefile = "ratedmoviesfull.csv";
    
    public void printAverageRatings(){
        FourthRatings four = new FourthRatings();
        MovieDatabase.initialize(moviefile);
        RaterDatabase.initialize(ratingsfile);
        System.out.println("read data for "+RaterDatabase.size()+" raters");
        System.out.println("read data for " + MovieDatabase.size()+" movies");
        int minimalRaters = 35;
        ArrayList<Rating> avgRatingList = four.getAverageRatings(minimalRaters);
        System.out.println("found "+avgRatingList.size()+" movies");
        Collections.sort(avgRatingList);
        for (Rating r : avgRatingList){
            System.out.println(r.getValue()+"\t"+MovieDatabase.getTitle(r.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        FourthRatings four = new FourthRatings();
        MovieDatabase.initialize(moviefile);
        RaterDatabase.initialize(ratingsfile);
        AllFilters af = new AllFilters();
        int minimalRaters = 8;
        af.addFilter(new YearAfterFilter(1990));
        af.addFilter(new GenreFilter("Drama"));
        ArrayList<Rating> avgRatingList = four.getAverageRatingByFilter(minimalRaters, af);
        System.out.println("read data for "+RaterDatabase.size()+" raters");
        System.out.println("read data for " + MovieDatabase.size()+" movies");
        System.out.println(avgRatingList.size()+" movies matched");
        
        Collections.sort(avgRatingList);
        for (Rating r : avgRatingList){
            System.out.println(r.getValue()+"\t"+MovieDatabase.getYear(r.getItem())+"\t"
             +MovieDatabase.getTitle(r.getItem()));
            System.out.println("\t"+ MovieDatabase.getGenres(r.getItem()));
        }
    }
    
    public void printSimilarRatings(){
        FourthRatings four = new FourthRatings();
        MovieDatabase.initialize(moviefile);
        RaterDatabase.initialize(ratingsfile);
        String id = "71";
        int numSimilarRaters = 20;
        int minimalRaters = 5;
        ArrayList<Rating> weightedRatings = four.getSimilarRatings(id, numSimilarRaters, minimalRaters);
        Collections.sort(weightedRatings, Collections.reverseOrder());
        for(Rating r : weightedRatings){
            System.out.println(r.getValue()+ "\t" + MovieDatabase.getTitle(r.getItem()));
        }
    }
    
    public void printSimilarRatingsByGenre(){
        FourthRatings four = new FourthRatings();
        MovieDatabase.initialize(moviefile);
        RaterDatabase.initialize(ratingsfile);
        String genre = "Mystery";
        String id = "964";
        int numSimilarRaters = 20;
        int minimalRaters = 5;
        Filter gf = new GenreFilter(genre);
        ArrayList<Rating> weightedRatings = four.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, gf);
        Collections.sort(weightedRatings, Collections.reverseOrder());
        for(Rating r : weightedRatings){
            System.out.println(r.getValue()+ "\t" + MovieDatabase.getTitle(r.getItem()));
            System.out.println("\t" + MovieDatabase.getGenres(r.getItem()));
        }
    }
    
    public void printSimilarRatingsByDirector(){
        FourthRatings four = new FourthRatings();
        MovieDatabase.initialize(moviefile);
        RaterDatabase.initialize(ratingsfile);
        String director = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh";
        String id = "120";
        int numSimilarRaters = 10;
        int minimalRaters = 2;
        Filter df = new DirectorFilter(director);
        ArrayList<Rating> weightedRatings = four.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, df);
        Collections.sort(weightedRatings, Collections.reverseOrder());
        for(Rating r : weightedRatings){
            System.out.println(r.getValue()+ "\t" + MovieDatabase.getTitle(r.getItem()));
            System.out.println("\t" + MovieDatabase.getDirector(r.getItem()));
        }
    }
    
    public void printSimilarRatingsByGenreAndMinutes(){
        FourthRatings four = new FourthRatings();
        MovieDatabase.initialize(moviefile);
        RaterDatabase.initialize(ratingsfile);
        String genre = "Drama";
        int minMinutes = 80;
        int maxMinutes = 160;
        String id = "168";
        int numSimilarRaters = 10;
        int minimalRaters = 3;
        AllFilters af = new AllFilters();
        af.addFilter(new GenreFilter(genre));
        af.addFilter(new MinutesFilter(minMinutes, maxMinutes));
        ArrayList<Rating> weightedRatings = four.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, af);
        Collections.sort(weightedRatings, Collections.reverseOrder());
        for(Rating r : weightedRatings){
            System.out.println(r.getValue() + "\tTime " + MovieDatabase.getMinutes(r.getItem()) + " mins\t" 
             + MovieDatabase.getTitle(r.getItem()));
            System.out.println("\t" + MovieDatabase.getGenres(r.getItem()));
        }
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes(){
        FourthRatings four = new FourthRatings();
        MovieDatabase.initialize(moviefile);
        RaterDatabase.initialize(ratingsfile);
        int year = 1975;
        int minMinutes = 70;
        int maxMinutes = 200;
        String id = "314";
        int numSimilarRaters = 10;
        int minimalRaters = 5;
        AllFilters af = new AllFilters();
        af.addFilter(new YearAfterFilter(year));
        af.addFilter(new MinutesFilter(minMinutes, maxMinutes));
        ArrayList<Rating> weightedRatings = four.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, af);
        Collections.sort(weightedRatings, Collections.reverseOrder());
        for(Rating r : weightedRatings){
            System.out.println(r.getValue() + "\tYear " + MovieDatabase.getYear(r.getItem()) + "\tTime " +
             + MovieDatabase.getMinutes(r.getItem())+" mins\t"+ MovieDatabase.getTitle(r.getItem()));
        }
    }
    
    
}