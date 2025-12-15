import java.util.*;
import java.io.*;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

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

    public void loadFromFile (String filename){
        CSVReader filereader = null;
        try {
            filereader = new CSVReader(new FileReader(filename));
            String[] line;
            //reads first line to discard header
            filereader.readNext();
            //While loop used for reading every line in csv and make it a song object by adding values at the commas.
            //addSong method to store it.
            int lineNumber = 2;
            while ((line = filereader.readNext()) != null){
                try {
                    if((line[4].isEmpty()) || (line[5].isEmpty()) || (line[22].isEmpty()) ){
                        lineNumber++;
                        continue;
                    }
                    //parse date to get year:
                    String raw = line[4];
                    String digits = raw.replaceAll("\\D", ""); // keep only digits
                    if (digits.length() < 4) {
                        throw new NumberFormatException("Invalid year");
                    }
                    int year = Integer.parseInt(digits.substring(0, 4));

                    //                Track Name, Artist(s),Released,Year,      Duration,             Genre,           BPM.
                    Song song = new Song(line[1], line[3], line[4], year, Integer.parseInt(line[5]), line[10], Double.parseDouble(line[22]));
                    addSong(song);
                } catch (NumberFormatException e) {
                    lineNumber++;
                    System.out.println("Invalid numeric value @line " + lineNumber);
                    continue;
                }
                lineNumber++;
            }

        } catch (IOException | CsvException e){
            System.out.println("File does not exist");
            e.printStackTrace();

        } finally {
            try {
                if(filereader != null) {
                    filereader.close();
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
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
     private String assignMood(double bpm) {
        if (bpm < 90) return "Chill";
        else if (bpm <= 120) return "Happy";
        else return "Energetic";
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