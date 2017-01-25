package json23plet.generators.regexGenerators;

import json23plet.JsonValidators.JsonValidator;
import json23plet.JsonValidators.OntologyValidator;
import json23plet.modules.Json;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.rdf.model.Resource;

import java.util.List;
import java.util.stream.Collectors;

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
    @Override
    public void registerGenerators() {
        registerGenerator(new IRegExGenerator() { //Thing type
            @Override
            public void generate(Json js) {
                triplet()
                        .subject(js.value("uri"))
                        .predicate(RDF_P_TYPE)
                        .object(OWL_C_THING);
            }

            @Override
            public boolean match(Json json) {
                return regex("jbr:.*")
                        .match(json.value("uri"));
            }

        });
        registerGenerator(new IRegExGenerator() { // Tanach type
            @Override
            public void generate(Json js) {
                triplet()
                        .subject(js.value("uri"))
                        .predicate(RDF_P_TYPE)
                        .object(JBO_C_TANACH);
            }

            @Override
            public boolean match(Json json) {
                    return regex("jbr:tanach-.*") //-(.*)-(\d+)-(\d+)-(\d+)
                            .match(json.value("uri"));
            }
        });

        registerGenerator(new IRegExGenerator() { // Torah type
            @Override
            public void generate(Json js) {
                triplet()
                        .subject(js.value("uri"))
                        .predicate(RDF_P_TYPE)
                        .object(JBO_C_TORAH);
            }

            @Override
            public boolean match(Json json) {
                return regex("jbr:tanach-[1-5]-\\d+-\\d+")
                        .match(json.value("uri"));
            }
        });

        registerGenerator(new IRegExGenerator() { // Neviyim type
            @Override
            public void generate(Json js) {
                triplet()
                        .subject(js.value("uri"))
                        .predicate(RDF_P_TYPE)
                        .object(JBO_C_NEVIYIM);
            }

            @Override
            public boolean match(Json json) {
                return regex("jbr:tanach-[6-9]-\\d+-\\d+" +
                        "|jbr:tanach-1[0-9]-\\d+-\\d+" +
                        "|jbr:tanach-2[0-6]-\\d+-\\d+")
                        .match(json.value("uri"));
            }
        });

        registerGenerator(new IRegExGenerator() { // Ketuvim type
            @Override
            public void generate(Json js) {
                triplet()
                        .subject(js.value("uri"))
                        .predicate(RDF_P_TYPE)
                        .object(JBO_C_KETUVIM);
            }

            @Override
            public boolean match(Json json) {
                return regex("jbr:tanach-2[7-9]-\\d+-\\d+|jbr:tanach-3[0-9]-\\d+-\\d+")
                        .match(json.value("uri"));
            }
        });


        registerGenerator(new IRegExGenerator() { // sefer type
            String seferPosition ="";

            /*
                In JbsOntology there is 39 different books.
                Instead of writing a rule to each book, we iterating over the JboOntology using JboOntology.model
                classes and use its jbo:position predicate to map between the json uri and the book name
             */
            public void generate(Json js) {
                for (OntClass c : model.listClasses().toList()) { // c holds the current class such as "Bereshit"
                    if (c.hasProperty(JBO_P_POSITION) //  c has property of jbo:position
                            && c.hasSuperClass(JBO_C_TANACH) // c has superclass of jbo:Tanach
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

        registerGenerator(new IRegExGenerator() { // mishneTorah type
            @Override
            public void generate(Json js) {
                triplet()
                        .subject(js.value("uri"))
                        .predicate(RDF_P_TYPE)
                        .object(JBO_C_MISHNETORAH);
                triplet()
                        .subject(js.value("uri"))
                        .predicate(RDF_P_TYPE)
                        .object(JBO_C_SEFERHAMITZVOT);
            }

            @Override
            public boolean match(Json json) {
                return regex("jbr:seferhamitzvot-\\d+-\\d+")
                        .match(json.value("uri"));
            }
        });


        registerGenerator(new IRegExGenerator() { // shmonaKavatzim type
            @Override
            public void generate(Json js) {
                triplet()
                        .subject(js.value("uri"))
                        .predicate(RDF_P_TYPE)
                        .object(JBO_C_SIFREYRAVKUK);
                triplet()
                        .subject(js.value("uri"))
                        .predicate(RDF_P_TYPE)
                        .object(JBO_C_SHEMONAKEVATZIM);
            }

            @Override
            public boolean match(Json json) {
                return regex("jbr:shmonakvatzim-.*")
                        .match(json.value("uri"));
            }
        });

        registerGenerator(new IRegExGenerator() { // mesilatYesharim type
            @Override
            public void generate(Json js) {
                triplet()
                        .subject(js.value("uri"))
                        .predicate(RDF_P_TYPE)
                        .object(JBO_C_MESILATYESHARIM);
                triplet()
                        .subject(js.value("uri"))
                        .predicate(RDF_P_TYPE)
                        .object(JBO_C_SIFREYRAMCHAL);
            }

            @Override
            public boolean match(Json json) {
                return regex("jbr:mesilatyesharim-.*")
                        .match(json.value("uri"));
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
