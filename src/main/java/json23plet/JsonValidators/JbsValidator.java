package json23plet.JsonValidators;

import json23plet.modules.Json;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.ModelFactory;

import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yon_b on 02/01/17.
 */
public class JbsValidator extends  JsonValidator{
    @Override
    public void registerValidators() {
        registerValidator(new IJsonValidator() {
            @Override
            public boolean JsonValidate(Json obj) {
                String jbsOntName = "JbsOntology";
                OntModel model = (OntModel) ModelFactory.createOntologyModel().read(Paths.get("ontologies", "ttl", jbsOntName + ".ttl").toString());
                List<String> JbsKeys = model.listAllOntProperties()
                .toList()
                .stream()
                .map(p -> model.getNsURIPrefix(p.getNameSpace()) + ":" + p.getLocalName())
                .collect(Collectors.toList());
                JbsKeys.addAll(
                model
                        .listClasses()
                        .toList()
                        .stream()
                        .map(c -> model.getNsURIPrefix(c.getNameSpace()) + ":" + c.getLocalName())
                        .collect(Collectors.toList())
                );
                JbsKeys.add("uri");
                return obj
                        .getJsonKeys()
                        .stream()
                        .allMatch(key ->
                                JbsKeys.contains(key)
                        );
            }
        });

    }

    @Override
    public List<Json> getJsonsToValidate() {
        return Json.json().getAsArray("subjects");
    }
}
