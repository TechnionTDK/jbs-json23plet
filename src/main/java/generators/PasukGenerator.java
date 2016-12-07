package generators;


import json23plet.modules.Json;
import json23plet.modules.Triplet;

import static json23plet.modules.Json.json;
import static json23plet.modules.Triplet.triplet;

/**
 * Created by yon_b on 29/11/16.
 */
public class PasukGenerator extends JbsBaseGenerator {
    @Override
    public void generate() {
        try {
            super.generate();
            for (Json j : json().getAsArray("subjects")) {
                String sub = JBR_PREFIX + ":" + j.value("uri");
                triplet()
                        .subject(sub)
                        .predicate(RDF_P_TYPE)
                        .object(JBO_C_PASUK);
                triplet()
                        .subject(sub)
                        .predicate(JBO_P_CONTAINER)
                        .object(j.value("parasha"));
                triplet()
                        .subject(sub)
                        .predicate(JBO_P_CONTAINER)
                        .object(j.value("perek"));
                triplet()
                        .subject(sub)
                        .predicate(JBO_P_POSITION_IN_PEREK)
                        .object(j.value("positionInPerek"));
                triplet()
                        .subject(sub)
                        .predicate(JBO_P_POSITION_IN_PARASHA)
                        .object(j.value("positionInParasha"));
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
