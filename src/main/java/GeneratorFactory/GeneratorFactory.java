package GeneratorFactory;

import Generators.KnownOntologies;
import JSONUtils.JsonAPI;
import TripletUtils.Triplet;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.FilenameUtils;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by yon_b on 29/11/16.
 */
public class GeneratorFactory {
    static String generatorsConfigFIle;
    static String inputRootDir;
    static String outputRootDir;

    static private Map<String, String[]> getActiveGeneratorsMap()
            throws FileNotFoundException {
        JsonParser jp = new JsonParser();
        JsonElement root;
        Map<String, String[]> res = new HashMap<>();
        root = jp.parse(new FileReader(Paths.get(generatorsConfigFIle, "config.json").toString()));
        for (JsonElement e :  root.getAsJsonObject().getAsJsonArray("generators")) {
            JsonObject obj = e.getAsJsonObject();
            if(obj.get("active").getAsString().equals("true")) {
                String[] params = {obj.get("input").getAsString(), obj.get("ontology").getAsString()};
                res.put(obj.get("name").getAsString(), params);
            }
        }
        return res;
    }

    static private void activateGenerator(String genName, String jsonInput, String ont)
            throws Exception {
        Triplet.Init();
        JsonAPI.Init(jsonInput);
        KnownOntologies.Init(ont);
        Class genClass = Class.forName("Generators." + genName);
        if (!Arrays.asList(genClass.getInterfaces()).contains(Class.forName("Generators.IGenerator"))) {
            throw new Exception("Generator need to implement IGenerator");
        }
        Constructor ctor = genClass.getConstructor();
        Method generate = genClass.getDeclaredMethod("generate", null);
        Object instance = ctor.newInstance(null);
        generate.invoke(instance);
        Triplet.Export(getOutPath(jsonInput), "TURTLE");
        Triplet.Close();
    }

    static public void activateAllGenerators() throws Exception {
        Map<String, String[]> generatorsMap = getActiveGeneratorsMap();
        for (String gen: generatorsMap.keySet()) {
            String inputPath = Paths.get(inputRootDir, generatorsMap.get(gen)[0]).toString();
            if(new File(inputPath).isFile()) {
                activateGenerator(gen, new File(inputPath).getPath(), generatorsMap.get(gen)[1]);
                return;
            }
            Files.find(Paths.get(inputPath), 999, (p, bfa) -> bfa.isRegularFile()).forEach(file -> {
                try {
                    activateGenerator(gen, file.toString(), generatorsMap.get(gen)[1]);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            });

        }
    }
    static public void Init(String configFile, String inputDir, String outputDir) {
        generatorsConfigFIle = configFile;
        inputRootDir = inputDir;
        outputRootDir = outputDir;
    }
    static private String getOutPath(String path) {
        String res = outputRootDir;
        String[] aPath = path.split("/");
        for (int i = 1 ; i < aPath.length - 1 ; i++) {
            res = Paths.get(res,aPath[i]).toString();
        }
        res = Paths.get(res, FilenameUtils.removeExtension(new File(path).getName()) + ".ttl").toString();
        createPath(res);
        return res;
    }
    static private void createPath(String path) {
        String res = "";
        String[] aPath = path.split("/");
        for (int i = 0 ; i < aPath.length - 1 ; i++) {
            res = Paths.get(res,aPath[i]).toString();
            if (!Files.exists(Paths.get(res))) {
                new File(res).mkdir();
            }
        }
    }
}
