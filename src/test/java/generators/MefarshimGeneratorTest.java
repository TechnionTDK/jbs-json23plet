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
        /// may change paths to unix and windows together
        String file =  Paths.get("src", "test", "testInput", "mefarshim").toString() ;//need to change path to mefarshim
        String outputLocation =  Paths.get("src", "test", "testsOutput").toString();
        JbsOntology init = new JbsOntology();
        GeneratorFactory.activateGenerator("SeferHamitzvotGenerator", file, outputLocation);
        assertTrue(TestUtils.isTwoEqual("mefarshim.ttl","mefarshim.ttl"));
    }

}