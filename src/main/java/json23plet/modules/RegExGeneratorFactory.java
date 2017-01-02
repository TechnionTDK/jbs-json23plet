package json23plet.modules;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by yon_b on 02/01/17.
 */
public class RegExGeneratorFactory {

    static public void activateSingleRegExGenerator(String reGenName, String jsonInput) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if(new File(reGenName).isFile()) {
            activateSingleRegExGeneratorSingleFile(reGenName, new File(jsonInput).getPath());
            return;
        }
        Files.find(Paths.get(jsonInput), 999, (p, bfa) -> bfa.isRegularFile()).forEach(file -> {
            try {
                activateSingleRegExGeneratorSingleFile(reGenName, file.toString());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }

    static private void activateSingleRegExGeneratorSingleFile(String reGenName, String input) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class reClass = Class.forName("json23plet.generators.regExGenerators." + reGenName);
        Constructor ctor = reClass.getConstructor();
        Method registerGenerators = reClass.getDeclaredMethod("registerGenerators", null);
        Method activateRegExGenerator = reClass.getDeclaredMethod("activateRegExGenerator", null);
        Object instance = ctor.newInstance(null);
        Json.Init(input);
        registerGenerators.invoke(instance);
        activateRegExGenerator.invoke(instance);
    }
}
