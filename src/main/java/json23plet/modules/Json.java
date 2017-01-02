package json23plet.modules;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by yon_b on 28/11/16.
 */
public class Json {
    static private JsonElement root;

    static public String PRIMITIVE_KEY = "obj";

    private JsonObject currentElement;

    private Json(JsonObject obj) {

        currentElement = obj;
    }
    private Json(String primitive) {
        currentElement =  new JsonParser().parse("{\"" + PRIMITIVE_KEY +"\":" + primitive + "}").getAsJsonObject();
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

    public Json getAsObject() {
        currentElement = currentElement.getAsJsonObject();
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

    public Map<String, Json> getAsDictionary() {
        Set<Map.Entry<String, JsonElement>> entries = currentElement
                .entrySet();
        Map<String, Json> res = new HashMap<>();
        for (Map.Entry<String, JsonElement> entry : entries)
        {
            res.put(entry.getKey(), new Json(entry.getValue().toString()));
        }
        return res;
    }

    public List<String> getAsStringArray(String member) {
        List<String> res = new ArrayList<>();
        for (JsonElement j : currentElement.get(member).getAsJsonArray()) {
            res.add(j.getAsString());
        }
        return res;
    }

    public List<String> getJsonKeys() {
        return currentElement
                .entrySet()
                .stream()
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());
    }

    public boolean isArray() {
        return currentElement.get(PRIMITIVE_KEY).isJsonArray();
    }

    public boolean isPrimitive() {
        return currentElement.isJsonPrimitive();
    }


}
