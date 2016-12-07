package generators;


import json23plet.modules.Json;
import json23plet.modules.Triplet;

/**
 * Created by yon_b on 06/12/16.
 */
public class JbsBaseGenerator extends JbsOntology {
    protected JbsBaseGenerator() {
        String jbrPrefix = "jbr:";
        for (Json j : json23plet.modules.Json.json().getAsArray("subjects")) {
            String sub = jbrPrefix + j.value("uri");
            Triplet
                    .triplet()
                    .subject(sub)
                    .predicate(JBO_P_TEXT)
                    .object(j.value("text"));
            Triplet
                    .triplet()
                    .subject(sub)
                    .predicate(JBO_P_CONTAINER)
                    .object(j.value("sefer"));
            for (json23plet.modules.Json Json : j.getAsArray("titles")) {
                Triplet
                        .triplet()
                        .subject(sub)
                        .predicate(RDFS_P_LABEL)
                        .object(Json.value("title"));
            }
        }

    }
}
