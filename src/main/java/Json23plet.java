import json23plet.modules.GeneratorFactory;
import json23plet.modules.OntologyClassGenerator;
import json23plet.modules.OntologyGenerator;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by yon_b on 28/11/16.
 */
public class Json23plet {
    public static void main(String argv[]) {
        boolean all = true;
        try {
            initJson23plet();
            GeneratorFactory.Init();
            if(all) {
                GeneratorFactory.activateAllGenerators();
            }
            //TODO: else call to GeneratorFactory.activateGenerator
            //TODO: call to OntologyGenerator.generate(ontName) on demand
//            OntologyGenerator.generate("jbsOntology");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    private static void initJson23plet() {
        String[] files = {"ontologies/json", "ontologies/ttl"};
        for (String file : files) {
            if (!Files.exists(Paths.get(file))) {
                new File(file).mkdirs();
            }
        }
    }
}
