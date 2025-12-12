public class Song {
    private String songTitle;
    private String artist;
    private String genre;
    private String releaseDate;
    private int releaseYear;
    private int duration;
    private int bpm;

    public Song(String songTitle, String artist, String genre, String releaseDate, int releaseYear, int duration, int bpm){
        this.songTitle = songTitle;
        this.artist = artist;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.releaseYear = releaseYear;
        this.duration = duration;
        this.bpm = bpm;
    }
    public String getTitle(){
        return this.songTitle;
    }
    public String getArtist(){
        return this.artist;
    }
    public String getGenre(){
        return this.genre;
    }
    public String getReleaseDate(){
        return this.releaseDate;
    }
    public int getReleaseYear(){
        return this.releaseYear;
    }
    public int getDuration(){
        return this.duration;
    }
    public int getBPM(){
        return this.bpm;
    }
    public String toString(){
        return "Song Title: "+ this.songTitle + ", Artist: "+ this.artist+", Genre: "+this.genre+", Released: "+this.releaseDate+", Duaration: "+this.duration;
    }
}
