import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class MainPlaylistTest {

    private MainPlaylist playlist;

    @Before
    public void setUp() {
        playlist = new MainPlaylist();

        Song song1 = new Song(
                "Blinding Lights",
                "The Weeknd",
                "2019-11-29",
                2019,
                200,
                "Pop",
                171.0
        );

        Song song2 = new Song(
                "Someone Like You",
                "Adele",
                "2011-01-24",
                2011,
                285,
                "Soul",
                67.0
        );

        playlist.addSong(song1);
        playlist.addSong(song2);
    }

    @Test
    public void testAddSongAndMasterListSize() {
        assertEquals(2, playlist.getAllSongs().size());
    }

    @Test
    public void testSearchByTitleFound() {
        Song song = playlist.searchByTitle("Blinding Lights");
        assertNotNull(song);
        assertEquals("The Weeknd", song.getArtist());
    }

    @Test
    public void testSearchByTitleNotFound() {
        assertNull(playlist.searchByTitle("Nonexistent Song"));
    }

    @Test
    public void testSearchByArtistFound() {
        ArrayList<Song> songs = playlist.searchByArtist("Adele");
        assertEquals(1, songs.size());
        assertEquals("Someone Like You", songs.get(0).getTitle());
    }

    @Test
    public void testSearchByArtistNotFound() {
        ArrayList<Song> songs = playlist.searchByArtist("Drake");
        assertTrue(songs.isEmpty());
    }

    @Test
    public void testSearchByGenreFound() {
        ArrayList<Song> songs = playlist.searchByGenre("Pop");
        assertEquals(1, songs.size());
        assertEquals("Blinding Lights", songs.get(0).getTitle());
    }

    @Test
    public void testSearchByGenreNotFound() {
        ArrayList<Song> songs = playlist.searchByGenre("Rock");
        assertTrue(songs.isEmpty());
    }

    @Test
    public void testSearchByYearFound() {
        ArrayList<Song> songs = playlist.searchByYear(2019);
        assertEquals(1, songs.size());
        assertEquals("Blinding Lights", songs.get(0).getTitle());
    }

    @Test
    public void testSearchByYearNotFound() {
        ArrayList<Song> songs = playlist.searchByYear(1990);
        assertTrue(songs.isEmpty());
    }
}