import com.helger.jcodemodel.JClassAlreadyExistsException;
import json23plet.JsonValidators.JbsValidator;
import json23plet.JsonValidators.JsonValidator;
import json23plet.modules.ontologyGenerator.OntologyGenerator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by yon_b on 28/11/16.
 */
public class Json23plet {
    static {
        org.apache.log4j.BasicConfigurator.configure();
        Logger.getRootLogger().setLevel(Level.INFO);
    }

    public static void main(String argv[]) throws IOException, JClassAlreadyExistsException {
//        new Cli(argv).parse();
//        OntologyGenerator.generate("JbsOntology");
        JsonValidator validator = new JbsValidator();
        validator.registerValidators();
        validator.validate("input/mefarshim.json");
    }

    public static void initJson23plet() {
        String[] files = {Paths.get("ontologies", "json").toString(), Paths.get("ontologies", "ttl").toString()};
        for (String file : files) {
            if (!Files.exists(Paths.get(file))) {
                new File(file).mkdirs();
            }
        }
    }

}
