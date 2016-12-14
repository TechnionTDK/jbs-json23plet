package generators;

import json23plet.generators.MefarshimGenerator;
import json23plet.modules.Json;
import json23plet.modules.Triplet;
import json23plet.ontologies.jbsOntology;
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
        String file =  Paths.get("input", "tanach-json", "tanach", "tanch-1.json").toString() ;//need to change path to mefarshim
        String outputLocation =  Paths.get("src", "test", "testsOutput", "testOutput-mefarshim.ttl").toString();
        Triplet.Init();
        Json.Init(file);
        jbsOntology init = new jbsOntology();
        MefarshimGenerator mg = new MefarshimGenerator();
        mg.generate();
        Triplet.Export(outputLocation, "TURTLE");
        Triplet.Close();
        assertTrue(TestUtils.isTwoEqual("testOutput-mefarshim.ttl","testExpected-mefarshim.ttl"));
    }

}