package json23plet.modules;


import com.google.gson.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by yon_b on 28/11/16.
 */
public class Json {
    static private ThreadLocal<JsonElement> root = new ThreadLocal<JsonElement>();

    /**
     * While generating a generic json some values might create as {"obj":value}
     * this property represent it.
     * see example at src/main/java/json23plet/generators/GeneratorsUtils.java
     */
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
     * @param member the json key name.
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
     * @param member the json key name.
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
     * @param member the json key name.
     * @return primitive String pointed by the key.
     */
    public String value(String member) {

        return currentElement.get(member).getAsString();
    }

    /**
     * Check if a Json has some key.
     * @param member the key to check if exist.
     * @return true if exist false if not.
     */
    public boolean has(String member) {
        return currentElement.has(member);
    }

    /**
     * Init the class to parse the current json file.
     * This function used by GeneratorFactory.
     * @param path path to the json file.
     */
    static public void Init(String path) throws FileNotFoundException {
        JsonParser jp = new JsonParser();
        root.set(jp.parse(new FileReader(path)));
    }

    /**
     * Get the current json as dictionary of (property, value) pairs.
     * @return dictionary represent the json.
     */
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

    /**
     * Get the values of strings list (e.g {key: [str1, str2]})
     * @param member the key name.
     * @return list of the strings (e.g [str1, str2])
     */
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

    /**
     * Check if the current value is array.
     * @return true if array, false if not.
     */
    public boolean isArray() {
        return currentElement.get(PRIMITIVE_KEY).isJsonArray();
    }

    /**
     * Check if the current value is a primitive.
     * @return true if primitive, false if not.
     */
    public boolean isPrimitive() {
        return currentElement.isJsonPrimitive();
    }

    /**
     * Get the Json as string.
     * @return Json as string.
     */
    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(currentElement);
    }


}
