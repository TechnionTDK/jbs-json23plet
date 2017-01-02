package json23plet.generators.regExGenerators;

import json23plet.modules.Json;
import json23plet.modules.Triplet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yon_b on 02/01/17.
 */
public abstract class BasicRegExGenerator {
    List<IRegExGenerator> generatorsList = new ArrayList<>();
    public abstract void registerGenerators();

    public void registerGenerator(IRegExGenerator rgen) {
        generatorsList.add(rgen);
    }

    public void activateRegExGenerators(String regEx, String inputPath, String outputPath) throws IOException {
        Files.find(Paths.get(inputPath), 999, (p, bfa) -> bfa.isRegularFile()).forEach(file -> {
            activateSingleRegExGenerator(regEx, file.toString(), outputPath);
        });

    }
    private void activateSingleRegExGenerator(String regEx, String inputPath, String outputPath) {
        Json.Init(inputPath);
        Triplet.Init();
        for (IRegExGenerator rgen : generatorsList) {
            if (rgen.match(regEx)) {
                rgen.generate();
                Triplet.Export(outputPath, "TURTLE");
            }
        }

    }
}
