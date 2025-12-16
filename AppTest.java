import static org.junit.Assert.*;
import org.junit.*;
import java.io.*;
import java.lang.reflect.Method;
import java.util.*;

public class AppTest {

    private App app;
    private Song song1;
    private Song song2;

    @Before
    public void setUp() {
        app = new App();
        song1 = new Song("Song A", "Artist A", "2020-01-01", 2020, 180, "Pop", 120.0);
        song2 = new Song("Song B", "Artist B", "2021-02-02", 2021, 200, "Rock", 130.0);
    }

    @Test
    public void testHandlePlayNext_withSong() throws Exception {
        PlayQueue pq = new PlayQueue();
        ArrayList<Song> list = new ArrayList<>();
        list.add(song1);
        pq.buildFromList(list);
        app.playQueue = pq;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream oldOut = System.out;
        System.setOut(new PrintStream(baos));
        try {
            Method m = App.class.getDeclaredMethod("handlePlayNext");
            m.setAccessible(true);
            m.invoke(app);
        } finally {
            System.setOut(oldOut);
        }

        String out = baos.toString();
        assertTrue("Should announce now playing", out.contains("==Now Playing=="));
        assertTrue("Should include song title", out.contains(song1.getTitle()));
    }

    @Test
    public void testHandlePlayNext_whenQueueEmpty() throws Exception {
        app.playQueue = new PlayQueue();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream oldOut = System.out;
        System.setOut(new PrintStream(baos));
        try {
            Method m = App.class.getDeclaredMethod("handlePlayNext");
            m.setAccessible(true);
            m.invoke(app);
        } finally {
            System.setOut(oldOut);
        }

        String out = baos.toString();
        assertTrue("Should prompt to build the play queue", out.contains("Build the play queue first by sorting the playlist."));
    }

    @Test
    public void testHandlePlayPrevious_whenHasPrevious() throws Exception {
        PlayQueue pq = new PlayQueue();
        ArrayList<Song> list = new ArrayList<>();
        list.add(song1);
        list.add(song2);
        pq.buildFromList(list);

        Song played = pq.playNext();
        assertEquals(song1.getTitle(), played.getTitle());

        app.playQueue = pq;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream oldOut = System.out;
        System.setOut(new PrintStream(baos));
        try {
            Method m = App.class.getDeclaredMethod("handlePlayPrevious");
            m.setAccessible(true);
            m.invoke(app);
        } finally {
            System.setOut(oldOut);
        }

        String out = baos.toString();
        assertTrue("Should announce previous now playing", out.contains("==Now Playing==") || out.contains("Now Playing"));
        assertTrue("Should include the previously played song title", out.contains(song1.getTitle()));
    }
}
