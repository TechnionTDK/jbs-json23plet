package generators;

import json23plet.generators.PasukGenerator;
import json23plet.modules.GeneratorFactory;
import json23plet.modules.Json;
import json23plet.modules.Triplet;
import json23plet.ontologies.JbsOntology;
import org.junit.Test;

import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

/**
 * Created by yogev_henig on 13/12/2016.
 */
public class PasukGeneratorTest {
    @Test
    public void generate() throws Exception {
        String folder = Paths.get("src", "test", "testInput", "tanach").toString();
        String outputLocation =  Paths.get("src", "test", "testsOutput").toString();
        JbsOntology init = new JbsOntology();
        GeneratorFactory.generate("PasukGenerator", folder, outputLocation);
        assertTrue(TestUtils.isTwoEqual("tanach"));
    }
}