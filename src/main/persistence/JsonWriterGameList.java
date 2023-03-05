package persistence;

import model.GameList;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Many parts of this class are taken from the Demo or used one of its method as a template:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a writer that writes JSON representation of workroom to file
public class JsonWriterGameList {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file;throws FileNotFoundException
    // if destination file cannot be opened for writing
    public JsonWriterGameList(String destination) throws FileNotFoundException {
        this.destination = destination;
        writer = new PrintWriter(new File(destination));
    }

    // Most of this method was taken from the demo.
    // MODIFIES: this
    // EFFECTS: writes JSON representation of workroom to file
    public void write(GameList games) {
        JSONObject json = games.toJson();
        writer.print(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }
}
