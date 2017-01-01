package json23plet.generators;


import json23plet.modules.Json;
import json23plet.modules.Triplet;

import static json23plet.modules.Json.json;
import static json23plet.modules.Triplet.triplet;
import static json23plet.ontologies.JbsOntology.*;

/**
 * Created by yon_b on 06/12/16.
 */
public class JbsBaseGenerator {

//    public void generate() {
//        Triplet.addNSprefix(JBO_PREFIX, JBO_URI);
//        Triplet.addNSprefix(JBR_PREFIX, JBR_URI);
//        for (Json j : json().getAsArray("subjects")) {
//            String prefix = JBR_PREFIX + ":";
//            String sub = prefix + j.value("uri");
//            triplet()
//                    .subject(sub)
//                    .predicate(JBO_P_TEXT)
//                    .object(j.value("text"));
//            triplet()
//                    .subject(sub)
//                    .predicate(JBO_P_CONTAINER)
//                    .object(prefix + j.value("sefer"));
//            for (Json js : j.getAsArray("titles")) {
//                triplet()
//                        .subject(sub)
//                        .predicate(RDFS_P_LABEL)
//                        .object(js.value("title"));
//            }
//        }
//
//    }
}
