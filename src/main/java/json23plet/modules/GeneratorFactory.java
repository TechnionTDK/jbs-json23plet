package json23plet.modules;

import json23plet.generators.GeneratorsUtils;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;


/**
 * Created by yon_b on 29/11/16.
 */
public class GeneratorFactory {

    static public void activateGenerator(String gen, String jsonRoot, String outputDirRoot) throws Exception {
//        if(new File(jsonRoot).isFile()) {
//            activateGeneratorSingleFile(gen, new File(jsonRoot).getPath());
//            export(jsonRoot, jsonRoot, outputDirRoot, true);
//            return;
//        }
        Files.find(Paths.get(jsonRoot), 999, (p, bfa) -> bfa.isRegularFile()).forEach(file -> {
            try {
                activateGeneratorSingleFile(gen, file.toString());
                export(jsonRoot, file.toString(), outputDirRoot, false);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }

    static public void activateAllConfigGenerators(String outputRoot) throws Exception {
        Map<String, String> generatorsMap = GeneratorsUtils.getActiveGeneratorsMap();
        for (String gen: generatorsMap.keySet()) {
            String inputPath = generatorsMap.get(gen);
            activateGenerator(gen, new File(inputPath).getPath(), outputRoot);
        }
    }

    static private void activateGeneratorSingleFile(String genName, String jsonInput)
            throws Exception {
        Triplet.Init();
        Json.Init(jsonInput);
        Class genClass = Class.forName("json23plet.generators." + genName);
        Constructor ctor = genClass.getConstructor();
        Method generate = genClass.getDeclaredMethod("generate", null);
        Object instance = ctor.newInstance(null);
        generate.invoke(instance);
    }

    static private void export(String inputRoot, String inputFile, String outRoot, boolean isFIle) {
        String inputRootDir = new File(inputRoot).getParent();
        int beginConOut = inputFile.indexOf(inputRootDir) + inputRootDir.length();

        String outputPath = Paths.get(outRoot, inputFile.substring
                (beginConOut, inputFile.length()).replace(".json", ".ttl")).toString();
        new File(new File(outputPath).getParent()).mkdirs();
        Triplet.Export(outputPath, "TURTLE");
        Triplet.Close();

    }
    static public void Init() throws IOException {
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
