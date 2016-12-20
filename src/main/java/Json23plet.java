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
    static Properties props = new Properties();
    static String propPath = Paths.get("src", "main", "java", "config.properties").toString();
    static String outputDir = "outputDir";
    static {
        try {
            props.load(new FileReader(new File(propPath)));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        org.apache.log4j.BasicConfigurator.configure();
        Logger.getRootLogger().setLevel(Level.INFO);
    }

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

    public static void setNewPropConfigValue(String key, String val) throws IOException {
        FileWriter propWriter = new FileWriter(new File(propPath));
        props.setProperty(key, val);
        props.store(propWriter, "last action: add " + key + " property");
    }

    public static String getPropConfigValue(String key) throws IOException {
        return props.getProperty(key);
    }
}
