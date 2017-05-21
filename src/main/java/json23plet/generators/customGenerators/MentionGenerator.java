package json23plet.generators.customGenerators;

import json23plet.JsonValidators.JsonValidator;
import json23plet.JsonValidators.PsukimTagsValidator;
import json23plet.modules.DataPublisher;
import json23plet.modules.Json;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static json23plet.generators.GeneratorsUtils.URI;
import static json23plet.modules.Json.json;
import static json23plet.modules.Triplet.triplet;
import static json23plet.ontologies.JbsOntology.*;

/**
 * Created by omishali
 */
public class MentionGenerator extends Generator {
    @Override
    public void generate() throws IOException {
        JsonValidator v = new PsukimTagsValidator();
        v.registerValidators();
        v.validateSingleJson(Json.json());

        for (Json j : json().getAsArray("subjects")) {
            Map<String, Integer>  numOfMEntions = new HashMap<>();
            String subjectUri = j.value(URI);
            String subjectId = subjectUri.split(":")[1];

            for (Json tag : j.getAsArray("tags")) {
                String tagUri = tag.value(URI);
                String tagId = tagUri.split(":")[1];
                String mentionUri = "jbr:mention__" + subjectId + "__" + tagId;
                updateNumOfMentions(numOfMEntions, mentionUri);
                triplet()
                        .subject(mentionUri)
                        .predicate(JBO_P_SOURCE)
                        .object(subjectUri);
                triplet()
                        .subject(mentionUri)
                        .predicate(JBO_P_TARGET)
                        .object(tagUri);
                triplet()
                        .subject(mentionUri)
                        .predicate(JBO_P_SPAN)
                        .object(tag.value("span"));
                triplet()
                        .subject(mentionUri)
                        .predicate(RDF_P_TYPE)
                        .object(JBO_C_MENTION);
            }

            for (String mentionUri : numOfMEntions.keySet())
                triplet()
                        .subject(mentionUri)
                        .predicate(JBO_P_NUMOFMENTIONS)
                        .object(numOfMEntions.get(mentionUri).toString());
        }
        DataPublisher.publish("", "." + getID() + ".ttl", "TURTLE");
    }

    private void updateNumOfMentions(Map<String, Integer> numOfMEntions, String tagUri) {
        numOfMEntions.put(tagUri, numOfMEntions.getOrDefault(tagUri, 0) +  1);
    }

    @Override
    public String getID() {
        return "mention";
    }
}
