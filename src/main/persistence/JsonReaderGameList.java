package persistence;

import model.Game;
import model.GameList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Part of the implementation of this class was taken from
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a reader that reads workroom from JSON data stored in file
public class JsonReaderGameList {
    private String source;

    // The construction was taken from the Demo
    // EFFECTS: constructs reader to read from source file
    public JsonReaderGameList(String source) {
        this.source = source;
    }

    // This method's backbone is taken from the Demo
    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public GameList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGameList(jsonObject);
    }

    // This method was taken from the Demo
    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // The demo was used as a template for parsing the JSON.
    // Effects: returns the parsed gamelist from the Json.
    private GameList parseGameList(JSONObject jsonObject) {
        GameList games = new GameList();
        addGames(games, jsonObject);
        return games;
    }

    // The demo was used as a template to be able to add my games.
    // Modifies: games
    // Effects: parses games from the JSON object and adds them to games
    private void addGames(GameList games, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("games");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addGame(games, nextThingy);
        }
    }

    // The demo was used as a template to be able to add one single game.
    // Modifies: games
    // Effects: parses one single from the JSON object and adds them to games
    private void addGame(GameList games, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double price = jsonObject.getDouble("price");
        String genre = jsonObject.getString("genre");
        int score = jsonObject.getInt("score");
        Game game = new Game(name, price, genre, score);
        games.addGame(game);
    }
}
