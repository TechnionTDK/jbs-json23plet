package json23plet.generators;

import com.google.gson.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yon_b on 22/12/16.
 */
public class GeneratorsUtils {
    public static String jsonConfigPath = Paths.get("src", "main", "java", "json23plet", "generators", "config.json").toString();
    public static String GENERATOR_PROP = "generators";
    public static String GENERATOR_INPUT_PROP = "input";
    public static String GENERATOR_ACTIVE_PROP = "active";
    public static String GENERATOR_NAME_PROP = "name";
    public static String GLOBAL_SETTING_OUTPUTDIR = "outputDir";
    public static String GLOBAL_SETTING = "globalSetting";
    static String SETTING_PROP = "setting";
    static JsonElement root;
    static {
        JsonParser jp = new JsonParser();
        try {
            root = jp.parse(new FileReader(jsonConfigPath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static public Map<String, String> getActiveGeneratorsMap() {
        Map<String, String> res = new HashMap<>();
        for (JsonElement e :  root.getAsJsonObject().get(SETTING_PROP).getAsJsonObject().getAsJsonArray(GENERATOR_PROP)) {
            JsonObject obj = e.getAsJsonObject();
            if(obj.get(GENERATOR_ACTIVE_PROP).getAsString().equals("true")) {
                String params = obj.get(GENERATOR_INPUT_PROP).getAsString();
                res.put(obj.get(GENERATOR_NAME_PROP).getAsString(), params);
            }
        }
        return res;
    }

    static public void setNewGeneratorConfiguration(String genName, String input, String active) {
        JsonElement newGen = new JsonObject();
        newGen.getAsJsonObject()
                .addProperty(GENERATOR_NAME_PROP, genName);
        newGen.getAsJsonObject()
                .addProperty(GENERATOR_INPUT_PROP, input);
        newGen.getAsJsonObject()
                .addProperty(GENERATOR_ACTIVE_PROP, active);
        root.getAsJsonObject().get(SETTING_PROP).getAsJsonObject().getAsJsonArray(GENERATOR_PROP).add(newGen);
        dispose();
    }

    static public void setGenConfig(String genName, String propName, String newVal) {
        for (JsonElement e : root.getAsJsonObject().get(SETTING_PROP).getAsJsonObject().getAsJsonArray(GENERATOR_PROP)) {
            if (e.getAsJsonObject().get(GENERATOR_NAME_PROP).getAsString().equals(genName)) {
                setNewValToJsonElement(e, propName, newVal);
                dispose();
                return;
            }
        }
    }

    static public String getGlobalSettingProp(String propName) {
        return root.getAsJsonObject()
                .get(SETTING_PROP)
                .getAsJsonObject()
                .get(GLOBAL_SETTING)
                .getAsJsonObject()
                .get(propName)
                .getAsString();
    }

    static public void setGlobalSettingProp(String propName, String newVal) {
        setNewValToJsonElement(root
                                .getAsJsonObject()
                                .get(SETTING_PROP)
                                .getAsJsonObject()
                                .get(GLOBAL_SETTING),
                                    propName, newVal);
    }

    static private void setNewValToJsonElement(JsonElement e, String propName, String newVal) {
        e.getAsJsonObject().remove(propName);
        e.getAsJsonObject().addProperty(propName, newVal);
        dispose();
    }

    static void dispose() {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonFile = gson.toJson(root);
            FileWriter file = new FileWriter(jsonConfigPath);
            file.write(jsonFile);
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
