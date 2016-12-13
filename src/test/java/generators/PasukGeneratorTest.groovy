package generators

import json23plet.modules.Json
import json23plet.modules.Triplet
import org.apache.commons.io.FileUtils


/**
 * Created by yogev_henig on 09/12/2016.
 */
class PasukGeneratorTest extends GroovyTestCase {
    void testGenerate() {
        /// may change paths to unix and windows together
        String file = "input\\tanach-json\\tanach\\tanch-1.json"
        String ontInput = "ontologies\\jbsOntology.ttl"
        String outputLocation = "src\\test\\testsOutput\\testOutput-tanch-1.ttl"
        Triplet.Init();
        Json.Init(file);
        Generator.Init(ontInput);
        PasukGenerator g = new PasukGenerator();
        g.generate();
        Triplet.Export(outputLocation, "TURTLE");
        Triplet.Close();
        assertTrue(TestUtils.isTwoEqual("testOutput-tanch-1.ttl","testExpected-tanch-1.ttl"));
    }
}


