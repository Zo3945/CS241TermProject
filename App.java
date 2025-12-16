import java.util.*;

public class App {
    public static void main(String[] args) {
        App app = new App();
        app.run();
    }
    MainPlaylist playlist = new MainPlaylist();
    PlayQueue playQueue = new PlayQueue();
    Scanner scanner = new Scanner(System.in);

    public App () {
        playlist = new MainPlaylist();
        playQueue = new PlayQueue();
        scanner = new Scanner(System.in);
    }

    public void run () {
        boolean running = true;

        while (running) {
            showMenu(); //displays the Menu
            if (!scanner.hasNextInt()) {
            System.out.println("Please enter 1 or 2.");
            scanner.nextLine(); // discard bad input
            return;
            }
        int choice = scanner.nextInt();
        scanner.nextLine();

            //each case calls a helper method
            switch (choice) {
                case 1:
                    handleLoadSongs();
                    break;
                case 2:
                    handleAddSong();
                    break;
                case 3:
                    handleSearch();
                    break;
                case 4:
                    handleSortAndQueue();
                    break;
                case 5:
                    handlePlayNext();
                    break;
                case 6:
                    handlePlayPrevious();
                    break;
                case 7:
                    playQueue.printQueue(); // optional, testing
                    break;
                case 8:
                    playQueue.clear();
                    System.out.println("Queue cleared!");
                    break;
                case 9:
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        scanner.close();
    }

    private void handlePlayNext() {
    Song next = playQueue.playNext();
    if (next != null) {
        System.out.println("Now playing: " + next);
    } else {
         System.out.println("Build the play queue first by sorting the playlist.");
        }
    }
    private void handlePlayPrevious() {
    Song prev = playQueue.playPrevious();
    if (prev != null) {
        System.out.println("Now playing: " + prev);
    } else {
         System.out.println("Build the play queue first by sorting the playlist.");
        }
    }
    
    private void handleLoadSongs(){
        System.out.println("1. Load from file");
        System.out.println("2. Enter songs manually \n(BPM and Duration(ms) are required!)");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1){
            System.out.println("Enter the file path: ");
            String filepath = scanner.nextLine();
            playlist.loadFromFile(filepath);

        } else if (choice == 2) {
            System.out.print("Enter a number of songs you wish to add: ");
            int count = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < count; i++) {
                System.out.print("Title: ");
                String title = scanner.nextLine();

                System.out.print("Artist: ");
                String artist = scanner.nextLine();

                System.out.print("Release Date (YYYY-DD-MM): ");
                String releaseDate = scanner.nextLine();

                System.out.print("Year: ");
                int year = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Duration: ");
                int duration = scanner.nextInt();
                scanner.nextLine();
                
                System.out.print("Genre: ");
                String genre = scanner.nextLine();

                System.out.print("BPM: ");
                double bpm = scanner.nextDouble();
                scanner.nextLine();
                

                Song song = new Song(title, artist, releaseDate, year, duration, genre, bpm);
                playlist.addSong(song);
            }

        }
    }

    private void handleAddSong(){
        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Artist: ");
        String artist = scanner.nextLine();

        System.out.print("Release Date (YYYY-DD-MM): ");
        String releaseDate = scanner.nextLine();

        System.out.print("Year: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Duraton: ");
        int duration = scanner.nextInt();
        scanner.nextLine();
            
        System.out.print("Genre: ");
        String genre = scanner.nextLine();

        System.out.print("BPM: ");
        double bpm = scanner.nextDouble();
        scanner.nextLine();
                
        Song song = new Song(title, artist, releaseDate, year, duration, genre, bpm);
        playlist.addSong(song);
    }

    private void handleSearch(){
        System.out.println("Search by:");
        System.out.println("1. Title");
        System.out.println("2. Artist");
        System.out.println("3. Genre");
        System.out.println("4. Year");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            System.out.print("Enter title: ");
            String title = scanner.nextLine();
            Song song = playlist.searchByTitle(title);
            System.out.println(song != null ? song : "Song not found");

        } else if (choice == 2) {
            System.out.print("Enter artist: ");
            String artist = scanner.nextLine();
            playlist.searchByArtist(artist).forEach(System.out::println);

        } else if (choice == 3) {
            System.out.print("Enter genre: ");
            String genre = scanner.nextLine();
            playlist.searchByGenre(genre).forEach(System.out::println);

        } else if (choice == 4) {
            System.out.print("Enter year: ");
            int year = scanner.nextInt();
            scanner.nextLine();
            playlist.searchByYear(year).forEach(System.out::println);
        }
    }

    private void handleSortAndQueue(){
        System.out.println("Sort by:");
        System.out.println("1. Artist");
        System.out.println("2. Genre");
        System.out.println("3. Title");
        System.out.println("4. Year");
        System.out.println("5. Shuffle");

        int sortChoice = scanner.nextInt();
        scanner.nextLine();
        boolean direction = true;

        if (sortChoice != 5) {
        System.out.println("Direction:");
        System.out.println("1. Normal");
        System.out.println("2. Reversed");
        int dirChoice = scanner.nextInt();
        scanner.nextLine();
        direction = (dirChoice == 1);
    }

    ArrayList<Song> songs = playlist.getAllSongs();
    ArrayList<Song> sorted = null;

    switch (sortChoice) {
        case 1:
            sorted = SongSorter.sortByArtist(songs, direction);
            break;
        case 2:
            sorted = SongSorter.sortByGenre(songs, direction);
            break;
        case 3:
            sorted = SongSorter.sortByTitle(songs, direction);
            break;
        case 4:
            sorted = SongSorter.sortByYear(songs, direction);
            break;
        case 5:
            sorted = SongSorter.shuffle(songs);
            break;
        default:
            System.out.println("Invalid sort option.");
            return;
        }

        playQueue.buildFromList(sorted);
        System.out.println("Play queue rebuilt.");
    }
    private void showMenu() {
        System.out.println("\n=== Music App Menu ===\n");
        System.out.println("1. Load songs from file");
        System.out.println("2. Add a new song");
        System.out.println("3. Search songs");
        System.out.println("4. Sort playlist and build queue");
        System.out.println("5. Play next song");
        System.out.println("6. Play previous song");
        System.out.println("7. Print current queue");
        System.out.println("8. Clear queue");
        System.out.println("9. Exit");
        System.out.print("Enter your choice: ");
    }
}

    

    