package generators;


import json23plet.modules.Json;
import json23plet.modules.Triplet;

/**
 * Created by yon_b on 29/11/16.
 */
public class PasukGenerator extends JbsBaseGenerator implements IGenerator {
    @Override
    public void generate() {
        String jbrPrefix = "jbr:";
        try {
            for (Json j : json23plet.modules.Json.json().getAsArray("subjects")) {
                String sub = jbrPrefix + j.value("uri");
                Triplet
                        .triplet()
                        .subject(sub)
                        .predicate(RDF_P_TYPE)
                        .object(JBO_C_PASUK);
                Triplet
                        .triplet()
                        .subject(sub)
                        .predicate(JBO_P_CONTAINER)
                        .object(j.value("parasha"));
                Triplet
                        .triplet()
                        .subject(sub)
                        .predicate(JBO_P_CONTAINER)
                        .object(j.value("perek"));
                Triplet
                        .triplet()
                        .subject(sub)
                        .predicate(JBO_P_POSITION_IN_PEREK)
                        .object(j.value("positionInPerek"));
                Triplet
                        .triplet()
                        .subject(sub)
                        .predicate(JBO_P_POSITION_IN_PARASHA)
                        .object(j.value("positionInParasha"));
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
