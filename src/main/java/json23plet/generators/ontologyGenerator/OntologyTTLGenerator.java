package json23plet.generators.ontologyGenerator;


import json23plet.generators.GeneratorsUtils;
import json23plet.modules.Json;
import json23plet.modules.Triplet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import java.util.Map;

import static json23plet.modules.Json.json;
import static json23plet.modules.Json.PRIMITIVE_KEY;
import static json23plet.modules.Triplet.triplet;
import static json23plet.modules.Triplet.addNSprefix;

/**
 * Created by yon_b on 03/12/16.
 */
public class OntologyTTLGenerator {
    /**
     * Generate the ontology ttl file.
     * @param ontName the name of the ontology file.
     */
    static public void generate(String ontName) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        Triplet.Init();
        Json.Init(Paths.get("ontologies", "json", ontName + ".json").toString());

        for (Json j : json().getAsArray("prefixes")) {
            addNSprefix(j.value("prefix"), j.value("uri"));
        }

        GeneratorsUtils.generateBasicJsonForm("metadata");

        Triplet.Export(Paths.get("ontologies", "ttl", ontName + ".ttl").toString(), "TURTLE");
    }
}
