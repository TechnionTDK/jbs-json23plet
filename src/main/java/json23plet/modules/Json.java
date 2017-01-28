package json23plet.modules;


import com.google.gson.*;

import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by yon_b on 28/11/16.
 */
public class Json {
    static private ThreadLocal<JsonElement> root = new ThreadLocal<JsonElement>();

    static public String PRIMITIVE_KEY = "obj";

    protected JsonObject currentElement;

    private Json(JsonObject obj) {

        currentElement = obj;
    }
    private Json(String primitive) {
        currentElement =  new JsonParser().parse("{\"" + PRIMITIVE_KEY +"\":" + primitive + "}").getAsJsonObject();
    }
    private Json() {

        currentElement = root.get().getAsJsonObject();
    }

    /**
     * Return new Json object.
     * @return New Json object.
     */
    static public Json json() {

        return new Json();
    }

    /**
     * Get the value of a json key as list of Json objects.
     * @param member: the json key name.
     * @return the list pointed by the key as list of Json objects.
     */
    public List<Json> getAsArray(String member) {
        List<Json> res = new ArrayList<Json>();
        for (JsonElement e : currentElement.getAsJsonArray(member)) {
            res.add(new Json(e.getAsJsonObject()));
        }
        return res;
    }

    /**
     * Get the value of a json key as Json object.
     * @param member: the json key name.
     * @return the object pointed by the key as Json object.
     */
    public Json getAsObject(String member) {
        currentElement = currentElement.getAsJsonObject(member);
        return this;
    }

    /**
     * Get the current Json object.
     * @return Json object representing the current json.
     */
    public Json getAsObject() {
        currentElement = currentElement.getAsJsonObject();
        return this;
    }

    /**
     * Get the primitive value of a json key as String.
     * @param member: the json key name.
     * @return primitive String pointed by the key.
     */
    public String value(String member) {

        return currentElement.get(member).getAsString();
    }

    /**
     * Check if a Json has some key.
     * @param member: the key to check if exist.
     * @return true if exist false if not.
     */
    public boolean has(String member) {
        return currentElement.has(member);
    }

    static public void Init(String path) {
        JsonParser jp = new JsonParser();
        try {
            root.set(jp.parse(new FileReader(path)));
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

    /**
     * Get all the json keys as List of Strings.
     * @return List of Strings represents the json keys
     */
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

    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(currentElement);
    }


}
