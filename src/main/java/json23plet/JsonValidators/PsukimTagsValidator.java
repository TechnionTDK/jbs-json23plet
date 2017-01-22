package json23plet.JsonValidators;

import json23plet.modules.Json;

import java.util.List;

import static json23plet.modules.Json.json;

/**
 * Created by yon_b on 22/01/17.
 */
public class PsukimTagsValidator extends JsonValidator{
    @Override
    public void registerValidators() {
        registerValidator(new IJsonValidator() {
            boolean retValue = true;
            @Override
            public boolean JsonValidate(Json obj) {
                if (!obj.has("uri")) {
                    onError("[ERROR]: missing uri property in json:\n" + obj.toString());
                    return false;
                }
                if (!obj.value("uri").split(":")[0].equals("jbr")) {
                    onError("[ERROR]: missing uri prefix in json:\n" + obj.toString());
                    return false;
                }
                for (Json j : obj.getAsArray("tags")) {
                    if (!j.has("uri")) {
                        onError("[ERROR]: missing tagged uri property in json:\n" + obj.toString());
                        return false;
                    }
                    if (!j.value("uri").split(":")[0].equals("jbr")) {
                        onError("[ERROR]: missing secondary uri prefix in json:\n" + obj.toString());
                        return false;
                    }
                    if (!j.has("span")) {
                        onError("[ERROR]: missing span property in json:\n" + obj.toString());
                        retValue = false;
                    }
                }
                return retValue;
            }
        });

    }

    @Override
    public List<Json> getJsonsToValidate() {
        return json().getAsArray("subjects");

    }

    @Override
    public List<Json> getJsonsToValidate(Json jsonRoot) {
        return jsonRoot.getAsArray("subjects");
    }
}
