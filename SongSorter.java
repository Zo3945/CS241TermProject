import java.util.*;

public class SongSorter {
    // Sorts by Alphabetical
    public static ArrayList<Song> sortByArtist(ArrayList<Song> songs, boolean direction) {
    ArrayList<Song> sorted = new ArrayList<>(songs);

    Collections.sort(sorted, (a, b) -> {
        int artistCompare = a.getArtist().compareToIgnoreCase(b.getArtist());
        if(artistCompare != 0) {
            return artistCompare;
        }
        return a.getTitle().compareToIgnoreCase(b.getTitle()); //if arist name is the same, compare by title
        });
        if(!direction){ //true = A-Z order, false = reversed Order
            Collections.reverse(sorted);
        }
        return sorted;
    }
    
    public static ArrayList<Song> sortByGenre(ArrayList<Song> songs, boolean direction) {
        ArrayList<Song> sorted = new ArrayList<>(songs);
        
        Collections.sort(sorted, (a, b) -> {
            int genreCompare = a.getGenre().compareToIgnoreCase(b.getGenre());
            if(genreCompare != 0){
                return genreCompare;
            }
            return a.getTitle().compareToIgnoreCase(b.getTitle());
        });
        if(!direction){ //true = A-Z order, false = reversed Order
            Collections.reverse(sorted);
        }
        return sorted;
    }
    
    public static ArrayList<Song> sortByTitle(ArrayList<Song> songs, boolean direction) {
        ArrayList<Song> sorted = new ArrayList<>(songs);
        
        Collections.sort(sorted, (a, b) -> {
            int titleCompare = a.getTitle().compareToIgnoreCase(b.getTitle());
            if(titleCompare != 0){
                return titleCompare;
            }
            return a.getArtist().compareToIgnoreCase(b.getArtist());
        });
        if(!direction){ //true = A-Z order, false = reversed Order
            Collections.reverse(sorted);
        }
        return sorted;
    }
    public static ArrayList<Song> sortByYear(ArrayList<Song> songs, boolean direction) {
        ArrayList<Song> sorted = new ArrayList<>(songs);

        Collections.sort(sorted, (a, b) -> {
            int yearCompare = Integer.compare(a.getReleaseYear(), b.getReleaseYear());
            if(yearCompare != 0){
                return yearCompare;
            }
            return a.getTitle().compareToIgnoreCase(b.getTitle());
        });
        if(!direction){ //true = A-Z order, false = reversed Order
            Collections.reverse(sorted);
        }
        return sorted;
    }
    public static ArrayList<Song> shuffle(ArrayList<Song> songs) {
        ArrayList<Song> shuffled = new ArrayList<>(songs);
        Collections.shuffle(shuffled);
        return shuffled;
    }
}