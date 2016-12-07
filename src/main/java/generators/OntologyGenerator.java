package generators;


import json23plet.modules.Json;
import json23plet.modules.Triplet;

import static json23plet.modules.Json.json;
import static json23plet.modules.Triplet.triplet;
import static json23plet.modules.Triplet.addNSprefix;

/**
 * Created by yon_b on 03/12/16.
 */
public class OntologyGenerator extends Generator{
    @Override
    public void generate() {
        for (Json j : json().getAsArray("prefixes")) {
            addNSprefix(j.value("prefix"), j.value("uri"));
        }
        for (Json j : json().getAsArray("metadata")) {
            triplet()
                    .subject(j.value("subject"))
                    .predicate(j.value("predicate"))
                    .object(j.value("object"));
        }
    }
}
