package json23plet.generators.ontologyGenerator;

import java.io.PrintStream;

/**
 * Created by yon_b on 13/12/16.
 */
public class OntologyGenerator {
    static public void generate(String ontName) {
        try {
            OntologyTTLGenerator.generate(ontName);
            System.out.println("json23plet has created " + ontName + ".ttl file in " + "jbs-json23plet/ontologies/ttl/" + ontName + ".ttl");
            OntologyClassGenerator.generate(ontName);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
