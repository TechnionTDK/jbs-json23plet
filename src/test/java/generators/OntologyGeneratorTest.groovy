package generators

import json23plet.modules.Json
import json23plet.modules.Triplet

/**
 * Created by yogev_henig on 13/12/2016.
 */
class OntologyGeneratorTest extends GroovyTestCase {
    void testGenerate() {
        /// may change paths to unix and windows together
        String file = "input\\ontologies\\jbsOntology\\jbsOntology.json"
        String ontInput = "ontologies\\jbsOntology.ttl"
        String outputLocation = "src\\test\\testsOutput\\testOutput-jbsOntology.ttl"
        Triplet.Init();
        Json.Init(file);
        Generator.Init(ontInput);
        OntologyGenerator og = new OntologyGenerator();
        og.generate();
        Triplet.Export(outputLocation, "TURTLE");
        Triplet.Close();
        assertTrue(TestUtils.isTwoEqual("testOutput-jbsOntology.ttl","testExpected-jbsOntology.ttl"));
    }

}
