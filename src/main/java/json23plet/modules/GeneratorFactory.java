package json23plet.modules;

import json23plet.generators.GeneratorsUtils;
import org.apache.commons.io.FilenameUtils;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;


/**
 * Created by yon_b on 29/11/16.
 */
public class GeneratorFactory {

    static public void generate(String gen, String jsonRoot, String outputDirRoot) throws IOException {
        System.out.println("Activating " + gen + " on " + jsonRoot + " directory...");
        int totalWork = (int) Files.find(Paths.get(jsonRoot), 999, (p, bfa) -> bfa.isRegularFile()).count();
        ProgressBar pb = new ProgressBar(totalWork);
        Files.find(Paths.get(jsonRoot), 999, (p, bfa) -> bfa.isRegularFile()).forEach(file -> {
            if (FilenameUtils.getExtension(file.toString()).equals("json")) {
                try {
                    DataPublisher.Init(file.toString(), jsonRoot, outputDirRoot);
                    if (isClassExist("json23plet.generators.customGenerators." + gen)) {
                        activateGeneratorSingleFile(gen, file.toString());
                    } else if (isClassExist("json23plet.generators.regexGenerators." + gen)) {
                        activateSingleRegExGeneratorSingleFile(gen, file.toString());
                    } else {
                        throw new ClassNotFoundException();

                    }
                } catch (Exception e) {
                    System.out.println("\nerror while generating file: " + file.toString());
                    System.out.println("please check your json file or run /json23plet -config errorLevel=medium\n" +
                                        "and then ./json23plet -generate <generatorName> <" + file.toString() + ">");
                    e.printStackTrace();
                }
            }
                pb.update();

        });

    }

    static public void activateAllConfigGenerators(String outputRoot) throws Exception {
        Map<String, String> generatorsMap = GeneratorsUtils.getActiveGeneratorsMap();
        for (String gen: generatorsMap.keySet()) {
            String inputPath = generatorsMap.get(gen);
            generate(gen, new File(inputPath).getPath(), outputRoot);
        }
    }

    static private void activateGeneratorSingleFile(String genName, String jsonInput)
            throws Exception {
        Triplet.Init();
        Json.Init(jsonInput);
        Class genClass = Class.forName("json23plet.generators.customGenerators." + genName);
        Constructor ctor = genClass.getConstructor();
        Method generate = genClass.getDeclaredMethod("generate", null);
        Object instance = ctor.newInstance(null);
        generate.invoke(instance);
        Triplet.Close();
    }

    static private void activateSingleRegExGeneratorSingleFile(String reGenName, String input) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, IOException {
        Triplet.Init();
        Json.Init(input);
        Class reClass = Class.forName("json23plet.generators.regexGenerators." + reGenName);
        Constructor ctor = reClass.getConstructor();
        Method registerGenerators = reClass.getDeclaredMethod("registerGenerators", null);
        Method generate = reClass.getDeclaredMethod("generate", null);
        Object instance = ctor.newInstance(null);
        registerGenerators.invoke(instance);
        generate.invoke(instance);
        Triplet.Close();
    }

    static private boolean isClassExist(String className) {
        Class c;
        try {
            c  = Class.forName(className);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
