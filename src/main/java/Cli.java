/**
 * Created by Yaakov on 20/12/2016.
 */

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import json23plet.generators.GeneratorsUtils;
import json23plet.modules.GeneratorFactory;
import json23plet.generators.ontologyGenerator.OntologyGenerator;
import org.apache.commons.cli.*;

import static json23plet.generators.GeneratorsUtils.GLOBAL_SETTING_ERROR_LEVEL;
import static json23plet.generators.GeneratorsUtils.GLOBAL_SETTING_GEN_INPUT_DIR;
import static json23plet.generators.GeneratorsUtils.GLOBAL_SETTING_GEN_OUTPUTDIR;

public class Cli {
    private static final Logger log = Logger.getLogger(Cli.class.getName());
    private String[] args = null;
    private Options options = new Options();

    static {
        GeneratorsUtils init = new GeneratorsUtils();
    }

    public Cli(String[] args) {
        this.args = args;
        options.addOption("help", "print this message");
        options.addOption(Option.builder("generate")
                .hasArg()
                .numberOfArgs(3)
                .optionalArg(true)
                .desc("generate the files under <input dir> recursively using the <generator name>\n" +
                        "for the basic generator don't specify \"basic\" as <generator name>")
                .build());
        options.addOption("generateAll", "run all generators that are configered in generator.config");
        options.addOption("init", "initializes the structure of the project");
        options.addOption(Option.builder("ontology")
                .hasArg()
                .desc("create the appropriate ontology resources to a given ontology name")
                .build());
        options.addOption(Option.builder("config")
                .hasArg()
                .numberOfArgs(4)
                .optionalArg(true)
                .desc("add or edit configuration for generator or global setting\n" +
                        "specify\n" +
                        "1. outputDir=myOutputPath\n" +
                        "2. errorLevel=myErrorLevel\n" +
                        "3. genName=myGenName inputPath=myInput active=status\n"
                )
                .build());
    }

    public void parse() {
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine line = parser.parse(options, args);
            if (line.hasOption("help")) {
                help();
            }
            if (line.hasOption("init")) {
                init();
                System.out.println("Initialization of json23plet has finished successfully");
            }
            if (line.hasOption("ontology")) {
                String ontologyName = line.getOptionValue("ontology");
                OntologyGenerator.generate(ontologyName);
                System.out.println("Creating ontology has finished successfully");
                System.out.println("Pay attention that after updating an existing ontology you might need to update the related generators");
            }
            if (line.hasOption("generate")) {
                String[] generateOptions = line.getOptionValues("generate");
                generate(generateOptions);
                System.out.println("json23plet has successfully generated the ttl files");
            }
            if (line.hasOption("generateAll")) {
                generateAll();
            }
            if (line.hasOption("config")) {
                String[] configOptions = line.getOptionValues("config");
                config(configOptions);
            }
        }
        catch (ParseException e) {
            log.log(Level.SEVERE, "Parsing failed.  Reason: " + e.getMessage());
            e.printStackTrace();
            help();
        }
        catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
            e.printStackTrace();
        }
    }
    private void help() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("json23plet", options);
    }
    private void init() throws IOException {
        initJson23plet();
    }
    private void generate(String[] params) throws Exception {
        String gen = params[0];
        if (gen.equals("basic")) {
            gen = "BasicJsonGenerator";
        }
        String inputDir = GeneratorsUtils.getGlobalSettingProp(GLOBAL_SETTING_GEN_INPUT_DIR);
        if (inputDir.equals("") && params.length == 1) {
            throw new Exception("No input dir specified");
        }
        if (inputDir.equals("")) {
            inputDir = params[1];
        }
        GeneratorFactory.generate(gen, inputDir, GeneratorsUtils.getGlobalSettingProp(GLOBAL_SETTING_GEN_OUTPUTDIR));
    }
    private void generateAll() throws Exception {
        String outputDir = GeneratorsUtils.getGlobalSettingProp(GLOBAL_SETTING_GEN_OUTPUTDIR);
        if (outputDir.isEmpty()) {
            throw new Exception("No Output Directory is configered");
        }
        GeneratorFactory.activateAllConfigGenerators(outputDir);
    }

    private void config(String[] params) throws IOException {
        String action = params[0];
        String param = action.split("=")[0];
        String val = action.split("=")[1];
        switch (param) {
            case "outputDir":
                GeneratorsUtils.setGlobalSettingProp(GLOBAL_SETTING_GEN_OUTPUTDIR, val);
                System.out.println("output directory has been set to " + val);
                break;
            case "errorLevel":
                GeneratorsUtils.setGlobalSettingProp(GLOBAL_SETTING_ERROR_LEVEL, val);
                System.out.println("error level has been set to " + val);
                break;
            case "genInputDir":
                GeneratorsUtils.setGlobalSettingProp(GLOBAL_SETTING_GEN_INPUT_DIR, val);
                System.out.println("input dir has been set to " + val);
                break;
            case "genName":
                setGenConfig(params);
                System.out.println("added configuration successfully");
                break;
            default: System.out.println("no such configuration available\ncheck README.md again");
        }
    }
    private void setGenConfig(String[] params) throws IOException {
        if (params.length < 2) {
            return;
        }
        String genName = params[0].split("=")[1];
        String inputPath ="";
        String active = "";
        if (params[1].split("=")[0].equals("inputPath")) {
            inputPath = params[1].split("=")[1];
        } else if (params[1].split("=")[0].equals("active")) {
            active = params[1].split("=")[1];
        }
        if (params.length == 3) {
            if (params[2].split("=")[0].equals("inputPath")) {
                inputPath = params[2].split("=")[1];
            } else if (params[2].split("=")[0].equals("active")) {
                active = params[2].split("=")[1];
            }
        }
        GeneratorsUtils.setGenConfig(genName, inputPath, active);
    }

    public static void initJson23plet() {
        String[] files = {Paths.get("ontologies", "json").toString(), Paths.get("ontologies", "ttl").toString(), Paths.get("src", "test", "testsFiles").toString()};
        for (String file : files) {
            if (!Files.exists(Paths.get(file))) {
                System.out.println("Creating directory " + file);
                new File(file).mkdirs();
            }
        }
    }
}
