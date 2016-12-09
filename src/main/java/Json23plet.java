import json23plet.modules.GeneratorFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by yon_b on 28/11/16.
 */
public class Json23plet {
    public static void main(String argv[]) {
        initJson23plet();
        GeneratorFactory.Init("src/main/java/generators", "input", "output");
        try {
            GeneratorFactory.activateAllGenerators();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    private static void initJson23plet() {
        String[] files = {"output", "ontologies", "input", "src/main/java/generators"};
        for (String file : files) {
            if (!Files.exists(Paths.get(file))) {
                new File(file).mkdir();
            }
        }
    }
}
