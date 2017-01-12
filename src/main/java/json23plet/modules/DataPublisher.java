package json23plet.modules;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.nio.file.Paths;

/**
 * Created by yon_b on 03/01/17.
 */
public class DataPublisher {
    static ThreadLocal<String>  tsInputPath = new ThreadLocal<>();
    static ThreadLocal<String> tsInputRootDir = new ThreadLocal<>();
    static ThreadLocal<String> tsOutputRootDirDir = new ThreadLocal<>();

    static public void Init(String inputFilePath, String inputRootDirPath, String outputRootDirPath) {
        tsInputPath.set(inputFilePath);
        tsInputRootDir.set(inputRootDirPath);
        tsOutputRootDirDir.set(outputRootDirPath);
    }


    static public void publish(String dir, String fileExt, String format) {
        String currentOutputPath = getOutputPath(dir, fileExt);
        new File(new File(currentOutputPath).getParent()).mkdirs();
        Triplet.Export(currentOutputPath, format);

    }

    static private String getOutputPath(String dir, String fileExt) {
        String inputPath = tsInputPath.get();
        String inputRootDir = tsInputRootDir.get();
        String outputRootDir = tsOutputRootDirDir.get();
        int beginConOut = inputPath.indexOf(inputRootDir) + inputRootDir.length();
        String outputPath = Paths.get(outputRootDir, (new File(inputPath.substring(beginConOut, inputPath.length()))).getParent()
        , dir, (new File(inputPath)).getName().replace("." + FilenameUtils.getExtension(inputPath), fileExt)).toString();
        return outputPath;
    }
}
