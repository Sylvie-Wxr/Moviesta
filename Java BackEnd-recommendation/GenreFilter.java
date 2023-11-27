import java.util.*;


public class GenreFilter implements Filter
{
    private String myGenre;
    
    public GenreFilter(String genre){
        myGenre = genre;
    }
    
    @Override
    public boolean satisfies(String id){
        String movieGenre = MovieDatabase.getGenres(id).toLowerCase();
        if (movieGenre.indexOf(myGenre.toLowerCase()) != -1){
            return true;
        }
        return false;
    }
}
