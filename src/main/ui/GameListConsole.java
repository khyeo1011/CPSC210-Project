package ui;

import exceptions.InvalidScoreException;
import exceptions.NegativePriceException;
import model.Game;
import model.GameList;
import persistence.JsonReaderGameList;
import persistence.JsonWriterGameList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Gamelist Application
public class GameListConsole {
    private static String DESTINATION = "./data/gamelist.json";
    private GameList games;
    private Scanner input;

    // EFFECTS: Creates a Game List application
    public GameListConsole() throws IOException {
        JsonReaderGameList reader = new JsonReaderGameList(DESTINATION);
        games = reader.read();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        runApp();
    }


    // Note: I used the Teller App as an example for my UI.
    // MODIFIES: this
    // EFFECTS: processes user input
    private void runApp() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        try {
            JsonWriterGameList writer = new JsonWriterGameList(DESTINATION);
            writer.write(games);
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("File was not found...");
        } finally {
            System.out.println("Exiting App!");
        }
    }

    // EFFECTS: Displays the actions available for the user.
    private void displayMenu() {
        System.out.println("Select From: ");
        System.out.println("\ta -> add");
        System.out.println("\tv -> view");
        System.out.println("\tc -> actions");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void processCommand(String command) {
        if (command.equals("a")) {
            addGame();
        } else if (command.equals("v")) {
            viewGames();
        } else if (command.equals("c")) {
            doAction();
        } else {
            System.out.println("Invalid Command!");
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a game to the list
    private void addGame() {
        String name = null;
        double price = 0;
        String genre = null;
        int score = 0;
        System.out.print("Write the name of the game: ");
        name = input.next();
        System.out.print("Write the price of the game: $");
        price = input.nextDouble();
        while (price < 0) {
            System.out.println("Invalid Price!");
            System.out.print("Write the price of the game: $");
            price = input.nextDouble();
        }
        System.out.print("Write the genre of the game: ");
        genre = input.next();
        System.out.print("Write the score of the game (-1 for unplayed): ");
        score = input.nextInt();
        addGame(name, price, genre, score);

    }

    // MODIFIES: this
    // EFFECTS: adds a game to the list.
    private void addGame(String name, double price, String genre, int score) {
        Game game = null;
        if (score == -1) {
            try {
                game = new Game(name, price, genre, -1);
            } catch (NegativePriceException e) {
                throw new RuntimeException(e);
            } catch (InvalidScoreException e) {
                throw new RuntimeException(e);
            }
        } else {
            while (!((score >= 0 && score <= 10) || score == -1)) {
                try {
                    game = new Game(name, price, genre, score);
                } catch (NegativePriceException e) {
                    throw new RuntimeException(e);
                } catch (InvalidScoreException e) {
                    System.out.println("Invalid Input!");
                    System.out.print("Write the score of the game (-1 for unplayed): ");
                }
                score = input.nextInt();
            }
        }
        checkDuplicate(game);

    }

    // Effects: Checks for duplicate name games
    private void checkDuplicate(Game game) {
        boolean success = games.addGame(game);
        if (!success) {
            System.out.println("Game with same name present! Please try again:");
            return;
        }
        System.out.println("Game with description: ");
        System.out.println(game);
        System.out.println("Has been added successfully!");
    }


    // EFFECTS: Prints out all the games;
    private void viewGames() {
        for (int i = 0; i < games.getSize(); i++) {
            System.out.println("Game #" + i + ":");
            System.out.println(games.getGame(i));
        }
    }

    // MODIFIES: this
    // EFFECTS: Does a desired action.
    private void doAction() {
        String action = null;
        System.out.println("What action would you like to do?");
        printActions();
        action = input.next();
        processAction(action);
    }

    // EFFECTS: prints the actions available
    private void printActions() {
        System.out.println("Select From:");
        System.out.println("\tc -> change game information");
        System.out.println("\ta -> view average price");
        System.out.println("\tt -> view total price");
    }

    // EFFECTS: processes user input
    private void processAction(String action) {
        if (action.equals("c")) {
            changeGame();
        } else if (action.equals("a")) {
            System.out.println("Average Price: $" + games.avgPrice());
        } else if (action.equals("t")) {
            System.out.println("Total Price: $" + games.totalPrice());
        } else {
            System.out.println("Invalid Input!");
        }
    }

    // MODIFIES: this
    // EFFECTS: changes game's information
    private void changeGame() {
        int index = 0;
        viewGames();
        System.out.print("Please select game #: ");
        index = input.nextInt();
        while (!(index >= 0 && index < games.getSize())) {
            System.out.println("Invalid Index!");
            System.out.print("Please select index: ");
            index = input.nextInt();
        }

    }

    private void tryChanging(int index) {
        boolean keepGoing = true;
        while (keepGoing) {
            String command = null;
            printFields();
            command = input.next();
            if (command.equals("q")) {
                keepGoing = false;
            } else {
                try {
                    processFields(command, index);
                } catch (NegativePriceException e) {
                    System.out.println("Invalid Price!");
                } catch (InvalidScoreException e) {
                    System.out.println("Invalid Score!");
                }
            }
        }
    }

    // EFFECTS: Prints the fields that can change
    private void printFields() {
        System.out.println("Select from:");
        System.out.println("\tn -> name");
        System.out.println("\tp -> price");
        System.out.println("\tg -> genre");
        System.out.println("\ts -> score");
        System.out.println("\tq -> return to menu");
    }

    // MODIFIES: this
    // EFFECTS: processes user input and changes game info.
    private void processFields(String command, int index) throws NegativePriceException, InvalidScoreException {
        if (command.equals("n")) {
            System.out.print("Input the new name:");
            games.getGame(index).setName(input.next());
        } else if (command.equals("p")) {
            System.out.print("Input the new price: $");
            double price = input.nextDouble();
            games.getGame(index).setPrice(price);
        } else if (command.equals("g")) {
            System.out.print("Input the new genre:");
            games.getGame(index).setGenre(input.next());
        } else if (command.equals("s")) {
            System.out.print("Input the new score (-1 for unplayed): ");
            int score = input.nextInt();
            if (!(score == -1 || (score >= 0 && score <= 10))) {
                System.out.println("Invalid Score!");
                return;
            }
            games.getGame(index).setScore(score);
        } else {
            System.out.println("Invalid input!");
        }
        System.out.println("Game change to:");
        System.out.println(games.getGame(index));
    }
}
