package generators;

import json23plet.modules.Json;
import json23plet.modules.Triplet;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yogev_henig on 13/12/2016.
 */
public class OntologyGeneratorTest {
    @Test
    public void generate() throws Exception {
        /// may change paths to unix and windows together
        String file = "input\\ontologies\\jbsOntology\\jbsOntology.json";
        //String ontInput = "ontologies\\jbsOntology.ttl";
        String outputLocation = "src\\test\\testsOutput\\testOutput-jbsOntology.ttl";
        Triplet.Init();
        Json.Init(file);
        Generator.Init("");
        OntologyGenerator og = new OntologyGenerator();
        og.generate();
        Triplet.Export(outputLocation, "TURTLE");
        Triplet.Close();
        assertTrue(TestUtils.isTwoEqual("testOutput-jbsOntology.ttl","testExpected-jbsOntology.ttl"));
    }

}