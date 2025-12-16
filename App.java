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
            
            int choice;
                while(true){
                    String input = scanner.nextLine();
                    try{
                        choice = Integer.parseInt(input);
                        if(choice < 1 || choice > 9){
                            System.out.println("Please enter a valid number.\n");
                            continue;
                        } break;
                    } catch(NumberFormatException e){
                        System.out.println("Please enter a valid number.\n");
                    }
                }

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
        System.out.println("\n==Now Playing==  " + next);

    } else if (playQueue.isEmpty()) {
        System.out.println("Build the play queue first by sorting the playlist.");

    } else {
        System.out.println("No song in da queue.");
    }
}

    private void handlePlayPrevious() {
        Song prev = playQueue.playPrevious();
        if (prev != null) {
            System.out.println("\n==Now Playing== " + prev);
        } else if (playQueue.isEmpty()){
            System.out.println("Build the play queue first by sorting the playlist.");
        } else {
            System.out.println("Playlist has no previous song yet.");
        }
    }
    
    private void handleLoadSongs(){
        System.out.println("1. Load from file");
        System.out.println("2. Enter songs manually \n(BPM and Duration(ms) are required!)");

        int choice;
        System.out.print("\nEnter your choice:--> ");
        while(true){
            String input = scanner.nextLine();
            try{
                choice = Integer.parseInt(input);
                if(choice < 1 || choice > 2){
                    System.out.println("Please enter a valid number.\n");
                    continue;
                } break;
            } catch(NumberFormatException e){
                        System.out.println("Please enter a valid number.");
            }
        }
        if (choice == 1){
            System.out.println("Enter the file path: ");
            String filepath = scanner.nextLine();

            int count = playlist.loadFromFile(filepath);
            if (count > 0) {
                System.out.println("Successfully loaded " + count + " songs into playlist!");
            } else {
                System.out.println("File loaded, but no valid songs were found.");
            }

        } else if(choice == 2) {
            System.out.print("Enter a number of songs you wish to add: ");
            int count = scanner.nextInt();
            scanner.nextLine();

            for(int i = 0; i < count; i++) {
                System.out.print("Title: ");
                String title = scanner.nextLine();

                System.out.print("Artist: ");
                String artist = scanner.nextLine();

                System.out.print("Release Date (YYYY-DD-MM): ");
                String releaseDate = scanner.nextLine();

                int year;
                while(true){
                    System.out.print("Year: ");
                    String input = scanner.nextLine();
                    try{
                        year = Integer.parseInt(input);
                        if(year < 1000 || year > 3000){
                            System.out.println("Please enter a valid number from 1000-3000.\n");
                            continue;
                        } break;
                    } catch(NumberFormatException e){
                        System.out.println("Please enter a valid number.\n");
                    }
                }
                
                int duration;
                while(true){
                    System.out.print("Duration (ms): ");
                    String input = scanner.nextLine();
                    try{
                        duration = Integer.parseInt(input);
                        if(duration <= 0){
                            System.out.println("Please enter a positive duration time.\n ");
                            continue;
                        } break;
                    } catch(NumberFormatException e){
                        System.out.println("Please enter a valid number.\n");
                    }
                }
            
                System.out.print("Genre: ");
                String genre = scanner.nextLine();

                double bpm;
                while(true){
                    System.out.print("BPM: ");
                    String input = scanner.nextLine();
                    try{
                        bpm = Double.parseDouble(input);
                        if(bpm < 0){
                            System.out.println("Please enter a non negative number!\n");
                            continue;
                        } break;
                    } catch(NumberFormatException e){
                        System.out.println("Please enter a valid number.\n");
                    }
                }
                Song song = new Song(title, artist, releaseDate, year, duration, genre, bpm);
                playlist.addSong(song);
            }
        }
        else{
            System.out.println("Invalid option. Please enter 1 or 2.");
        }
    }

    private void handleAddSong(){
        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Artist: ");
        String artist = scanner.nextLine();

        System.out.print("Release Date (YYYY-DD-MM): ");
        String releaseDate = scanner.nextLine();

        int year;
        while(true){
            System.out.print("Year: ");
            String input = scanner.nextLine();
            try{
                year = Integer.parseInt(input);
                if(year < 1000 || year > 3000){
                    System.out.println("Please enter a valid number from 1000-3000.\n");
                    continue;
                } break;
            } catch(NumberFormatException e){
                System.out.println("Please enter a valid number.");
            }
        }

        int duration;
        while(true){
            System.out.print("Duration (ms): ");
            String input = scanner.nextLine();
            try{
                duration = Integer.parseInt(input);
                if(duration <= 0){
                    System.out.println("Please enter a positive duration time.\n ");
                    continue;
                } break;
            } catch(NumberFormatException e){
                System.out.println("Please enter a valid number.");
            }
        }
            
        System.out.print("Genre: ");
        String genre = scanner.nextLine();

        double bpm;
        while(true){
            System.out.print("BPM: ");
            String input = scanner.nextLine();
            try{
                bpm = Double.parseDouble(input);
                if(bpm < 0){
                    System.out.println("Please enter a non negative number! ");
                    continue;
                } break;
            } catch(NumberFormatException e){
                System.out.println("Please enter a valid number.");
            }
        }
                
        Song song = new Song(title, artist, releaseDate, year, duration, genre, bpm);
        playlist.addSong(song);
    }

    private void handleSearch(){
        System.out.println("Search by:");
        System.out.println("1. Title");
        System.out.println("2. Artist");
        System.out.println("3. Genre");
        System.out.println("4. Year");

        int choice;
        System.out.print("\nEnter your choice --> ");
        while(true){
            String input = scanner.nextLine();
            try{
                choice = Integer.parseInt(input);
                if(choice < 1 || choice > 4){
                    System.out.println("Please enter a valid number.\n");
                    continue;
                } break;
            } catch(NumberFormatException e){
                        System.out.println("Please enter a valid number.");
            }
        }

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

        int sortChoice;
        System.out.print("\nEnter your choice --> ");
        while(true){
            String input = scanner.nextLine();
            try{
                sortChoice = Integer.parseInt(input);
                if(sortChoice < 1 || sortChoice > 5){
                    System.out.println("Please enter a valid number.\n");
                    continue;
                } break;
            } catch(NumberFormatException e){
                        System.out.println("Please enter a valid number.");
            }
        }
        boolean direction = true;

        if (sortChoice != 5) {
        System.out.println("Direction:");
        System.out.println("1. Normal");
        System.out.println("2. Reversed");

        int dirChoice;
        System.out.print("\nEnter your choice --> ");
        while(true){
            String input = scanner.nextLine();
            try{
                dirChoice = Integer.parseInt(input);
                if(dirChoice < 1 || dirChoice > 2){
                    System.out.println("Please enter a valid number.\n");
                    continue;
                } break;
            } catch(NumberFormatException e){
                System.out.println("Please enter a valid number.");
            }
        }
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
        System.out.println("Play queue is now rebuilt.");
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
        System.out.print("Enter your choice: --> ");
    }
}