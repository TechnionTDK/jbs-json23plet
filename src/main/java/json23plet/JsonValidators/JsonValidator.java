package json23plet.JsonValidators;

import com.github.jsonldjava.utils.Obj;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import json23plet.JsonValidators.IJsonValidator;
import json23plet.modules.Json;
import org.apache.jena.ontology.*;
import org.apache.jena.rdf.model.ModelFactory;

import java.awt.*;
import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static json23plet.modules.Json.json;

/**
 * Created by yon_b on 01/01/17.
 */
public abstract class JsonValidator {
    static List<IJsonValidator> validatorsList = new ArrayList<>();
    public abstract void registerValidators();

    public abstract List<Json> getJsonsToValidate();

    public void registerValidator(IJsonValidator v) {
        validatorsList.add(v);
    }

    public void validate(String jsonPath) {
        Json.Init(jsonPath);
        for (Json j : getJsonsToValidate()) {
            for (IJsonValidator v : validatorsList) {
                if (v.JsonValidate(j)) {
                    System.out.println("[O.K]... " + j.value("uri"));
                } else {
                    System.out.println("[ERROR]... " + j.value("uri"));
                }
            }
        }

    }


//    static public boolean spell(String inputPath, String ontName) {
//        OntModel model = (OntModel) ModelFactory.createOntologyModel().read(Paths.get("ontologies", "ttl", ontName + ".ttl").toString());
//
//
//        return true;
//    }
//    static private List<String> getAllProperties(OntModel om) {
//        List<String> res = om.listObjectProperties()
//                .toList()
//                .stream()
//                .map(p -> om.getNsURIPrefix(p.getNameSpace()) + ":" + p.getLocalName())
//                .collect(Collectors.toList());
//        return res;
//    }
//
//    static private void validateJsonAsStream(String jsonPath, OntModel om) throws IOException {
//
//        InputStream stream;
//        JsonReader reader = new JsonReader(new FileReader(jsonPath));
//        Gson gson = new GsonBuilder().create();
//        reader.beginArray();
//        while (reader.hasNext()) {
//            JsonElement element = gson.fromJson (reader, JsonElement.class);
//            if (! validateJsonObjectProperties(element, om)) {
//                System.out.println("error");
//            }
//
//        }
//
//    }
//
//    static boolean validateJsonObjectProperties(JsonElement json, OntModel om) {
//        List<String> ontProp = getAllProperties(om);
//        ontProp.add("uri");
//        Set<Map.Entry<String, JsonElement>> entries = json
//                .getAsJsonObject()
//                .entrySet();
//
//        return entries
//                .stream()
//                .allMatch(entry ->
//                        ontProp
//                        .contains(
//                                entry.getKey()
//                        )
//                );
//    }
}
