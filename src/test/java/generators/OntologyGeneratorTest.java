package generators;

import json23plet.modules.Json;
import json23plet.modules.Triplet;
import json23plet.modules.ontologyGenerator.OntologyGenerator;
import org.junit.Test;

import java.nio.file.Paths;

import static org.junit.Assert.*;

/**
// * Created by yogev_henig on 13/12/2016.
// */
public class OntologyGeneratorTest {
    @Test
    public void generate() throws Exception {
        /// may change paths to unix and windows together
        String OntTestName = "JbsOntologyTest";
        String jbsOntName = "JbsOntology.ttl";
        OntologyGenerator.generate(OntTestName);
        assertTrue(TestUtils.isTwoEqual("testOutput-JbsOntology.ttl","testExpected-JbsOntology.ttl"));
    }

}