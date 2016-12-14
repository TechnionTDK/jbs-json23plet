import json23plet.modules.GeneratorFactory;
import json23plet.modules.ontologyGenerator.OntologyGenerator;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Created by yon_b on 28/11/16.
 */
public class Json23plet {
    static Properties props = new Properties();
    static String propPath = Paths.get("src", "main", "java", "config.properties").toString();
    static {

        try {
            props.load(new FileReader(new File(propPath)));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String argv[]) {
        boolean all = true;

        try {
            initJson23plet();
            GeneratorFactory.Init("output");
            if(all) {
                GeneratorFactory.activateAllGenerators();
            }
            //TODO: else call to GeneratorFactory.activateGenerator
            //TODO: call to OntologyGenerator.generate(ontName) on demand
//            OntologyGenerator.generate("JbsOntology");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    private static void initJson23plet() {
        String[] files = {Paths.get("ontologies", "json").toString(), Paths.get("ontologies", "ttl").toString()};
        for (String file : files) {
            if (!Files.exists(Paths.get(file))) {
                new File(file).mkdirs();
            }
        }
    }

    private static void setNewPropConfigValue(String key, String val) throws IOException {
        FileWriter propWriter = new FileWriter(new File(propPath));
        props.setProperty(key, val);
        props.store(propWriter, "last action: add " + key + " property");
    }

    private static String getPropConfigValue(String key) throws IOException {
        return props.getProperty(key);
    }
}
