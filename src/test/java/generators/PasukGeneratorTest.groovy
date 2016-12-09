package generators

import json23plet.modules.Json
import json23plet.modules.Triplet


/**
 * Created by yogev_henig on 09/12/2016.
 */
class PasukGeneratorTest extends GroovyTestCase {
    void testGenerate() {
        String jsonInput = "input\\tanach-json\\tanach\\tanch-1.json"
        String ontInput = "ontologies\\jbsOntology.ttl"
        String outputLocation = "testsOutput\\test-tanch-1.ttl"

        Triplet.Init();
            Json.Init(jsonInput);
            Generator.Init(ontInput);
            PasukGenerator g = new PasukGenerator();
            g.generate();
            Triplet.Export(outputLocation, "TURTLE");
            Triplet.Close();
        }
    }


