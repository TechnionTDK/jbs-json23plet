package json23plet.modules;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yon_b on 28/11/16.
 */
public class Json {
    static private JsonElement root;

    private JsonObject currentElement;

    private Json(JsonObject obj) {

        currentElement = obj;
    }
    private Json() {

        currentElement = root.getAsJsonObject();
    }
    static public Json json() {

        return new Json();
    }
    public List<Json> getAsArray(String member) {
        List<Json> res = new ArrayList<Json>();
        for (JsonElement e : currentElement.getAsJsonArray(member)) {
            res.add(new Json(e.getAsJsonObject()));
        }
        return res;
    }

    public Json getAsObject(String member) {
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
