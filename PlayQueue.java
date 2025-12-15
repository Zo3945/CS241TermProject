
import java.util.*;

public class PlayQueue {

    private Deque<Song> queue = new ArrayDeque<>();
    private Deque<Song> historyStack = new ArrayDeque<>();
    boolean currSong = false;

    public void buildQueue(ArrayList<Song> songlist){
        queue.clear();
        historyStack.clear();

    }

    // Add song to end of queue
    public void addSong(Song song) {
        queue.addLast(song);
    }

    // Playing the next song fifo
    public Song playNext() {
        return queue.pollFirst();
    }

    // peek the song without skipping the next song 
    public Song peekNext() {
        return queue.peekFirst();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    
    public void printQueue() {
        if (queue.isEmpty()) {
            System.out.println("Queue is empty.");
        } else {
            System.out.println("Current Queue:");
            queue.forEach(System.out::println);
        }
    }
}
