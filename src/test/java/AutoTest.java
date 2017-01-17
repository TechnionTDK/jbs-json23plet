/**
 * Created by yon_b on 15/01/17.
 */

import difflib.Delta;
import json23plet.modules.GeneratorFactory;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import difflib.Chunk;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static json23plet.modules.Regex.regex;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class AutoTest {
    String genPath = Paths.get("src", "main", "java", "json23plet", "generators").toString();
    String testPath = Paths.get("src", "test", "testsFiles").toString();

    private static File[] listFilesMatching(File root, String regex) {
        if(!root.isDirectory()) {
            throw new IllegalArgumentException(root+" is no directory.");
        }
        final Pattern p = Pattern.compile(regex); // careful: could also throw an exception!
        return root.listFiles(new FileFilter(){
            @Override
            public boolean accept(File file) {
                return p.matcher(file.getName()).matches();
            }
        });
    }
    private void handleSingleFile(File fileToCheck, String genName) {
        System.out.println("Testing " + genName + " Generator...");
        try {
            GeneratorFactory.generate(genName, fileToCheck.toString() , testPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Files.find(Paths.get(testPath), 999, (p, bfa) -> bfa.isRegularFile())
                    .filter(f -> regex("(.*)\\.input(.*)\\.ttl").match(f.getFileName().toString()))
                    .forEach(f -> {

                        String outputPath = f.toString().substring(0, f.toString().indexOf(".input.")) + ".output.ttl"; //   f.toString().replace("input.ttl", "output.ttl");
                        if ((new File(outputPath).exists())) {
                            new File(outputPath).delete();
                        }
                        new File(f.toString())
                                .renameTo(new File(outputPath));
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        String expectedFilePath = Paths.get(this.testPath, fileToCheck.getName().replace(".input.json", ".expected.ttl")).toString();
        String outputFilePath = Paths.get(this.testPath, fileToCheck.getName().replace(".input.json", ".output.ttl")).toString();
        File original = new File(expectedFilePath);
        File revised = new File(outputFilePath);
        final Diff comparator = new Diff(original, revised);
        try {
            List<Delta> deltas = comparator.getDeltas();
            deltas.forEach(d -> System.out.println(d));
            assertEquals(deltas.size(), 0);
        } catch (IOException ioe) {
            fail("Error running test AutoTest " + ioe.toString());
        }

    }
    @Test
    public void Test() throws IOException {
        org.apache.log4j.BasicConfigurator.configure();
        Logger.getRootLogger().setLevel(Level.INFO);
        Files.find(Paths.get(genPath), 999, (p, bfa) -> bfa.isRegularFile()).forEach(file -> {
            String genName = file.getFileName().toString().split("\\.")[0];
            File[] inputFiles = listFilesMatching(new File(this.testPath), genName + "(.*)" + "\\.input\\.json");
            Arrays.stream(inputFiles).forEach(inf -> handleSingleFile(inf, genName));
        });
    }
}
