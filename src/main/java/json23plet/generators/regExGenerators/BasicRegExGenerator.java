package json23plet.generators.regExGenerators;

import json23plet.modules.Json;
import json23plet.modules.Triplet;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yon_b on 02/01/17.
 */
public abstract class BasicRegExGenerator {

    List<IRegExGenerator> generatorsList = new ArrayList<>();
    public abstract void registerGenerators();

    public void registerGenerator(IRegExGenerator rgen) {
        generatorsList.add(rgen);
    }
    public abstract List<Json> getJsonsToGenerate();

    public abstract void activateRegExGenerator();

    private List<Object> getRegExGenerators() throws IOException {
        return Files.find(Paths.get("src", "main", "java", "json23plet", "generators", "regExGenerator"), 999, (p, bfa) -> bfa.isRegularFile())
                .map(file -> {
                    try {
                        Class reClass = Class.forName("json23plet.generators.regExGenerator." + file.getFileName().toString().replace(".java", ""));
                        Constructor ctor = reClass.getConstructor();
                        return (ctor.newInstance(null));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .collect(Collectors.toList());

    }

    protected void _activateRegExGenerator() {
        for (IRegExGenerator rgen : generatorsList) {
            Triplet.Init();
            for (Json js : getJsonsToGenerate()) {
                if (rgen.match(js.value("uri"))) {
                    rgen.generate(js);
                }
            }
            new File(new File(rgen.outputPath()).getParent()).mkdirs();
            Triplet.Export(rgen.outputPath(), "TURTLE");
            Triplet.Close();
        }
    }
}
