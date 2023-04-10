package ui;

import model.Event;
import model.EventLog;
import model.GameList;
import persistence.JsonReaderGameList;
import persistence.JsonWriterGameList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// Gamelist Application
public class GameListUI extends JFrame implements WindowListener{
    public static final int WIDTH = 400;
    public static final int HEIGHT = 500;
    private static final String DEFAULT_DESTINATION = "./data/gamelist.json";
    private AddGameFrame addGameFrame;
    private GraphFrame graphFrame;
    private DeleteGameFrame deleteGameFrame;
    private JButton saveButton;
    private JButton loadButton;
    private JButton avgButton;
    private JButton totalButton;
    private ViewFrame viewFrame;
    private GameList games;

    // Effects : Constructs and initializes the application
    public GameListUI() {
        games = new GameList();
        initializeFrames();
        initializeAddButton();
        initializeDeleteButton();
        initializeViewButton();
        initializeGraphButton();
        initializeAvgButton();
        initializeTotalButton();
        initializeSaveButton();
        initializeLoadButton();
        initializeSaveAsButton();
        initializeLoadFromButton();
        this.setVisible(true);

    }

    // Modifies: this
    // Effects: initializes graph frame and graph button.
    private void initializeGraphButton() {
        JButton button = new JButton("Graph");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                graphFrame = new GraphFrame(games);
                graphFrame.setVisible(true);
            }
        });
        add(button);
    }

    // Modifies: this
    // Effects: initializes load from button
    private void initializeLoadFromButton() {
        JButton button = new JButton("Load From...");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(null, "Input the file name");
                if (input != null && input.length() > 0) {
                    load("./data/" + input + ".json");
                }
            }
        });
        add(button);
    }

    // Modifies: this
    // Effects: Initializes Save As Button
    private void initializeSaveAsButton() {
        JButton button = new JButton("Save as...");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(null, "Input the file name");
                if (input != null) {
                    if (input.length() <= 0) {
                        JOptionPane.showMessageDialog(null, "Input the destination!", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    save("./data/" + input + ".json");
                }

            }
        });
        add(button);
    }

    // Modifies: this
    // Effects: Initializes view frame and view button
    private void initializeViewButton() {
        viewFrame = new ViewFrame(games);
        JButton viewGameButton = new JButton("View Games");
        viewGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewFrame.setVisible(true);
                viewFrame.updateList();
            }
        });
        add(viewGameButton);
    }

    // Modifies: this
    // Effects: Initializes Total Price button.
    private void initializeTotalButton() {
        totalButton = new JButton("Compute Total Price");
        totalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (games.getSize() == 0) {
                    JOptionPane.showMessageDialog(null, "No Games!", "Total Price",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                double total = games.totalPrice();
                JOptionPane.showMessageDialog(null, "Total is: " + total, "Total Price",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        add(totalButton);
    }

    // Modifies: this
    // Effects: Initializes Average Price button
    private void initializeAvgButton() {
        avgButton = new JButton("Compute Average Price");
        avgButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (games.getSize() == 0) {
                    JOptionPane.showMessageDialog(null, "No Games!", "Average Price",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                double avg = games.avgPrice();
                JOptionPane.showMessageDialog(null, "Average is: " + avg, "Average Price",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        add(avgButton);
    }

    // Modifies: this
    // Effects: Initializes Load Button
    private void initializeLoadButton() {
        loadButton = new JButton("Load from Default");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                load(DEFAULT_DESTINATION);
            }
        });
        add(loadButton);
    }

    // Modifies: this
    // Effects: Loads game from a filie
    private void load(String source) {
        JsonReaderGameList reader = new JsonReaderGameList(source);
        try {
            games = reader.read();
            addGameFrame = new AddGameFrame(games);
            deleteGameFrame = new DeleteGameFrame(games);
            viewFrame = new ViewFrame(games);
            JOptionPane.showMessageDialog(null, "Loading Successful!",
                    "Loading Successful!",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Failed to load", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Modifies: this
    // Effects: Initializes Save Button
    private void initializeSaveButton() {
        saveButton = new JButton("Save to Default");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save(DEFAULT_DESTINATION);
            }
        });
        add(saveButton);
    }

    // Effects: Saves current list at selected destination
    private void save(String destination) {
        if (games.getSize() == 0) {
            JOptionPane.showMessageDialog(null, "No Games to save!", "Save",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            JsonWriterGameList writer = new JsonWriterGameList(destination);
            writer.write(games);
            writer.close();
            JOptionPane.showMessageDialog(null, "Save Successful!", "Save Successful!",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Failed to open destination", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    // Modifies: this
    // Effects: Initializes Delete Button and Delete Frame
    private void initializeDeleteButton() {
        deleteGameFrame = new DeleteGameFrame(games);
        JButton deleteGameButton = new JButton("Delete Game");
        deleteGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteGameFrame.setVisible(true);
                deleteGameFrame.refreshComboBox();
            }
        });
        add(deleteGameButton);
    }

    // Modifies: this
    // Effects: Initializes Add Button and Add Frame
    private void initializeAddButton() {
        addGameFrame = new AddGameFrame(games);
        JButton addGameButton = new JButton("Add Game");
        addGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addGameFrame.setVisible(true);
            }
        });
        add(addGameButton);
    }

    // Modifies: this
    // Effects: Initializes Main Frame
    private void initializeFrames() {
        this.setTitle("Game List Application");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(WIDTH, HEIGHT);
        this.addWindowListener(this);
        setLayout(new GridLayout(5, 2));
        setLocationRelativeTo(null);
    }

    private void printEvents() {
        for(Event event : EventLog.getInstance()){
            System.out.println(event.getDate() + " : " + event.getDescription());
        }
    }


    public static void main(String[] args) {
        new GameListUI();
    }

    // Unused
    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        printEvents();
    }

    // Unused
    @Override
    public void windowClosed(WindowEvent e) {

    }

    // Unused
    @Override
    public void windowIconified(WindowEvent e) {

    }

    // Unused
    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    // Unused
    @Override
    public void windowActivated(WindowEvent e) {

    }

    // Unused
    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
