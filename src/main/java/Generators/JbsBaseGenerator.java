package Generators;

import JSONUtils.JsonAPI;
import TripletUtils.Triplet;

/**
 * Created by yon_b on 06/12/16.
 */
public class JbsBaseGenerator extends JbsOntology {
    protected JbsBaseGenerator() {
        String jbrPrefix = "jbr:";
        for (JsonAPI j : JsonAPI.json().getAsArray("subjects")) {
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
            for (JsonAPI json : j.getAsArray("titles")) {
                Triplet
                        .triplet()
                        .subject(sub)
                        .predicate(RDFS_P_LABEL)
                        .object(json.value("title"));
            }
        }

    }
}
