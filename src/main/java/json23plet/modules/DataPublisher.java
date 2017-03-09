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

    /**
     * Init the module with the current file, This function called by GeneratorFactory class.
     * @param inputFilePath the current file we work on.
     * @param inputRootDirPath the input root dir as passed by the command line.
     * @param outputRootDirPath the output root dir as config in the config.json file.
     */
    static public void Init(String inputFilePath, String inputRootDirPath, String outputRootDirPath) {
        tsInputPath.set(inputFilePath);
        tsInputRootDir.set(inputRootDirPath);
        tsOutputRootDirDir.set(outputRootDirPath);
    }

    /**
     * Publish the data in the root corresponded path
     * @param dir an additional directory level to add above the file.
     * @param fileExt the extension of the file.
     * @param format the format of the file (usually TURTLE).
     */
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
        String path = (new File(inputPath.substring(beginConOut, inputPath.length()))).toString();
        if (!path.equals("") && path.indexOf(File.separator) != 0) {
            path = Paths.get(File.separator, path).toString();
        }
        if (!path.equals("")) {
            path = new File(path).getParent();
        }
        String outputPath = Paths.get(outputRootDir,path
        , dir, (new File(inputPath)).getName().replace("." + FilenameUtils.getExtension(inputPath), fileExt)).toString();
        return outputPath;
    }
}
