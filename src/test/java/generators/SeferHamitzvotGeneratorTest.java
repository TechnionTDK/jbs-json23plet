package generators;

import json23plet.modules.Json;
import json23plet.modules.Triplet;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yogev_henig on 13/12/2016.
 */
public class SeferHamitzvotGeneratorTest {
    @Test
    public void generate() throws Exception {
        /// may change paths to unix and windows together
        String file = "input\\seferhamitzvot-json\\seferhamitzvot.json";
        String ontInput = "ontologies\\jbsOntology.ttl";
        String outputLocation = "src\\test\\testsOutput\\testOutput-seferhamitzvot.ttl";
        Triplet.Init();
        Json.Init(file);
        Generator.Init(ontInput);
        SeferHamitzvotGenerator shg = new SeferHamitzvotGenerator();
        shg.generate();
        Triplet.Export(outputLocation, "TURTLE");
        Triplet.Close();
        assertTrue(TestUtils.isTwoEqual("testOutput-seferhamitzvot.ttl","testExpected-seferhamitzvot.ttl"));
    }
}