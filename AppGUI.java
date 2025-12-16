import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AppGUI extends JFrame {

    private final MainPlaylist playlist = new MainPlaylist();
    private final PlayQueue playQueue = new PlayQueue();

    // UI components
    private final JTextField fileField = new JTextField(25);
    private final JButton loadButton = new JButton("Load CSV");

    private final JComboBox<String> sortBox = new JComboBox<>(new String[]{
            "Artist", "Genre", "Title", "Year", "Shuffle"
    });
    private final JComboBox<String> dirBox = new JComboBox<>(new String[]{
            "Normal", "Reversed"
    });
    private final JButton buildQueueButton = new JButton("Build Queue");

    private final JButton nextButton = new JButton("Play Next");
    private final JButton prevButton = new JButton("Play Previous");

    private final JLabel nowPlayingLabel = new JLabel("Now Playing: (none)");
    private final DefaultListModel<String> listModel = new DefaultListModel<>();
    private final JList<String> displayList = new JList<>(listModel);

    public AppGUI() {
        super("Playlist Organizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        // Top: file loading
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.add(new JLabel("CSV Path: "));
        top.add(fileField);
        top.add(loadButton);

        // Middle: sort controls
        JPanel mid = new JPanel(new FlowLayout(FlowLayout.LEFT));
        mid.add(new JLabel("Sort: "));
        mid.add(sortBox);
        mid.add(new JLabel("Direction: "));
        mid.add(dirBox);
        mid.add(buildQueueButton);

        // Bottom: playback controls
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottom.add(prevButton);
        bottom.add(nextButton);
        bottom.add(nowPlayingLabel);

        // Center: list display
        JScrollPane scrollPane = new JScrollPane(displayList);

        // Layout
        setLayout(new BorderLayout());
        add(top, BorderLayout.NORTH);
        add(mid, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        // Hook up actions
        wireEvents();
    }

    private void wireEvents() {
        loadButton.addActionListener(e -> {
            String path = fileField.getText().trim();
            if (path.isEmpty()) {
                showMessage("Please enter a CSV file path.");
                return;
            }
            int loaded = playlist.loadFromFile(path);
            showMessage("Loaded " + loaded + " songs!");
            refreshPlaylistView();
        });

        buildQueueButton.addActionListener(e -> {
            ArrayList<Song> songs = playlist.getAllSongs();
            if (songs.isEmpty()) {
                showMessage("Your playlist is empty. Load songs first.");
                return;
            }

            String sortChoice = (String) sortBox.getSelectedItem();
            boolean normal = "Normal".equals(dirBox.getSelectedItem());
            ArrayList<Song> sorted;

            if ("Artist".equals(sortChoice)) sorted = SongSorter.sortByArtist(songs, normal);
            else if ("Genre".equals(sortChoice)) sorted = SongSorter.sortByGenre(songs, normal);
            else if ("Title".equals(sortChoice)) sorted = SongSorter.sortByTitle(songs, normal);
            else if ("Year".equals(sortChoice)) sorted = SongSorter.sortByYear(songs, normal);
            else sorted = SongSorter.shuffle(songs);

            playQueue.buildFromList(sorted);
            showMessage("Play queue rebuilt (" + sorted.size() + " songs).");
            refreshQueueView();
        });

        nextButton.addActionListener(e -> {
            Song s = playQueue.playNext();
            if (s == null) {
                showMessage("Queue empty. Build the queue first.");
                return;
            }
            nowPlayingLabel.setText("==Now Playing:== " + s);
            refreshQueueView();
        });

        prevButton.addActionListener(e -> {
            Song s = playQueue.playPrevious();
            if (s == null) {
                showMessage("No previous song yet.");
                return;
            }
            nowPlayingLabel.setText("Now Playing: " + s);
            refreshQueueView();
        });
    }

    private void refreshPlaylistView() {
        listModel.clear();
        for (Song s : playlist.getAllSongs()) {
            listModel.addElement(s.toString());
        }
    }

    private void refreshQueueView() {
        // If your PlayQueue has no "getQueueSnapshot()" method, see note below.
        listModel.clear();
        listModel.addElement("(Queue view not implemented yet)");
    }

    private void showMessage(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AppGUI().setVisible(true));
    }
}
