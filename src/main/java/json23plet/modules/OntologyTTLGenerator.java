package json23plet.modules;


import json23plet.modules.Json;
import json23plet.modules.Triplet;

import java.nio.file.Paths;

import static json23plet.modules.Json.json;
import static json23plet.modules.Triplet.triplet;
import static json23plet.modules.Triplet.addNSprefix;

/**
 * Created by yon_b on 03/12/16.
 */
public class OntologyTTLGenerator {
    static public void generate(String ontName) {
        Triplet.Init();
        Json.Init(Paths.get("ontologies", "json", ontName + ".json").toString());
        for (Json j : json().getAsArray("prefixes")) {
            addNSprefix(j.value("prefix"), j.value("uri"));
        }
        for (Json j : json().getAsArray("metadata")) {
            triplet()
                    .subject(j.value("subject"))
                    .predicate(j.value("predicate"))
                    .object(j.value("object"));
        }
        Triplet.Export(Paths.get("ontologies", "ttl", ontName + ".ttl").toString(), "TURTLE");
    }
}
