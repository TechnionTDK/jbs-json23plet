import json23plet.modules.GeneratorFactory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Created by yon_b on 28/11/16.
 */
public class Json23plet {
    public static void main(String argv[]) {
        new Cli(argv).parse();
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
