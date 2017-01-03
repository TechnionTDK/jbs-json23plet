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
import static json23plet.generators.GeneratorsUtils.GLOBAL_SETTING_REGEX_GEN_OUTPUTDIR;

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
//                .argName("generator class> <generator name> <input dir")
                .hasArg()
                .numberOfArgs(3)
                .optionalArg(true)
                .desc("generate the files under <input dir> recursively using the <generator name>\n" +
                        "<generator class> specify the type of the generator [-gen, -regex, -basic]\n" +
                        "(with basic option no need to specify the generator name)\n")
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
                        "-setGlobal : set global setting according to the filed name\n" +
                        "-setGenOutDir : set the \"genoutputDir\" configuration (default \"genOutput\") \n" +
                        "-setRegexGenOutDir : set the \"regexGenOutputDir\" configuration (default \"regexOutput\")\n" +
                        "-setErrorLevel : set the \"setErrorLevel\" configuration (default \"none\") might be [\"none\", \"infi\", \"stop\"]\n")
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
        String action = params[0];
        switch (action) {
            case "-gen" : String outputDir = GeneratorsUtils.getGlobalSettingProp(GLOBAL_SETTING_GEN_OUTPUTDIR);
                if ( outputDir.isEmpty())  throw new Exception("No Output Directory is configered for " + params[1]);
                GeneratorFactory.activateGenerator(params[1], params[2], outputDir);
                break;
            case "-regex" : String regexOutputDir = GeneratorsUtils.getGlobalSettingProp(GLOBAL_SETTING_REGEX_GEN_OUTPUTDIR);
                if ( regexOutputDir.isEmpty())  throw new Exception("No Output Directory is configered for " + params[1]);
                GeneratorFactory.activateRegexGenerator(params[1], params[2], regexOutputDir);
                break;
            case "-basic" : String basicOutputDir = GeneratorsUtils.getGlobalSettingProp(GLOBAL_SETTING_GEN_OUTPUTDIR);
                if ( basicOutputDir.isEmpty())  throw new Exception("No Output Directory is configered for " + params[1]);
                GeneratorFactory.activateGenerator("BasicJsonGenerator", params[1], basicOutputDir);
            default:
        }
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
        switch (action) {
            case "-addGen" : GeneratorsUtils.setNewGeneratorConfiguration(params[1], params[2], params[3]); break;
            case "-editGen" : GeneratorsUtils.setGenConfig(params[1], params[2], params[3]); break;
            case "-setGlobal" :  GeneratorsUtils.setGlobalSettingProp(params[1], params[2]); break;
            case "-setGenOutDir" :  GeneratorsUtils.setGlobalSettingProp(GLOBAL_SETTING_GEN_OUTPUTDIR, params[1]); break;
            case "-setRegexGenOutDir" : GeneratorsUtils.setGlobalSettingProp(GLOBAL_SETTING_REGEX_GEN_OUTPUTDIR, params[1]); break;
            case "-setErrorLevel" :  GeneratorsUtils.setGlobalSettingProp(GLOBAL_SETTING_ERROR_LEVEL, params[1]); break;
            default:
        }
    }
}
