package json23plet.generators.customGenerators;


import json23plet.JsonValidators.JsonValidator;
import json23plet.JsonValidators.OntologyValidator;
import json23plet.generators.GeneratorsUtils;
import json23plet.modules.DataPublisher;
import json23plet.modules.Json;
import json23plet.modules.Triplet;

import java.io.IOException;

/**
 * Created by yon_b on 02/01/17.
 */
public class BasicJsonGenerator extends Generator {
    public void generate() throws IOException {
        GeneratorsUtils.generateBasicJsonForm("subjects");
        DataPublisher.publish("", "." + getID() + ".ttl", "TURTLE");
    }

    @Override
    public String getID() {
        return "basic";
    }
}