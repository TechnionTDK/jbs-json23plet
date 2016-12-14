package json23plet.generators;


import json23plet.modules.Json;

import static json23plet.modules.Json.json;
import static json23plet.modules.Triplet.triplet;
import static json23plet.ontologies.jbsOntology.*;

/**
 * Created by yon_b on 06/12/16.
 */
public class SeferHamitzvotGenerator extends JbsBaseGenerator {
    @Override
    public void generate() {
        try {
            super.generate();
            for (Json j : json().getAsArray("subjects")) {
                String sub = JBR_PREFIX + ":" + j.value("uri");
                triplet()
                        .subject(sub)
                        .predicate(RDF_P_TYPE)
                        .object(JBO_C_MITZVA);

                triplet()
                        .object(sub)
                        .predicate(RDF_P_TYPE)
                        .object(JBO_PREFIX + ":" + j.value("mitzvaType"));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
