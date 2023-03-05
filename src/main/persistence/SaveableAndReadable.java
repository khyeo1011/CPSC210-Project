package persistence;

import org.json.JSONObject;

public interface SaveableAndReadable {

    // The example was used as a template for persistence.
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // Effects: Returns an JSON object
    JSONObject toJson();
}
