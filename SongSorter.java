import java.util.*;

public class SongSorter {

    // this jsut sorts by Alphabetical in artist
    public static ArrayList<Song> sortByArtist(ArrayList<Song> songs) {
        ArrayList<Song> sorted = new ArrayList<>(songs);
        Collections.sort(sorted, Comparator.comparing(Song::getArtist));
        return sorted;
    }

    
    public static ArrayList<Song> sortByGenre(ArrayList<Song> songs) {
        ArrayList<Song> sorted = new ArrayList<>(songs);
        Collections.sort(sorted, Comparator.comparing(Song::getGenre));
        return sorted;
    }

    
    public static ArrayList<Song> sortByYear(ArrayList<Song> songs) {
        ArrayList<Song> sorted = new ArrayList<>(songs);
        Collections.sort(sorted, Comparator.comparingInt(Song::getReleaseYear));
        return sorted;
    }

    
    public static ArrayList<Song> shuffle(ArrayList<Song> songs) {
        ArrayList<Song> shuffled = new ArrayList<>(songs);
        Collections.shuffle(shuffled);
        return shuffled;
    }
}