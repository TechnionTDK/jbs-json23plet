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
import java.nio.file.Files;
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

    public void validate(String jsonPath) throws IOException {
        Files.find(Paths.get(jsonPath), 999, (p, bfa) -> bfa.isRegularFile()).forEach(file -> {
            validateSingleJsonFile(file.toString());
        });


    }
    private void validateSingleJsonFile(String jsonPath) {
        Json.Init(jsonPath);
        System.out.println("[PATH]... " + jsonPath);
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
}
