package JSONUtils;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yon_b on 28/11/16.
 */
public class JsonAPI {
    static private JsonElement root;

    private JsonObject currentElement;

    private JsonAPI(JsonObject obj) {

        currentElement = obj;
    }
    private JsonAPI() {

        currentElement = root.getAsJsonObject();
    }
    static public JsonAPI json() {

        return new JsonAPI();
    }
    public List<JsonAPI> getAsArray(String member) {
        List<JsonAPI> res = new ArrayList<JsonAPI>();
        for (JsonElement e : currentElement.getAsJsonArray(member)) {
            res.add(new JsonAPI(e.getAsJsonObject()));
        }
        return res;
    }

    public JsonAPI getAsObject(String member) {
        currentElement = currentElement.getAsJsonObject(member);
        return this;
    }

    public String value(String member) {

        return currentElement.get(member).getAsString();
    }

    public boolean has(String member) {
        return currentElement.has(member);
    }
    static public void Init(String path) {
        JsonParser jp = new JsonParser();
        try {
            root = jp.parse(new FileReader(path));
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
