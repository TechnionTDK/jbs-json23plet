package json23plet.JsonValidators;

import json23plet.modules.Json;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static json23plet.generators.GeneratorsUtils.GLOBAL_SETTING_ERROR_LEVEL;
import static json23plet.generators.GeneratorsUtils.getGlobalSettingProp;

/**
 * Created by yon_b on 01/01/17.
 */

public abstract class JsonValidator {
    List<IJsonValidator> validatorsList = new ArrayList<>();
    public abstract void registerValidators();

    public abstract List<Json> getJsonsToValidate();
    public abstract List<Json> getJsonsToValidate(Json jsonRoot);

    public void registerValidator(IJsonValidator v) {
        validatorsList.add(v);
    }

    public void validate(String jsonPath) throws IOException, JsonValidatorException {
        if(new File(jsonPath).isFile()) {
            validateSingleJson(jsonPath);
            return;
        }
        Files.find(Paths.get(jsonPath), 999, (p, bfa) -> bfa.isRegularFile()).forEach(file -> {
            try {
                validateSingleJson(file.toString());
            } catch (JsonValidatorException e) {
                e.printStackTrace();
            }
        });


    }
    public void validateSingleJson(String jsonPath) throws JsonValidatorException {
        String errorLevel = getGlobalSettingProp(GLOBAL_SETTING_ERROR_LEVEL);
        Json.Init(jsonPath);
        System.out.println("[PATH]... " + jsonPath);
        for (Json j : getJsonsToValidate()) {
            for (IJsonValidator v : validatorsList) {
                v.JsonValidate(j);
            }
        }
    }

    public void validateSingleJson(Json jsonRoot) throws JsonValidatorException {
        for (Json j : getJsonsToValidate(jsonRoot)) {
            for (IJsonValidator v : validatorsList) {
                v.JsonValidate(j);
            }
        }
    }

    public boolean validateSingleJsonObject(Json json) throws JsonValidatorException {
        String errorLevel = getGlobalSettingProp(GLOBAL_SETTING_ERROR_LEVEL);
        for (IJsonValidator v : validatorsList) {
            if (! v.JsonValidate(json)) {
                return false;
            }
        }
        return true;
    }
    public class JsonValidatorException extends Exception {
        JsonValidatorException(String msg) {
            System.err.println(msg);
            System.exit(1);
        }
    }
    public void onError(String messege) {
        String errorLevel = getGlobalSettingProp(GLOBAL_SETTING_ERROR_LEVEL);
        switch (errorLevel) {
            case "none": break;
            case  "info": System.err.println(messege); break;
            case "stop": System.err.println(messege); System.exit(1);
            default:
        }
    }
}
