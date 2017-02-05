package json23plet.generators.regexGenerators;

import json23plet.JsonValidators.JsonValidator;
import json23plet.JsonValidators.OntologyValidator;
import json23plet.modules.Json;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.rdf.model.Resource;

import java.util.List;

import static json23plet.modules.Json.json;
import static json23plet.modules.Regex.regex;
import static json23plet.modules.Triplet.triplet;
import static json23plet.ontologies.BaseOntology.OWL_C_THING;
import static json23plet.ontologies.BaseOntology.RDF_P_TYPE;
import static json23plet.ontologies.JbsOntology.*;

/**
 * Created by yon_b on 02/01/17.
 */
public class RdfTypeGenerator extends BaseRegexGenerator {
    private class TypeRegEx implements IRegExGenerator {
        String regex;
        Resource clazz;

        TypeRegEx(String regex, Resource clazz) {
            this.regex = regex;
            this.clazz = clazz;
        }

        @Override
        public void generate(Json js) {
            triplet()
                    .subject(js.value("uri"))
                    .predicate(RDF_P_TYPE)
                    .object(clazz);
        }

        @Override
        public boolean match(Json js) {
            return regex(regex)
                    .match(js.value("uri"));
        }
    }

    @Override
    public void registerGenerators() {

        registerGenerator(new TypeRegEx("jbr:.*", OWL_C_THING));
        registerGenerator(new TypeRegEx("jbr:tanach-.*", JBO_C_TANACH));
        registerGenerator(new TypeRegEx("jbr:tanach-[1-5]-.*", JBO_C_PASUKTORAH));
        registerGenerator(new TypeRegEx("jbr:tanach-[6-9]-.*|jbr:tanach-1[0-9]-.*|jbr:tanach-2[0-6]-.*", JBO_C_PASUKNEVIYIM));
        registerGenerator(new TypeRegEx("jbr:tanach-2[7-9]-.*|jbr:tanach-3[0-9]-.*", JBO_C_PASUKKETUVIM));

        registerGenerator(new IRegExGenerator() { // for <book> type
            String seferPosition ="";
            /*
                In JbsOntology there is 39 different books.
                Instead of writing a rule to each book, we iterating over the JboOntology using JboOntology.model
                classes and use its jbo:position predicate to map between the json uri and the book name

                Still you can do it by the naive way e.g:
                registerGenerator(new TypeRegEx("jbr:tanach-1-.*", JBO_C_BERESHIT));
             */
            public void generate(Json js) {
                for (OntClass c : model.listClasses().toList()) { // c holds the current class such as "Bereshit"
                    if (c.hasProperty(JBO_P_POSITION) //  c has property of jbo:position
                            && c.hasSuperClass(JBO_C_PASUK) // c has superclass of jbo:Tanach
                            && !c.equals(JBO_C_PASUKTORAH) && !c.equals(JBO_C_PASUKNEVIYIM) && !c.equals(JBO_C_PASUKKETUVIM) // c is not one of them
                            && c.getProperty(JBO_P_POSITION).getObject().toString().equals(seferPosition)) { // c.position equals to the current uri position
                        triplet()
                                .subject(js.value("uri"))
                                .predicate(RDF_P_TYPE)
                                .object(c);
                        return;
                    }
                }
            }

            @Override
            public boolean match(Json json) {
                if ( json.value("uri").startsWith("jbr:tanach-")) {
                    seferPosition = json.value("uri").split("-")[1];
                }
                return regex("jbr:tanach-\\d+-\\d+-\\d+|jbr:tanach-3[0-9]-\\d+-\\d+")
                        .match(json.value("uri"));
            }
        });

        registerGenerator(new TypeRegEx("jbr:seferhamitzvot-.*", JBO_C_MISHNETORAH));
        registerGenerator(new TypeRegEx("jbr:seferhamitzvot-.*", JBO_C_SEFERHAMITZVOT));

        registerGenerator(new TypeRegEx("jbr:shmonakvatzim-.*", JBO_C_SIFREYRAVKUK));
        registerGenerator(new TypeRegEx("jbr:shmonakvatzim-.*", JBO_C_SHEMONAKEVATZIM));

        registerGenerator(new TypeRegEx("jbr:mesilatyesharim-.*", JBO_C_MESILATYESHARIM));
        registerGenerator(new TypeRegEx("jbr:mesilatyesharim-.*", JBO_C_SIFREYRAMCHAL));
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
