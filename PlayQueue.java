
import java.util.*;

public class PlayQueue {

    private Deque<Song> queue = new ArrayDeque<>();
    private Deque<Song> historyStack = new ArrayDeque<>();

    public void buildFromList(ArrayList<Song> songlist){
        queue.clear();
        historyStack.clear();
        for(Song song : songlist){
            queue.add(song);
        }
    }

    // Add song to end of queue
    public void addSong(Song song) {
        queue.addFirst(song);
    }

    // Playing the next song fifo
    public Song playNext() {
        if (queue.isEmpty()){
            return null;
        }
        //Poll the next song in the queue
        Song current = queue.poll();
        if (current != null){
            //Add this song to the history
            historyStack.push(current);
        }
        return current;
    }
    public Song playPrevious(){
        if (historyStack.isEmpty()) {
            return null;
        }
        //Pop the last played song from history
        Song prev = historyStack.pop();
        if (prev != null) {
            //Add it to the front of the queue, but do NOT push back to history
            queue.addFirst(prev);
        }
        return prev;
    }

    public boolean hasNext() {
        return !queue.isEmpty();
    }
    public boolean hasPrevious() { 
        return !historyStack.isEmpty(); 
    }

    // peek the song without skipping the next song 
    public Song peekNext() {
        return queue.peekFirst();
    }
    public Song peekPrevious(){
        return historyStack.peek();
    }
    public boolean isEmpty() {
        return queue.isEmpty();
    }
    public void clear(){
        queue.clear();
        historyStack.clear();
    }
}
