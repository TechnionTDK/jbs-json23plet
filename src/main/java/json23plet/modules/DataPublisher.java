package json23plet.modules;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.nio.file.Paths;

/**
 * Created by yon_b on 03/01/17.
 */
public class DataPublisher {
    static String inputPath;
    static String inputRootDir;
    static String outputRootDir;

    static public void Init(String inputFilePath, String inputRootDirPath, String outputRootDirPath) {
        inputPath = inputFilePath;
        inputRootDir = inputRootDirPath;
        outputRootDir = outputRootDirPath;
    }


    static public void publish(String fileExt, String format) {
        String currentOutputPath = getOutputPath(fileExt);
        new File(new File(currentOutputPath).getParent()).mkdirs();
        Triplet.Export(currentOutputPath, format);

    }

    static private String getOutputPath(String fileExt) {
        int beginConOut = inputPath.indexOf(inputRootDir) + inputRootDir.length();
        String outputPath = Paths.get(outputRootDir, inputPath.substring
                (beginConOut, inputPath.length()).replace("." + FilenameUtils.getExtension(inputPath), fileExt)).toString();
        return outputPath;
    }
}
