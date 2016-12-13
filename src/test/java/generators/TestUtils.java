package generators;

import json23plet.modules.Json;
import json23plet.modules.Triplet;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by yogev_henig on 10/12/2016.
 */

class TestUtils {

    static boolean isTwoEqual (String testOutput ,String testExpected) {
        File file1 = new File("src\\test\\testsOutput\\" + testOutput);
        File file2 = new File("src\\test\\testsExpected\\" + testExpected);
        try {
            return FileUtils.contentEquals(file1, file2);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
