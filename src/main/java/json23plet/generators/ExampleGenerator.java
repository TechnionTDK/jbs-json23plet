package json23plet.generators;


import json23plet.JsonValidators.JsonValidator;
import json23plet.JsonValidators.OntologyValidator;
import json23plet.modules.Json;

import static json23plet.modules.Json.json;
import static json23plet.modules.Triplet.triplet;
import static json23plet.ontologies.JbsOntology.*;

/**
 * Created by yon_b on 29/11/16.
 */
public class ExampleGenerator extends JbsBaseGenerator {
   @Override
    public void generate() {
       JsonValidator v = new OntologyValidator();
       v.registerValidators();
       try {
           v.validateSingleJson(Json.json());
       } catch (JsonValidator.JsonValidatorException e) {
           e.printStackTrace();
       }
       try {
            super.generate();
            for (Json j : json().getAsArray("subjects")) {
                String prefix = JBR_PREFIX + ":";
                String sub = prefix + j.value("uri");

                triplet()
                        .subject(sub)
                        .predicate(RDF_P_TYPE)
                        .object(JBO_C_PASUK);
                triplet()
                        .subject(sub)
                        .predicate(JBO_P_CONTAINER)
                        .object(prefix + j.value("parasha"));
                triplet()
                        .subject(sub)
                        .predicate(JBO_P_CONTAINER)
                        .object(prefix + j.value("perek"));
                triplet()
                        .subject(sub)
                        .predicate(JBO_P_POSITIONINPEREK)
                        .object(j.value("positionInPerek"));
                triplet()
                        .subject(sub)
                        .predicate(JBO_P_POSITIONINPARASHA)
                        .object(j.value("positionInParasha"));
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
