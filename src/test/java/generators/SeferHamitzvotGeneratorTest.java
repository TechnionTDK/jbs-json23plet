package generators;

import json23plet.generators.SeferHamitzvotGenerator;
import json23plet.modules.Json;
import json23plet.modules.Triplet;
import json23plet.ontologies.JbsOntology;
import org.junit.Test;

import java.nio.file.Paths;

import static org.junit.Assert.*;

/**
 * Created by yogev_henig on 13/12/2016.
 */
//public class SeferHamitzvotGeneratorTest {
//    @Test
//    public void generate() throws Exception {
//        /// may change paths to unix and windows together
//        String file = Paths.get("input", "seferhamitzvot-json", "seferhamitzvot.json").toString();
//        String outputLocation = Paths.get("src", "test", "testsOutput", "testOutput-seferhamitzvot.ttl").toString();
//        Triplet.Init();
//        Json.Init(file);
//        JbsOntology init = new JbsOntology();
//        SeferHamitzvotGenerator shg = new SeferHamitzvotGenerator();
//        shg.generate();
//        Triplet.Export(outputLocation, "TURTLE");
//        Triplet.Close();
//        assertTrue(TestUtils.isTwoEqual("testOutput-seferhamitzvot.ttl","testExpected-seferhamitzvot.ttl"));
//    }
//}