package generators;

import json23plet.generators.PasukGenerator;
import json23plet.modules.Json;
import json23plet.modules.Triplet;
import json23plet.ontologies.JbsOntology;
import org.junit.Test;

import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

/**
 * Created by yogev_henig on 13/12/2016.
 */
//public class PasukGeneratorTest {
//    @Test
//    public void generate() throws Exception {
//        /// may change paths to unix and windows together
//        String file = Paths.get("input", "tanach-json", "tanach", "tanch-1.json").toString();
//        String ontInput =  Paths.get("ontologies", "JbsOntology.ttl").toString();
//        String outputLocation =  Paths.get("src", "test", "testsOutput", "testOutput-tanch-1.ttl").toString();
//        Triplet.Init();
//        Json.Init(file);
//        JbsOntology init = new JbsOntology();
//        PasukGenerator g = new PasukGenerator();
//        g.generate();
//        Triplet.Export(outputLocation, "TURTLE");
//        Triplet.Close();
//        assertTrue(TestUtils.isTwoEqual("testOutput-tanch-1.ttl","testExpected-tanch-1.ttl"));
//    }
//}