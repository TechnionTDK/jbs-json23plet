
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by yon_b on 28/11/16.
 */
public class json23plet {
    public static void main(String argv[]) {
        initJson23plet();
        GeneratorFactory.GeneratorFactory.Init("src/main/java/Generators", "input", "output");
        try {
            GeneratorFactory.GeneratorFactory.activateAllGenerators();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    private static void initJson23plet() {
        String[] files = {"output", "ontologies", "input", "src/Generators"};
        for (String file : files) {
            if (!Files.exists(Paths.get(file))) {
                new File(file).mkdir();
            }
        }
    }
}
