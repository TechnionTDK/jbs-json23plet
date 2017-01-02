package json23plet.generators.ontologyGenerator;

/**
 * Created by yon_b on 13/12/16.
 */
public class OntologyGenerator {
    static public void generate(String ontName) {
        try {
            OntologyTTLGenerator.generate(ontName);
            OntologyClassGenerator.generate(ontName);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
