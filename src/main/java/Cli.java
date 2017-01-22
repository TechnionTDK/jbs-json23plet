/**
 * Created by Yaakov on 20/12/2016.
 */
/**
 * Created by Yaakov on 20/12/2016.
 */

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import json23plet.generators.GeneratorsUtils;
import json23plet.modules.GeneratorFactory;
import json23plet.generators.ontologyGenerator.OntologyGenerator;
import org.apache.commons.cli.*;

import static json23plet.generators.GeneratorsUtils.GLOBAL_SETTING_ERROR_LEVEL;
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
            }
            if (line.hasOption("ontology")) {
                String ontologyName = line.getOptionValue("ontology");
                OntologyGenerator.generate(ontologyName);
            }
            if (line.hasOption("generate")) {
                String[] generateOptions = line.getOptionValues("generate");
                generate(generateOptions);
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
            help();
        }
        catch (Exception e) {
            log.log(Level.SEVERE, e.getMessage());
        }
    }
    private void help() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("json23plet", options);
    }
    private void init() throws IOException {
        Json23plet.initJson23plet();
    }
    private void generate(String[] params) throws Exception {
        String gen = params[0];
        if (gen.equals("basic")) {
            gen = "BasicJsonGenerator";
        }
        GeneratorFactory.generate(gen, params[1], GeneratorsUtils.getGlobalSettingProp(GLOBAL_SETTING_GEN_OUTPUTDIR));
    }
    private void generateAll() throws Exception {
        String outputDir = GeneratorsUtils.getGlobalSettingProp(GLOBAL_SETTING_GEN_OUTPUTDIR);
        if (outputDir.isEmpty()) {
            throw new Exception("No Output Directory is configered");
        }
        GeneratorFactory.activateAllConfigGenerators(outputDir);
    }

    private void config(String[] params) {
        String action = params[0];
        String param = action.split("=")[0];
        String val = action.split("=")[1];
        switch (param) {
            case "outputDir": GeneratorsUtils.setGlobalSettingProp(GLOBAL_SETTING_GEN_OUTPUTDIR, val); break;
            case "errorLevel": GeneratorsUtils.setGlobalSettingProp(GLOBAL_SETTING_ERROR_LEVEL, val); break;
            case "genName": setGenConfig(params);
            default:
        }
    }
    private void setGenConfig(String[] params) {
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
}
