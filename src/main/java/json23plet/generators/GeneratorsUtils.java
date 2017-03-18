package json23plet.generators;

import com.google.gson.*;
import json23plet.modules.Json;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.exit;
import static json23plet.modules.Json.PRIMITIVE_KEY;
import static json23plet.modules.Json.json;
import static json23plet.modules.Triplet.triplet;

/**
 * Created by yon_b on 22/12/16.
 */
public class GeneratorsUtils {
    public static String jsonConfigPath = Paths.get("config.json").toString();
    public static String GENERATOR_PROP = "generators";
    public static String GENERATOR_INPUT_PROP = "input";
    public static String GENERATOR_ACTIVE_PROP = "active";
    public static String GENERATOR_NAME_PROP = "name";
    public static String GLOBAL_SETTING_GEN_OUTPUTDIR = "genOutputDir";
    public static String GLOBAL_SETTING = "globalSetting";
    public static String GLOBAL_SETTING_ERROR_LEVEL = "errorLevel";
    public static String GLOBAL_SETTING_GEN_INPUT_DIR = "genInputDir";
    public static String URI = "uri";
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


    static public void setNewGeneratorConfiguration(String genName, String input, String active) throws IOException {
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

    static public void setGenConfig(String genName, String inputPath, String active) throws IOException {
        for (JsonElement e : root.getAsJsonObject().get(SETTING_PROP).getAsJsonObject().getAsJsonArray(GENERATOR_PROP)) {
            if (e.getAsJsonObject().get(GENERATOR_NAME_PROP).getAsString().equals(genName)) {
                if (!inputPath.equals("")) {
                    setNewValToJsonElement(e,GENERATOR_INPUT_PROP, inputPath);
                }
                if (!active.equals("")) {
                    setNewValToJsonElement(e,GENERATOR_ACTIVE_PROP, active);
                }
                dispose();
                return;
            }
        }
        setNewGeneratorConfiguration(genName, inputPath, active);
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

    static public void setGlobalSettingProp(String propName, String newVal) throws IOException {
        setNewValToJsonElement(root
                                .getAsJsonObject()
                                .get(SETTING_PROP)
                                .getAsJsonObject()
                                .get(GLOBAL_SETTING),
                                    propName, newVal);
    }

    static private void setNewValToJsonElement(JsonElement e, String propName, String newVal) throws IOException {
        e.getAsJsonObject().remove(propName);
        e.getAsJsonObject().addProperty(propName, newVal);
        dispose();
    }

    static void dispose() throws IOException {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonFile = gson.toJson(root);
            FileWriter file = new FileWriter(jsonConfigPath);
            file.write(jsonFile);
            file.close();
    }
    static public void generateBasicJsonForm(String propOfJsonString) {
        for (Json j : json().getAsArray(propOfJsonString)) {
            try {
                Map<String, Json> mapJson = j.getAsDictionary();
                String uri = mapJson.get("uri").value(PRIMITIVE_KEY);
                for (String key : mapJson.keySet()) {
                    try {
                        if (key.equals("uri")) continue;
                        if (mapJson.get(key).isArray()) {
                            for (String s : mapJson.get(key).getAsStringArray(PRIMITIVE_KEY)) {
                                triplet()
                                        .subject(uri)
                                        .predicate(key)
                                        .object(s);
                            }
                        } else {
                            triplet()
                                    .subject(uri)
                                    .predicate(key)
                                    .object(mapJson.get(key).value(PRIMITIVE_KEY));
                        }
                    } catch (Exception e) {
                        onError("ERROR while activating basic generator, the json is:\n" + j.toString());

                    }
                }
            }  catch (Exception e) {
                System.err.println("ERROR while activating basic generator, the json is:\n" + j.toString());
                throw e;
            }
        }
    }
    static void onError(String errorMassage) {
        String errorLevel = getGlobalSettingProp(GLOBAL_SETTING_ERROR_LEVEL);
                        switch (errorLevel) {
                            case "low":
                                break;
                            case "medium":
                                System.err.println(errorMassage);
                                break;
                            case "high":
                                System.err.println(errorMassage);
                                System.exit(1);
                            default:
                        }
    }
}
