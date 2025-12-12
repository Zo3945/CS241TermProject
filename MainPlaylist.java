import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.SortedMap;

public class MainPlaylist {
    private ArrayList<Song> masterList = new ArrayList<>(); // all songs
    Map<String, Song> titleMap = new HashMap<>(); // song title --> song with that title
    Map<String, ArrayList<Song>> artistMap = new HashMap<>(); // artist --> list of songs from that artist including their features
    Map<String, ArrayList<Song>> genreMap = new HashMap<>(); // genre --> list of songs with that genre(songs can have multiple genres)
    Map<Integer, ArrayList<Song>> yearMap = new HashMap<>(); // year --> list of songs from that year 
    
    /**
    Adds a song to the main playlist and updates all the Hashmaps associated with the data
     * @param song The song you are adding to the playlist while updating HashMaps
     **/
    public void addSong(Song song){
        masterList.add(song);
        titleMap.put(song.getTitle(), song);
        artistMap.computeIfAbsent(song.getArtist(), artistSongList -> new ArrayList<>()).add(song);
        genreMap.computeIfAbsent(song.getGenre(), genreList -> new ArrayList<>()).add(song);
        yearMap.computeIfAbsent(song.getReleaseYear(), yearList -> new ArrayList<>()).add(song);
    }
    public void loadFromFile(String filename){
        
    }
    /**
     * @return Returns the master list for sorting
     */
    public ArrayList<Song> getAllSongs(){
        return masterList;
    }
    /**
     * @return Returns the title from the title map
     */
    public Song searchByTitle(String title){
        return titleMap.get(title);
    }
    /**
     * @return Returns the list of songs from chosen artist
     */
    public ArrayList<Song> searchByArtist(String artist){
        if(artist == null || !artistMap.containsKey(artist)){
            return null;
            //returns message saying this artist is not in the playlist
        }
        return artistMap.get(artist);
    }
    /**
     * @return Returns the list of genres from chosen genre
     */
    public ArrayList<Song> searchByGenre(String genre){
        if(genre == null || !genreMap.containsKey(genre)){
            return null;
            //returns message saying this genre is not in the playlist
        }
        return genreMap.get(genre);
    }
    /**
     * @return Returns list songs from that year
     */
    public ArrayList<Song> searchByYear(Integer year){
        if(year == null || !yearMap.containsKey(year)){
            return null;
            //returns message saying this artist is not in the playlist
        }
        return yearMap.get(year);
    }
}