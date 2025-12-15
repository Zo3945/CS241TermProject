
import static org.junit.Assert.*;
import java.io.*;
import org.junit.*;

public class PlayQueueTest {

    private PlayQueue playQueue;
    private Song song1;
    private Song song2;
    private Song song3;


    @BeforeEach
    void setUp() {
        playQueue = new PlayQueue();

        song1 = new Song("Song A", "Artist A", "Pop", "2020", 2020, 180, 120);
        song2 = new Song("Song B", "Artist B", "Rock", "2021", 2021, 200, 130);
        song3 = new Song("Song C", "Artist C", "Jazz", "2019", 2019, 210, 90);
    }

    @Test
    void testQueueStartsEmpty() {
        assertTrue(playQueue.isEmpty(), "Queue should start empty");
    }

    @Test
    void testAddSong() {
        playQueue.addSong(song1);

        assertFalse(playQueue.isEmpty(), "Queue should not be empty after adding a song");
        assertEquals(song1, playQueue.peekNext(), "Peek should return the first song added");
    }

    @Test
    void testFIFOOrder() {
        playQueue.addSong(song1);
        playQueue.addSong(song2);
        playQueue.addSong(song3);

        assertEquals(song1, playQueue.playNext());
        assertEquals(song2, playQueue.playNext());
        assertEquals(song3, playQueue.playNext());
        assertTrue(playQueue.isEmpty(), "Queue should be empty after playing all songs");
    }

    @Test
    void testPeekDoesNotRemoveSong() {
        playQueue.addSong(song1);
        playQueue.addSong(song2);

        Song peeked = playQueue.peekNext();

        assertEquals(song1, peeked, "Peek should return first song");
        assertFalse(playQueue.isEmpty(), "Peek should not remove the song");
    }

    @Test
    void testPlayNextOnEmptyQueue() {
        assertNull(playQueue.playNext(), "Playing from empty queue should return null");
    }
}
