package json23plet.JsonValidators;

import json23plet.modules.Json;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static json23plet.generators.GeneratorsUtils.GLOBAL_SETTING_ERROR_LEVEL;
import static json23plet.generators.GeneratorsUtils.getGlobalSettingProp;

/**
 * Created by yon_b on 01/01/17.
 */

public abstract class JsonValidator {
    List<IJsonValidator> validatorsList = new ArrayList<>();
    /**
     * An abstract function, force the user to register generators of type
     * IJsonValidator to the current JsonValidator.
     */
    public abstract void registerValidators();

    /**
     * Define the way we get the json's to work on from your json file (using the Json module).
     * @return A list of Json contains the json's to work on.
     */
    public abstract List<Json> getJsonsToValidate();

    /**
     * Define the way we get the json's to work on from your json file (using the Json module).
     * @param jsonRoot a Json object to define how to work on it.
     * @return A list of Json contains the json's to work on.
     */
    public abstract List<Json> getJsonsToValidate(Json jsonRoot);

    /**
     * Register new IJsonValidator to the generatorsList.
     * @param v  the IJsonValidator to register.
     */
    public void registerValidator(IJsonValidator v) {
        validatorsList.add(v);
    }

    /**
     * Validate all json's files recursively of a given directory..
     * @param jsonPath path to the directory.
     * @throws IOException
     */
    public void validate(String jsonPath) throws IOException {
        if(new File(jsonPath).isFile()) {
            validateSingleJson(jsonPath);
            return;
        }
        List<Path> paths = Files.find(Paths.get(jsonPath), 999, (p, bfa) -> bfa.isRegularFile()).collect(Collectors.toList());
        for (Path file : paths) {
            validateSingleJson(file.toString());
        }


    }

    /**
     * Validate a single json file given by path.
     * @param jsonPath path to the json file.
     */
    public void validateSingleJson(String jsonPath) throws FileNotFoundException {
        String errorLevel = getGlobalSettingProp(GLOBAL_SETTING_ERROR_LEVEL);
        Json.Init(jsonPath);
        System.out.println("[PATH]... " + jsonPath);
        for (Json j : getJsonsToValidate()) {
            for (IJsonValidator v : validatorsList) {
                v.JsonValidate(j);
            }
        }
    }

    /**
     * Validate a full Json object (given as root)
     * @param jsonRoot the Json represent the root of the json file.
     */
    public void validateSingleJson(Json jsonRoot) {
        for (Json j : getJsonsToValidate(jsonRoot)) {
            for (IJsonValidator v : validatorsList) {
                v.JsonValidate(j);
            }
        }
    }

    /**
     * Validate single Json object
     * @param json the object to validate
     * @return true if valid, false otherwise
     */
    public boolean validateSingleJsonObject(Json json) {
        String errorLevel = getGlobalSettingProp(GLOBAL_SETTING_ERROR_LEVEL);
        for (IJsonValidator v : validatorsList) {
            if (! v.JsonValidate(json)) {
                return false;
            }
        }
        return true;
    }

    /**
     * A JsonValidator exception
     */
    public class JsonValidatorException extends Exception {
        JsonValidatorException(String msg) {
            System.err.println(msg);
            System.exit(1);
        }
    }

    /**
     * Define the behavior on validation error.
     * @param messege
     */
    public void onError(String messege) {
        String errorLevel = getGlobalSettingProp(GLOBAL_SETTING_ERROR_LEVEL);
        switch (errorLevel) {
            case "low": break;
            case  "medium": System.err.println(messege); break;
            case "high": System.err.println(messege); System.exit(1);
            default:
        }
    }
}
