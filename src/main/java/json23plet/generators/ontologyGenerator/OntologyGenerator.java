package json23plet.generators.ontologyGenerator;

import com.helger.jcodemodel.JClassAlreadyExistsException;

import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by yon_b on 13/12/16.
 */
public class OntologyGenerator {
    /**
     * Generate the ontology class and the ontology ttl.
     * @param ontName
     */
    static public void generate(String ontName) throws IOException, JClassAlreadyExistsException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        OntologyTTLGenerator.generate(ontName);
        System.out.println("json23plet has created " + ontName + ".ttl file in " + "jbs-json23plet/ontologies/ttl/" + ontName + ".ttl");
        OntologyClassGenerator.generate(ontName);
    }
}
