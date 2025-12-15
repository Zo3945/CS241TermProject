import java.util.*;

public class Sorter {

    // this jsut sorts by Alphabetical in artist
    public static List<Song> sortByArtist(List<Song> songs) {
        List<Song> sorted = new ArrayList<>(songs);
        Collections.sort(sorted, Comparator.comparing(Song::getArtist));
        return sorted;
    }

    
    public static List<Song> sortByGenre(List<Song> songs) {
        List<Song> sorted = new ArrayList<>(songs);
        Collections.sort(sorted, Comparator.comparing(Song::getGenre));
        return sorted;
    }

    
    public static List<Song> sortByYear(List<Song> songs) {
        List<Song> sorted = new ArrayList<>(songs);
        Collections.sort(sorted, Comparator.comparingInt(Song::getReleaseYear));
        return sorted;
    }

    
    public static List<Song> shuffle(List<Song> songs) {
        List<Song> shuffled = new ArrayList<>(songs);
        Collections.shuffle(shuffled);
        return shuffled;
    }
}