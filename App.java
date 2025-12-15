import java.util.*;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        MainPlaylist playlist = new MainPlaylist();

        playlist.loadFromFile("songs.csv");

        boolean running = true;

        while (running) {
            System.out.println("\n=== Smart Playlist Menu ===");
            System.out.println("1. View All Songs");
            System.out.println("2. Search by Artist");
            System.out.println("3. Search by Genre");
            System.out.println("4. Sort by Artist");
            System.out.println("5. Sort by Release Year");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {

                case 1:
                    playlist.getAllSongs().forEach(System.out::println);
                    break;

                case 2:
                    System.out.print("Artist: ");
                    String artist = scanner.nextLine();
                    playlist.searchByArtist(artist)
                            .forEach(System.out::println);
                    break;

                case 3:
                    System.out.print("Genre: ");
                    String genre = scanner.nextLine();
                    playlist.searchByGenre(genre)
                            .forEach(System.out::println);
                    break;

                case 4:
                    Sorter.sortByArtist(playlist.getAllSongs())
                          .forEach(System.out::println);
                    break;

                case 5:
                    Sorter.sortByYear(playlist.getAllSongs())
                          .forEach(System.out::println);
                    break;

                case 6:
                    running = false;
                    System.out.println("Catch you My Heart ❤️❤️❤️");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
