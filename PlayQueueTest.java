import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PlayQueueTest {

    private PlayQueue playQueue;
    private Song song1;
    private Song song2;
    private Song song3;

    @Before
    public void setUp() {
        playQueue = new PlayQueue();

        // Match your Song constructor:
        // Song(String title, String artist, String releaseDate,
        //      int year, int duration, String genre, double bpm)

        song1 = new Song("Song A", "Artist A", "2020-01-01", 2020, 180, "Pop", 120);
        song2 = new Song("Song B", "Artist B", "2021-01-01", 2021, 200, "Rock", 130);
        song3 = new Song("Song C", "Artist C", "2019-01-01", 2019, 210, "Jazz", 90);
    }

    @Test
    public void testQueueStartsEmpty() {
        assertTrue(playQueue.isEmpty());
    }

    @Test
    public void testAddSong() {
        playQueue.addSong(song1);

        assertFalse(playQueue.isEmpty());
        assertEquals(song1, playQueue.peekNext());
    }

    @Test
    public void testFIFOOrder() {
        playQueue.addSong(song1);
        playQueue.addSong(song2);
        playQueue.addSong(song3);

        assertEquals(song1, playQueue.playNext());
        assertEquals(song2, playQueue.playNext());
        assertEquals(song3, playQueue.playNext());
        assertTrue(playQueue.isEmpty());
    }

    @Test
    public void testPeekDoesNotRemoveSong() {
        playQueue.addSong(song1);
        playQueue.addSong(song2);

        Song peeked = playQueue.peekNext();

        assertEquals(song1, peeked);
        assertFalse(playQueue.isEmpty());
    }

    @Test
    public void testPlayNextOnEmptyQueue() {
        assertNull(playQueue.playNext());
    }
}