package json23plet.modules;

import com.helger.jcodemodel.*;
import json23plet.modules.Json;

import static json23plet.modules.Json.json;

import json23plet.ontologies.BaseOntology;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yon_b on 13/12/16.
 */
public class OntologyClassGenerator {

    static JCodeModel om = new JCodeModel();
    static JPackage op = om._package("json23plet.ontologies");

    static public void generate(String clssName) throws JClassAlreadyExistsException, IOException {
        Json.Init(Paths.get("ontologies", "json", clssName + ".json").toString());
        JDefinedClass oc = op._class(clssName);
        oc._extends(BaseOntology.class);

        createOntModelComponent(oc, clssName);
        createPrefixesComponent(oc);
        createUrisComponent(oc);
        createClassesComponent(oc);
        createPredicatesComponent(oc);

        File file = new File(Paths.get("src", "main", "java").toString());
        om.build(file);
    }

    static private void createOntModelComponent(JDefinedClass oc, String clssName) {
        JFieldVar model = oc.field(JMod.PUBLIC | JMod.STATIC, ModelFactory.class, "smodel");

        JFieldVar ontModel = oc.field(JMod.PUBLIC | JMod.STATIC, OntModel.class, "model");
        ontModel.init(JExpr.direct("(OntModel) ((OntModel) ModelFactory.createOntologyModel()).read(\"" +
                Paths.get("ontologies", "ttl", clssName + ".ttl") + "\")"));
    }

    static private void createPrefixesComponent(JDefinedClass oc) {
        for (Json j : json().getAsArray("prefixes")) {
            JFieldVar prefix = oc.field(JMod.PUBLIC | JMod.STATIC, String.class, j.value("prefix").toUpperCase() + "_PREFIX");
            prefix.init(JExpr.lit(j.value("prefix")));
        }
    }

    static private void createUrisComponent(JDefinedClass oc) {
        for (Json j : json().getAsArray("prefixes")) {
            JFieldVar uri = oc.field(JMod.PUBLIC | JMod.STATIC , String.class, j.value("prefix").toUpperCase() + "_URI");
            uri.init(JExpr.lit(j.value("uri")));
        }
    }
    static private void createClassesComponent(JDefinedClass oc) {
        List<Json> resourceList = json().getAsArray("metadata")
                .stream()
                .filter(obj -> obj.value("predicate").contains("type")
                        && obj.value("object").contains("Class"))
                .collect(Collectors.toList());

        for (Json j : resourceList) {
            JFieldVar clss = oc.field(JMod.PUBLIC | JMod.STATIC, Resource.class, getClassFiledName(j.value("subject")));
            clss.init(JExpr.direct("model.getOntClass(JBO_URI + \"" + j.value("subject").split(":")[1] +"\")"));

        }

    }

    static private void createPredicatesComponent(JDefinedClass oc) {
        List<Json> propList = json().getAsArray("metadata")
                .stream()
                .filter(obj -> obj.value("predicate").contains("type")
                        && obj.value("object")
                        .contains("Property")).collect(Collectors.toList());

        for (Json j : propList) {
            JFieldVar clss = oc.field(JMod.PUBLIC | JMod.STATIC, Property.class, getPredicateFiledName(j.value("subject")));
            clss.init(JExpr.direct("model.getOntProperty(JBO_URI + \"" + j.value("subject").split(":")[1] +"\")"));

        }

    }

    static private String getClassFiledName(String jsonValue) {
        String prefix = jsonValue.split(":")[0].toUpperCase();
        String name = jsonValue.split(":")[1].toUpperCase();
        return prefix + "_C_" + name;
    }

    static private  String getPredicateFiledName(String jsonValue) {
        String prefix = jsonValue.split(":")[0].toUpperCase();
        String name = jsonValue.split(":")[1].toUpperCase();
        return prefix + "_P_" + name;
    }
}
