import edu.duke.*;
import java.util.*;

public class MovieRunnerWithFilters
{
    private String ratingsfile = "ratings.csv";
    private String moviefile = "ratedmoviesfull.csv";
    
    public void printAverageRatings(){
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        MovieDatabase.initialize(moviefile);
        System.out.println("read data for "+tr.getRaterSize()+" raters");
        System.out.println("read data for " + MovieDatabase.size()+" movies");
        int minimalRaters = 35;
        ArrayList<Rating> avgRatingList = tr.getAverageRatings(minimalRaters);
        System.out.println("found "+avgRatingList.size()+" movies");
        Collections.sort(avgRatingList);
        for (Rating r : avgRatingList){
            System.out.println(r.getValue()+"\t"+MovieDatabase.getTitle(r.getItem()));
        }
    }
    
    public void printAverageRatingsByYear(){
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        MovieDatabase.initialize(moviefile);
        Filter yf = new YearAfterFilter(2000);
        System.out.println("read data for "+tr.getRaterSize()+" raters");
        System.out.println("read data for " + MovieDatabase.size()+" movies");
        int minimalRaters = 20;
        ArrayList<Rating> avgRatingList = tr.getAverageRatingByFilter(minimalRaters, yf);
        System.out.println("found "+avgRatingList.size()+" movies");
        Collections.sort(avgRatingList);
        for (Rating r : avgRatingList){
            System.out.println(r.getValue()+"\t"+MovieDatabase.getYear(r.getItem())+"\t"
             +MovieDatabase.getTitle(r.getItem()));
        }
    }
    
    public void printAverageRatingsByGenre(){
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        MovieDatabase.initialize(moviefile);
        Filter gf = new GenreFilter("Comedy");
        System.out.println("read data for "+tr.getRaterSize()+" raters");
        System.out.println("read data for " + MovieDatabase.size()+" movies");
        int minimalRaters = 20;
        ArrayList<Rating> avgRatingList = tr.getAverageRatingByFilter(minimalRaters, gf);
        System.out.println("found "+avgRatingList.size()+" movies");
        Collections.sort(avgRatingList);
        for (Rating r : avgRatingList){
            System.out.println(r.getValue()+"\t"+MovieDatabase.getTitle(r.getItem()));
            System.out.println("\t"+MovieDatabase.getGenres(r.getItem()));
        }
    }
    
    public void printAverageRatingsByMinutes(){
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        MovieDatabase.initialize(moviefile);
        Filter mf = new MinutesFilter(105, 135);
        System.out.println("read data for "+tr.getRaterSize()+" raters");
        System.out.println("read data for " + MovieDatabase.size()+" movies");
        int minimalRaters = 5;
        ArrayList<Rating> avgRatingList = tr.getAverageRatingByFilter(minimalRaters, mf);
        System.out.println("found "+avgRatingList.size()+" movies");
        
        Collections.sort(avgRatingList);
        for (Rating r : avgRatingList){
            System.out.println(r.getValue()+"\t"+"Time: "+MovieDatabase.getMinutes(r.getItem())
               +"\t" + MovieDatabase.getTitle(r.getItem()));
        }
        
        
    }
    
    public void printAverageRatingsByDirector(){
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        MovieDatabase.initialize(moviefile);
        Filter df = new DirectorFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        System.out.println("read data for "+tr.getRaterSize()+" raters");
        System.out.println("read data for " + MovieDatabase.size()+" movies");
        int minimalRaters = 4;
        ArrayList<Rating> avgRatingList = tr.getAverageRatingByFilter(minimalRaters, df);
        System.out.println("found "+avgRatingList.size()+" movies");
        
        Collections.sort(avgRatingList);
        for (Rating r : avgRatingList){
            System.out.println(r.getValue()+"\t"+MovieDatabase.getTitle(r.getItem()));
            System.out.println("\t"+MovieDatabase.getDirector(r.getItem()));
        }
        
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        MovieDatabase.initialize(moviefile);
        AllFilters af = new AllFilters();
        int minimalRaters = 8;
        af.addFilter(new YearAfterFilter(1990));
        af.addFilter(new GenreFilter("Drama"));
        ArrayList<Rating> avgRatingList = tr.getAverageRatingByFilter(minimalRaters, af);
        System.out.println("read data for "+tr.getRaterSize()+" raters");
        System.out.println("read data for " + MovieDatabase.size()+" movies");
        System.out.println(avgRatingList.size()+" movies matched");
        
        Collections.sort(avgRatingList);
        for (Rating r : avgRatingList){
            System.out.println(r.getValue()+"\t"+MovieDatabase.getYear(r.getItem())+"\t"
             +MovieDatabase.getTitle(r.getItem()));
            System.out.println("\t"+ MovieDatabase.getGenres(r.getItem()));
        }
        
    }
    
    public void printAverageRatingsByDirectorsAndMinutes(){
        ThirdRatings tr = new ThirdRatings(ratingsfile);
        MovieDatabase.initialize(moviefile);
        AllFilters af = new AllFilters();
        int minimalRaters = 3;
        af.addFilter(new MinutesFilter(90, 180));
        af.addFilter(new DirectorFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));
        ArrayList<Rating> avgRatingList = tr.getAverageRatingByFilter(minimalRaters, af);
        System.out.println("read data for "+tr.getRaterSize()+" raters");
        System.out.println("read data for " + MovieDatabase.size()+" movies");
        System.out.println(avgRatingList.size()+" movies matched");
        
        Collections.sort(avgRatingList);
        for (Rating r : avgRatingList){
            System.out.println(r.getValue()+"\t"+"Time: "+MovieDatabase.getMinutes(r.getItem())
               +"\t" + MovieDatabase.getTitle(r.getItem()));
            System.out.println("\t"+ MovieDatabase.getDirector(r.getItem()));
        }
        
    }
    
}
