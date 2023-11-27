import java.util.*;


public class DirectorFilter implements Filter
{
    private String[] directorList;
    
    public DirectorFilter(String director){
        directorList = director.split(",");
    }
    
    @Override
    public boolean satisfies(String id){
        String movieDirector = MovieDatabase.getDirector(id);
        for (String director : directorList){
            if (movieDirector.indexOf(director) != -1){
                return true;
            }
        }
        return false;
    }
}
