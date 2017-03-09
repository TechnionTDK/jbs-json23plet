package json23plet.JsonValidators;

import json23plet.modules.Json;

/**
 * Created by yon_b on 02/01/17.
 */
public interface IJsonValidator {

    /**
     * Validate a Json object.
     * @param obj the Json to validate.
     * @return true if valid, otherwise false.
     */
    public boolean JsonValidate(Json obj);
}
