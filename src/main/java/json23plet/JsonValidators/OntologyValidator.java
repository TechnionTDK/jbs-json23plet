package json23plet.JsonValidators;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import json23plet.modules.Json;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.ModelFactory;

import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static json23plet.generators.GeneratorsUtils.GLOBAL_SETTING_ERROR_LEVEL;
import static json23plet.generators.GeneratorsUtils.getGlobalSettingProp;

/**
 * Created by yon_b on 02/01/17.
 */
public class OntologyValidator extends  JsonValidator{
    @Override
    public void registerValidators() {
        registerValidator(new IJsonValidator() {
            String jbsOntName = "JbsOntology";
            OntModel model = (OntModel) ModelFactory.createOntologyModel().read(Paths.get("ontologies", "ttl", jbsOntName + ".ttl").toString());
            @Override
            public boolean JsonValidate(Json obj) {
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
                boolean valid = obj
                        .getJsonKeys()
                        .stream()
                        .allMatch(key ->
                                JbsKeys.contains(key));
                String errorLevel = getGlobalSettingProp(GLOBAL_SETTING_ERROR_LEVEL);
                if (!valid) {
                    List<String> keys = obj
                            .getJsonKeys()
                            .stream()
                            .filter(k -> !JbsKeys.contains(k))
                            .collect(Collectors.toList());
                    switch (errorLevel) {
                        case "none": break;
                        case  "info": System.err.println("[ERROR]: The following predicates don't appear in the ontology: " + keys.toString() +
                                " in json:\n" + obj.toString() ); break;
                        case "stop": System.err.println("[ERROR]: The following predicates don't appear in the ontology: " + keys.toString() +
                                " in json:\n" + obj.toString() ); System.exit(1);
                        default:
                    }

                }
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

    @Override
    public List<Json> getJsonsToValidate(Json jsonRoot) {
        return jsonRoot.getAsArray("subjects");
    }
}
