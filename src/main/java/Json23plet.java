import json23plet.modules.GeneratorFactory;
import json23plet.modules.Json;
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

    public static void main(String argv[]) throws Exception {
        new Cli(argv).parse();
    }

}
