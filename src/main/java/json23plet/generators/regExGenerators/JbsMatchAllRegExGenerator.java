package json23plet.generators.regExGenerators;

import json23plet.modules.Json;
import json23plet.modules.Triplet;

import java.util.List;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static json23plet.modules.Json.json;
import static json23plet.modules.Triplet.triplet;
import static json23plet.ontologies.BaseOntology.OWL_C_THING;
import static json23plet.ontologies.BaseOntology.RDFS_P_SUB_CLASS_OF;

/**
 * Created by yon_b on 02/01/17.
 */
public class JbsMatchAllRegExGenerator extends BasicRegExGenerator {
    @Override
    public void registerGenerators() {
        registerGenerator(new IRegExGenerator() {
            @Override
            public void generate(Json js) {
                triplet()
                        .subject(js.value("uri"))
                        .predicate(RDFS_P_SUB_CLASS_OF)
                        .object(OWL_C_THING);
            }

            @Override
            public boolean match(String uri) {
                Pattern p = Pattern.compile(getRegEx());
                Matcher m = p.matcher(uri);
                return m.find();
            }

            @Override
            public String outputPath() {
                return "bbb/try.ttl";
            }

            @Override
            public String getRegEx() {
                return ".*";
            }
        });

    }

    @Override
    public List<Json> getJsonsToGenerate() {
        return json().getAsArray("subjects");
    }

    @Override
    public void activateRegExGenerator() {
        super._activateRegExGenerator();
    }
}
