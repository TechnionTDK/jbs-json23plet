package json23plet.generators.regexGenerators;

import json23plet.modules.DataPublisher;
import json23plet.modules.Json;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yon_b on 02/01/17.
 */
public abstract class BaseRegexGenerator {

    List<IRegexGenerator> generatorsList = new ArrayList<>();

    /**
     * An abstract function, force the user to register generators of type
     * IRegexGeneraotrs to the current regexGenerator.
     */
    public abstract void registerGenerators();

    /**
     * Register new IRegexGenerator to the generatorsList.
     * @param rgen  the IRegexGenerator to register.
     */
    public void registerGenerator(IRegexGenerator rgen) {
        generatorsList.add(rgen);
    }

    /**
     * Define the way we get the json's to work on from your json file (using the Json module).
     * @return A list of Json  contains the json's to eotk on.
     */

    public abstract List<Json> getJsonsToGenerate();

    /**
     * Define the id of your regexGenerator, this ID associated with the output file name.
     * @return The ID of this regexGenerator
     */
    public abstract String getID();

    /**
     * The json23plet engine will search after this function and call it. this is the main
     * function of any generator.
     */
    public abstract void generate();

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

    /**
     * Activate the list of IRegexGenerators on the Json's lists.
     * Any regexGenerator have to call this function inside its generate function.
     */
    void _generate() {
        for (IRegexGenerator rgen : generatorsList) {
            for (Json js : getJsonsToGenerate()) {
                if (rgen.match(js)) {
                    rgen.generate(js);

                }
            }
            DataPublisher.publish("", "." + getID() + ".ttl", "TURTLE");
        }
    }
}
