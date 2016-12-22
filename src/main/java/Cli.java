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
import json23plet.modules.ontologyGenerator.OntologyGenerator;
import org.apache.commons.cli.*;
import static json23plet.generators.GeneratorsUtils.GLOBAL_SETTING_OUTPUTDIR;

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
                .argName("generator name> <input dir")
                .hasArg()
                .numberOfArgs(2)
                .desc("generate the ")
                .build());
        options.addOption("generateAll", "run all generators that are configered in generator.config");
        options.addOption(Option.builder("init")
                .argName("output dir")
                .hasArg()
                .desc("initializes the output location of the tool to \"output dir\"")
                .build());
        options.addOption(Option.builder("ontology")
                .argName("ontology name")
                .hasArg()
                .desc("create the appropriate ontology resources to a given ontology name")
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
                String outputDir = line.getOptionValue("init");
                init(outputDir);
            }
            if (line.hasOption("ontology")) {
                String ontologyName = line.getOptionValue("ontology");
                OntologyGenerator.generate(ontologyName);
            }
//            } else {
//                log.log(Level.SEVERE, "Missing generate options");
//                help();
//            }
            if (line.hasOption("generate")) {
                //log.log(Level.INFO, "Using cli arguments -generate " + line.getOptionValues("generate").toString());
                String[] generateOptions = line.getOptionValues("generate");
                generate(generateOptions[0], generateOptions[1]);
            }
            if (line.hasOption("generateAll")) {
                generateAll();
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
        //System.exit(0);
    }
    private void init(String outputDir) throws IOException {
        Json23plet.initJson23plet();
        GeneratorsUtils.setGlobalSettingProp(GLOBAL_SETTING_OUTPUTDIR, outputDir);
    }
    private void generate(String generatorName, String inputDir) throws Exception {
        String outputDir = GeneratorsUtils.getGlobalSettingProp(GLOBAL_SETTING_OUTPUTDIR);
        if (outputDir.isEmpty()) {
            throw new Exception("No Output Directory is configered");
        }
        GeneratorFactory.activateGenerator(generatorName, inputDir, outputDir);
    }
    private void generateAll() throws Exception {
        String outputDir = GeneratorsUtils.getGlobalSettingProp(GLOBAL_SETTING_OUTPUTDIR);
        if (outputDir.isEmpty()) {
            throw new Exception("No Output Directory is configered");
        }
        GeneratorFactory.activateAllConfigGenerators(outputDir);
    }
}
