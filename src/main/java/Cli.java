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
//                .argName("generator name> <input dir")
                .hasArg()
                .numberOfArgs(3)
                .optionalArg(true)
                .desc("generate the files under <input dir> recursively using the <generator name>\n" +
                        "for the basic generator don't specify \"basic\" as <generator name>")
                .build());
        options.addOption("generateAll", "run all generators that are configered in generator.config");
        options.addOption(Option.builder("init")
                .hasArg()
                .desc("initializes the structure of the project")
                .build());
        options.addOption(Option.builder("ontology")
//                .argName("ontology name")
                .hasArg()
                .desc("create the appropriate ontology resources to a given ontology name")
                .build());
        options.addOption(Option.builder("config")
//                .argName("config option> <[config args]")
                .hasArg()
                .numberOfArgs(4)
                .optionalArg(true)
                .desc("add or edit configuration for generator or global setting\n" +
                        "<config option> might be :\n" +
                        "-addGen : add new genrator configuration\n" +
                        "-editGen : edit specific configuration\n" +
                        "-setGlobal : set global setting according to the filed name\n")
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
                Json23plet.showMassege = true;
            }
//            } else {
//                log.log(Level.SEVERE, "Missing generate options");
//                help();
//            }
            if (line.hasOption("generate")) {
                //log.log(Level.INFO, "Using cli arguments -generate " + line.getOptionValues("generate").toString());
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
        //System.exit(0);
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
        Json23plet.showMassege = true;
    }
    private void generateAll() throws Exception {
        String outputDir = GeneratorsUtils.getGlobalSettingProp(GLOBAL_SETTING_GEN_OUTPUTDIR);
        if (outputDir.isEmpty()) {
            throw new Exception("No Output Directory is configered");
        }
        GeneratorFactory.activateAllConfigGenerators(outputDir);
        Json23plet.showMassege = true;
    }

    private void config(String[] params) {
        String action = params[0];
        switch (action) {
            case "-addGen" : GeneratorsUtils.setNewGeneratorConfiguration(params[1], params[2], params[3]); break;
            case "-editGen" : GeneratorsUtils.setGenConfig(params[1], params[2], params[3]); break;
            case "-setGlobal" :  GeneratorsUtils.setGlobalSettingProp(params[1], params[2]); break;
            default:
        }
    }
}
