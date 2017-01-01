package json23plet.modules.ontologyGenerator;


import json23plet.modules.Json;
import json23plet.modules.Triplet;

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
    static public void generate(String ontName) {
        Triplet.Init();
        Json.Init(Paths.get("ontologies", "json", ontName + ".json").toString());

        for (Json j : json().getAsArray("prefixes")) {
            addNSprefix(j.value("prefix"), j.value("uri"));
        }
        for (Json j : json().getAsArray("metadata")) {
            Map<String, Json> mapJson = j.getAsDictionary();
            String uri = mapJson.get("uri").value(PRIMITIVE_KEY);
            for ( String key : mapJson.keySet() ) {
                if (key.equals("uri")) continue;
                if (mapJson.get(key).isArray()) {
                    for (String s : mapJson.get(key).getAsStringArray(PRIMITIVE_KEY)) {
                        triplet()
                                .subject(uri)
                                .predicate(key)
                                .object(s);
                    }
                } else {
                    triplet()
                            .subject(uri)
                            .predicate(key)
                            .object(mapJson.get(key).value("obj"));
                }
            }

        }
        Triplet.Export(Paths.get("ontologies", "ttl", ontName + ".ttl").toString(), "TURTLE");
    }
}
