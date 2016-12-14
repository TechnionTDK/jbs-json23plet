package json23plet.modules;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.FilenameUtils;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by yon_b on 29/11/16.
 */
public class GeneratorFactory {
    static String outputRootDir;

    static private Map<String, String> getActiveGeneratorsMap()
            throws FileNotFoundException {
        String generatorsConfigFIle = Paths.get
                ("src", "main", "java", "json23plet", "generators", "config.json").toString();
        JsonParser jp = new JsonParser();
        JsonElement root;
        Map<String, String> res = new HashMap<>();
        root = jp.parse(new FileReader(generatorsConfigFIle));
        for (JsonElement e :  root.getAsJsonObject().getAsJsonArray("generators")) {
            JsonObject obj = e.getAsJsonObject();
            if(obj.get("active").getAsString().equals("true")) {
                String params = obj.get("input").getAsString();
                res.put(obj.get("name").getAsString(), params);
            }
        }
        return res;
    }

    static private void activateGenerator(String genName, String jsonInput, String outputDirForSingleFile)
            throws Exception {
        String outputDir = Paths.get(outputRootDir, outputDirForSingleFile).toString();
        Triplet.Init();
        Json.Init(jsonInput);
        Class genClass = Class.forName("json23plet.generators." + genName);
        Constructor ctor = genClass.getConstructor();
        Method generate = genClass.getDeclaredMethod("generate", null);
        Object instance = ctor.newInstance(null);
        generate.invoke(instance);
        String outputPath = Paths.get(outputDir,
                Paths.get(jsonInput).getFileName().toString().replace(".json", ".ttl")).toString();
        new File(outputDir).mkdirs();
        Triplet.Export(outputPath, "TURTLE");
        Triplet.Close();
    }

    static public void activateAllGenerators() throws Exception {
        Map<String, String> generatorsMap = getActiveGeneratorsMap();
        for (String gen: generatorsMap.keySet()) {
            String inputPath = generatorsMap.get(gen);
            if(new File(inputPath).isFile()) {
                activateGenerator(gen, new File(inputPath).getPath(), "");
                continue;
            }
            Files.find(Paths.get(inputPath), 999, (p, bfa) -> bfa.isRegularFile()).forEach(file -> {
                try {
                    activateGenerator(gen, file.toString(), "");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            });

        }
    }
    public static void Init(String rootDir) throws IOException {
        outputRootDir = rootDir;
        Files.find(Paths.get("src", "main", "java", "json23plet", "ontologies"), 999, (p, bfa) -> bfa.isRegularFile()).forEach(file -> {
            try {
                Class genClass = Class.forName("json23plet.ontologies." + file.getFileName().toString().replace(".java",""));
                Constructor ctor = genClass.getConstructor();
                Object instance = ctor.newInstance(null);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }
}
