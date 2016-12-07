package generators;


import json23plet.modules.Json;
import json23plet.modules.Triplet;

/**
 * Created by yon_b on 03/12/16.
 */
public class OntologyGenerator implements IGenerator{
    @Override
    public void generate() {
        for (Json j : json23plet.modules.Json.json().getAsArray("prefixes")) {
            Triplet.addNSprefix(j.value("prefix"), j.value("uri"));
        }
        for (Json j : json23plet.modules.Json.json().getAsArray("metadata")) {
            Triplet
                    .triplet()
                    .subject(j.value("subject"))
                    .predicate(j.value("predicate"))
                    .object(j.value("object"));
        }
    }
}
