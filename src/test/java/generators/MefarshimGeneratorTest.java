package generators;

import json23plet.modules.Json;
import json23plet.modules.Triplet;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yogev_henig on 13/12/2016.
 */
public class MefarshimGeneratorTest {
    @Test
    public void generate() throws Exception {
        /// may change paths to unix and windows together
        String file = "input\\tanach-json\\tanach\\tanch-1.json" ;//need to change path to mefarshim
        String ontInput = "ontologies\\jbsOntology.ttl";
        String outputLocation = "src\\test\\testsOutput\\testOutput-mefarshim.ttl";
        Triplet.Init();
        Json.Init(file);
        Generator.Init(ontInput);
        MefarshimGenerator mg = new MefarshimGenerator();
        mg.generate();
        Triplet.Export(outputLocation, "TURTLE");
        Triplet.Close();
        assertTrue(TestUtils.isTwoEqual("testOutput-mefarshim.ttl","testExpected-mefarshim.ttl"));
    }

}