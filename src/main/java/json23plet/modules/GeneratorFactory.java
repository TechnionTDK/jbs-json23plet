package json23plet.modules;

import json23plet.generators.Generator;
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
import java.util.HashMap;
import java.util.Map;


/**
 * Created by yon_b on 29/11/16.
 */
public class GeneratorFactory {
//    static String generatorsConfigFIle;
//    static String inputRootDir;
//    static String outputRootDir;

    static private Map<String, String[]> getActiveGeneratorsMap()
            throws FileNotFoundException {
        String generatorsConfigFIle = Paths.get
                ("src", "main", "java", "json23plet", "generators", "config.json").toString();
        JsonParser jp = new JsonParser();
        JsonElement root;
        Map<String, String[]> res = new HashMap<>();
        root = jp.parse(new FileReader(generatorsConfigFIle));
        for (JsonElement e :  root.getAsJsonObject().getAsJsonArray("generators")) {
            JsonObject obj = e.getAsJsonObject();
            if(obj.get("active").getAsString().equals("true")) {
                String[] params = {obj.get("input").getAsString(), obj.get("output").getAsString()};
                res.put(obj.get("name").getAsString(), params);
            }
        }
        return res;
    }

    static private void activateGenerator(String genName, String jsonInput, String outputDir)
            throws Exception {
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
        Map<String, String[]> generatorsMap = getActiveGeneratorsMap();
        for (String gen: generatorsMap.keySet()) {
            String inputPath = generatorsMap.get(gen)[0];
            if(new File(inputPath).isFile()) {
                activateGenerator(gen, new File(inputPath).getPath(), generatorsMap.get(gen)[1]);
                continue;
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
//    static public void Init(String configFile) {
////        generatorsConfigFIle = configFile;
////        inputRootDir = inputDir;
////        outputRootDir = outputDir;
//    }
//    static private String getOutPath(String path) {
//        String res = "";
//        String[] aPath = path.split("/");
//        for (int i = 1 ; i < aPath.length - 1 ; i++) {
//            res = Paths.get(res,aPath[i]).toString();
//        }
//        res = Paths.get(res, FilenameUtils.removeExtension(new File(path).getName()) + ".ttl").toString();
//        createPath(res);
//        return res;
//    }
//    static private void createPath(String path) {
//        String res = "";
//        String[] aPath = path.split("/");
//        for (int i = 0 ; i < aPath.length - 1 ; i++) {
//            res = Paths.get(res,aPath[i]).toString();
//            if (!Files.exists(Paths.get(res))) {
//                new File(res).mkdir();
//            }
//        }
//    }
}
