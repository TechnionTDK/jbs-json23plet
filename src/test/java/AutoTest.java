/**
 * Created by yon_b on 15/01/17.
 */

import json23plet.modules.GeneratorFactory;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class AutoTest {
    String genPath = Paths.get("src", "main", "java", "json23plet", "generators").toString();
    String testPath = Paths.get("src", "test", "testsFiles").toString();
    @Test
    public void Test() throws IOException {
        Files.find(Paths.get(genPath), 999, (p, bfa) -> bfa.isRegularFile()).forEach(file -> {
            String genName = file.getFileName().toString().split("\\.")[0];
            String inputFilePath = Paths.get(this.testPath, genName + ".input.json").toString();
            if(!Files.exists(Paths.get(inputFilePath))) {
                return;
            }
            String expectedFilePath = Paths.get(this.testPath, genName + ".expected.ttl").toString();
            String outputFilePath = genName + ".input.ttl";
            try {
                GeneratorFactory.generate(genName, inputFilePath, testPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Files.find(Paths.get(testPath), 999, (p, bfa) -> bfa.isRegularFile()).forEach(f -> {
                    if (f.getFileName().toString().equals(outputFilePath)) {
                        try {
                            assertTrue(FileUtils.contentEquals(new File(expectedFilePath), new File(f.toAbsolutePath().toString())));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
