package json23plet.generators.customGenerators;

import json23plet.modules.DataPublisher;
import json23plet.modules.Json;

import static json23plet.generators.GeneratorsUtils.URI;
import static json23plet.modules.Json.json;
import static json23plet.modules.Triplet.triplet;
import static json23plet.ontologies.JbsOntology.JBO_P_MENTIONS;

/**
 * Created by yon_b on 19/01/17.
 */
public class PsukimTagsGenerator extends Generator {
    @Override
    public void generate() {
        for (Json j : json().getAsArray("subjects")) {
            String subjectUri = j.value(URI);
            for (Json tag : j.getAsArray("tags")) {
                triplet()
                        .subject(subjectUri)
                        .predicate(JBO_P_MENTIONS)
                        .object(tag.value(URI));
            }
        }
        DataPublisher.publish("", ".ttl", "TURTLE");
    }
}
