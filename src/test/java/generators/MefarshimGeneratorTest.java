package generators;

import json23plet.generators.MefarshimGenerator;
import json23plet.modules.GeneratorFactory;
import json23plet.modules.Json;
import json23plet.modules.Triplet;
import json23plet.ontologies.JbsOntology;
import org.junit.Test;

import java.nio.file.Paths;

import static org.junit.Assert.*;

/**
 * Created by yogev_henig on 13/12/2016.
 */
public class MefarshimGeneratorTest {
    @Test
    public void generate() throws Exception {
        String folder =  Paths.get("src", "test", "testInput", "mefarshim").toString() ;
        String outputLocation =  Paths.get("src", "test", "testsOutput").toString();
        JbsOntology init = new JbsOntology();
        GeneratorFactory.activateGenerator("MefarshimGenerator", folder, outputLocation, false);
        assertTrue(TestUtils.isTwoEqual("mefarshim"));
    }

}