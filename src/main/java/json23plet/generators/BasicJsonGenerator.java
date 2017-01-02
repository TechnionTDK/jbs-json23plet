package json23plet.generators;


import json23plet.JsonValidators.JsonValidator;
import json23plet.JsonValidators.OntologyValidator;
import json23plet.modules.Json;

/**
 * Created by yon_b on 02/01/17.
 */
public class BasicJsonGenerator {
    public void generate() throws JsonValidator.JsonValidatorException {
        JsonValidator v = new OntologyValidator();
        v.registerValidators();
        v.validateSingleJson(Json.json());
        GeneratorsUtils.generateBasicJsonForm("subjects");
    }
}
