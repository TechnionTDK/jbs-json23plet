package json23plet.generators.regexGenerators;

import json23plet.JsonValidators.JsonValidator;
import json23plet.JsonValidators.OntologyValidator;
import json23plet.modules.Json;

import java.util.List;

import static json23plet.modules.Json.json;
import static json23plet.modules.Regex.regex;
import static json23plet.modules.Triplet.triplet;
import static json23plet.ontologies.BaseOntology.OWL_C_THING;
import static json23plet.ontologies.BaseOntology.RDF_P_TYPE;
import static json23plet.ontologies.JbsOntology.JBO_C_PERUSH;

/**
 * Created by yon_b on 02/01/17.
 */
public class RdfTypeGenerator extends BaseRegexGenerator {
    @Override
    public void registerGenerators() {
        registerGenerator(new IRegExGenerator() {
            @Override
            public void generate(Json js) {
                triplet()
                        .subject(js.value("uri"))
                        .predicate(RDF_P_TYPE)
                        .object(OWL_C_THING);
            }

            @Override
            public boolean match(Json json) {
                return regex("jbr:(.*)")
                        .match(json.value("uri"));
            }

        });
        registerGenerator(new IRegExGenerator() {
            @Override
            public void generate(Json js) {
                triplet()
                        .subject(js.value("uri"))
                        .predicate(RDF_P_TYPE)
                        .object(JBO_C_PERUSH);
            }

            @Override
            public boolean match(Json json) {
                try {
                    return regex("jbr:tanach-(.*)-(\\d+)-(\\d+)-(\\d+)")
                            .match(json.value("uri"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return false;
            }
        });

    }

    @Override
    public List<Json> getJsonsToGenerate() {
        return json().getAsArray("subjects");
    }

    @Override
    public String getID() {
        return "rdfType";
    }

    @Override
    public void generate() {
        JsonValidator v = new OntologyValidator();
        v.registerValidators();
        try {
            v.validateSingleJson(Json.json());
        } catch (JsonValidator.JsonValidatorException e) {
            e.printStackTrace();
        }
        super._generate();
    }
}
