package json23plet.generators.regexGenerators;

import json23plet.JsonValidators.JsonValidator;
import json23plet.JsonValidators.OntologyValidator;
import json23plet.modules.Json;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.rdf.model.Resource;

import java.io.IOException;
import java.util.List;

import static json23plet.modules.Json.json;
import static json23plet.modules.Regex.regex;
import static json23plet.modules.Triplet.triplet;
import static json23plet.ontologies.BaseOntology.OWL_C_THING;
import static json23plet.ontologies.BaseOntology.RDF_P_TYPE;
import static json23plet.ontologies.JbsOntology.*;

/**
 * Created by yon_b on 02/01/17.
 */
public class RdfTypeGenerator extends BaseRegexGenerator {
    private class TypeRegEx implements IRegexGenerator {
        String regex;
        Resource[] classes;

        TypeRegEx(String regex, Resource... classes) {
            this.regex = regex;
            this.classes = classes;
        }

        @Override
        public void generate(Json js) {
            for (Resource c : classes)
                triplet()
                    .subject(js.value("uri"))
                    .predicate(RDF_P_TYPE)
                    .object(c);
        }

        @Override
        public boolean match(Json js) {
            return regex(regex)
                    .match(js.value("uri"));
        }
    }

    @Override
    public void registerGenerators() {

        registerGenerator(new TypeRegEx("jbr:.*", OWL_C_THING));

        registerMachashavaGenerators();
        registerMusarGenerators();
        registerTanachGenerators();
        registerMishnaGenerators();
        registerBavliGenerators();
        registerMishneTorahGenerators();
        registerShulchanAruchGenerators();
        registerChasidutGenerators();

        //registerGenerator(new TypeRegEx("jbr:mention__.*", JBO_C_MENTION)); // inserted into the generator
    }

    private void registerMusarGenerators() {
        registerGenerator(new TypeRegEx("jbr:mesilatyesharim-.*", JBO_C_MUSAR, JBO_C_MESILATYESHARIM));
    }

    private void registerMachashavaGenerators() {
        registerGenerator(new TypeRegEx("jbr:shmonakvatzim-.*", JBO_C_MACHASHAVA, JBO_C_HARAVKUK, JBO_C_SHEMONAKEVATZIM));
        registerGenerator(new TypeRegEx("jbr:derashotharan-.*", JBO_C_MACHASHAVA, JBO_C_DERASHOTHARAN));
    }

    private void registerChasidutGenerators() {
        registerGenerator(new TypeRegEx("jbr:likuteymoharan-.*", JBO_C_CHASIDUT));
        registerGenerator(new TypeRegEx("jbr:likuteymoharan-.*", JBO_C_LIKUTEYMOHARAN));
    }

    private void registerMishnaGenerators() {
        registerGenerator(new TypeRegEx("jbr:mishna-.*", JBO_C_MISHNA));
        registerGenerator(new TypeRegEx("jbr:mishna-\\d+-\\d+-\\d+-\\d+", JBO_C_MISHNAYA));
        registerGenerator(new TypeRegEx("jbr:mishna-\\D+-\\d+-\\d+-\\d+-\\d+", JBO_C_PERUSHMISHNA));
        registerGenerator(new TypeRegEx("jbr:mishna-\\d+", JBO_C_CONTAINER, JBO_C_MISHNACONTAINER, JBO_C_SEDER));
        registerGenerator(new TypeRegEx("jbr:mishna-\\d+-\\d+", JBO_C_CONTAINER, JBO_C_MISHNACONTAINER, JBO_C_MASECHET));
        registerGenerator(new TypeRegEx("jbr:mishna-\\d+-\\d+-\\d+", JBO_C_CONTAINER, JBO_C_MISHNACONTAINER, JBO_C_PEREK));
    }

    private void registerShulchanAruchGenerators() {
        registerGenerator(new TypeRegEx("jbr:shulchanaruch-.*", JBO_C_HALACHA, JBO_C_SHULCHANARUCH));
        registerGenerator(new TypeRegEx("jbr:shulchanaruch-\\d+-\\d+-\\d+", JBO_C_HALACHASHULCHANARUCH));
        registerGenerator(new TypeRegEx("jbr:shulchanaruch-\\d+", JBO_C_CONTAINER, JBO_C_CHELEK));
        registerGenerator(new TypeRegEx("jbr:shulchanaruch-halachot-\\d+-\\d+", JBO_C_CONTAINER, JBO_C_HALACHOT));
        registerGenerator(new TypeRegEx("jbr:shulchanaruch-\\d+-\\d+", JBO_C_CONTAINER, JBO_C_SIMAN));
    }

    private void registerMishneTorahGenerators() {
        registerGenerator(new TypeRegEx("jbr:mishnetorah-.*", JBO_C_HALACHA, JBO_C_MISHNETORAH));
        registerGenerator(new TypeRegEx("jbr:mishnetorah-\\d+-\\d+-\\d+-\\d+", JBO_C_HALACHAMISHNETORAH));

        // sefer hamitzvot
        registerGenerator(new TypeRegEx("jbr:seferhamitzvot-.*", JBO_C_HALACHA, JBO_C_MISHNETORAH, JBO_C_SEFERHAMITZVOT));
        registerGenerator(new TypeRegEx("jbr:seferhamitzvot-3-\\d+", JBO_C_MITZVATASE));
        registerGenerator(new TypeRegEx("jbr:seferhamitzvot-4-\\d+", JBO_C_MITZVATLOTAASE));

        // register halachot based on sefarim
//        Resource[] sefarim = {JBO_C_HALACHASEFERHAMADA, JBO_C_HALACHASEFERAHAVA, JBO_C_HALACHASEFERZEMANIM, JBO_C_HALACHASEFERNASHIM,
//                JBO_C_HALACHASEFERKEDUSHA, JBO_C_HALACHASEFERHAFLAA, JBO_C_HALACHASEFERZERAIM, JBO_C_HALACHASEFERAVODA, JBO_C_HALACHASEFERKORBANOT,
//                JBO_C_HALACHASEFERTAHARA, JBO_C_HALACHASEFERNEZIKIN, JBO_C_HALACHASEFERKINYAN, JBO_C_HALACHASEFERMISHPATIM, JBO_C_HALACHASEFERSHOFTIM};
//
//        for (int i = 0; i < sefarim.length; i++) {
//            int seferNum = i + 1;
//            registerGenerator(new TypeRegEx("jbr:mishnetorah-" + seferNum + "-\\d+-\\d+-\\d+", JBO_C_HALACHA, sefarim[i]));
//        }

        // register mishne torah containers
        registerGenerator(new TypeRegEx("jbr:mishnetorah-\\d+", JBO_C_CONTAINER, JBO_C_MISHNETORAHCONTAINER, JBO_C_SEFER));
        registerGenerator(new TypeRegEx("jbr:mishnetorah-\\d+-\\d+", JBO_C_CONTAINER, JBO_C_MISHNETORAHCONTAINER, JBO_C_HALACHOT)); // currently no JBO class
        registerGenerator(new TypeRegEx("jbr:mishnetorah-\\d+-\\d+-\\d+", JBO_C_CONTAINER, JBO_C_MISHNETORAHCONTAINER, JBO_C_PEREK));

        // register mishne torah mefarshim
        registerGenerator(new TypeRegEx("jbr:mishnetorah-perush-\\d+-\\d+-\\d+-\\d+", JBO_C_PERUSHMISHNETORAH, JBO_C_PERUSHPERUSH));
        registerGenerator(new TypeRegEx("jbr:mishnetorah-kesefmishne-\\d+-\\d+-\\d+-\\d+", JBO_C_PERUSHMISHNETORAH, JBO_C_PERUSHKESEFMISHNE));
        registerGenerator(new TypeRegEx("jbr:mishnetorah-lechemmishne-\\d+-\\d+-\\d+-\\d+", JBO_C_PERUSHMISHNETORAH, JBO_C_PERUSHLECHEMMISHNE));
        registerGenerator(new TypeRegEx("jbr:mishnetorah-raabad-\\d+-\\d+-\\d+-\\d+", JBO_C_PERUSHMISHNETORAH, JBO_C_PERUSHRAABAD));
        registerGenerator(new TypeRegEx("jbr:mishnetorah-magidmishne-\\d+-\\d+-\\d+-\\d+", JBO_C_PERUSHMISHNETORAH, JBO_C_PERUSHMAGIDMISHNE));
    }

    private void registerBavliGenerators() {
        registerGenerator(new TypeRegEx("jbr:bavli-.*", JBO_C_TALMUDBAVLI));
        registerGenerator(new TypeRegEx("jbr:bavli-\\d+-\\d+-\\d+", JBO_C_AMUDBAVLI));

        // add containers
        registerGenerator(new TypeRegEx("jbr:bavli-\\d+", JBO_C_CONTAINER, JBO_C_BAVLICONTAINER, JBO_C_MASECHET));
        registerGenerator(new TypeRegEx("jbr:bavli-perek-\\d+-\\d+", JBO_C_CONTAINER, JBO_C_BAVLICONTAINER, JBO_C_PEREK));

        // mefarshim
        registerGenerator(new TypeRegEx("jbr:bavli-\\D+-\\d+-\\d+-\\d+", JBO_C_PERUSHBAVLI)); // \D matches non-digits

        String[] mefarshim = {"rashi", "tosafot", "rashbam", "ran"};
        Resource[] resources = {JBO_C_PERUSHBAVLIRASHI, JBO_C_PERUSHBAVLITOSAFOT, JBO_C_PERUSHBAVLIRASHBAM, JBO_C_PERUSHBAVLIRAN};

        for(int index=0; index<mefarshim.length;index++)
            registerGenerator(new TypeRegEx("jbr:bavli-" + mefarshim[index] + "-\\d+-\\d+-\\d+", resources[index]));
    }

    private void registerTanachGenerators() {
        registerGenerator(new TypeRegEx("jbr:tanach-.*", JBO_C_TANACH));
        registerGenerator(new TypeRegEx("jbr:tanach-\\d+-\\d+-\\d+", JBO_C_PASUK));
        registerGenerator(new TypeRegEx("jbr:tanach-[1-5]-\\d+-\\d+", JBO_C_PASUKTORAH));
        registerGenerator(new TypeRegEx("jbr:tanach-[6-9]-\\d+-\\d+|jbr:tanach-1[0-9]-\\d+-\\d+|jbr:tanach-2[0-6]-\\d+-\\d+", JBO_C_PASUKNEVIYIM));
        registerGenerator(new TypeRegEx("jbr:tanach-2[7-9]-\\d+-\\d+|jbr:tanach-3[0-9]-\\d+-\\d+", JBO_C_PASUKKETUVIM));

        registerGenerator(new TypeRegEx("jbr:tanach-\\d+", JBO_C_CONTAINER, JBO_C_TANACHCONTAINER, JBO_C_SEFER));
        registerGenerator(new TypeRegEx("jbr:tanach-\\d+-\\d+", JBO_C_CONTAINER, JBO_C_TANACHCONTAINER, JBO_C_PEREK));
        registerGenerator(new TypeRegEx("jbr:tanach-parasha-\\d+", JBO_C_CONTAINER, JBO_C_TANACHCONTAINER, JBO_C_PARASHA));

        registerGenerator(new IRegexGenerator() { // for <book> type
            String seferPosition ="";
            /*
                In JbsOntology there is 39 different books.
                Instead of writing a rule to each book, we iterating over the JboOntology using JboOntology.model
                classes and use its jbo:position predicate to map between the json uri and the book name

                Still you can do it by the naive way e.g:
                registerGenerator(new TypeRegEx("jbr:tanach-1-.*", JBO_C_BERESHIT));
             */
            public void generate(Json js) {
                for (OntClass c : model.listClasses().toList()) { // c holds the current class such as "Bereshit"
                    if (c.hasProperty(JBO_P_POSITION) //  c has property of jbo:position
                            && c.hasSuperClass(JBO_C_PASUK) // c has superclass of jbo:Tanach
                            && !c.equals(JBO_C_PASUKTORAH) && !c.equals(JBO_C_PASUKNEVIYIM) && !c.equals(JBO_C_PASUKKETUVIM) // c is not one of them
                            && c.getProperty(JBO_P_POSITION).getObject().toString().equals(seferPosition)) { // c.position equals to the current uri position
                        triplet()
                                .subject(js.value("uri"))
                                .predicate(RDF_P_TYPE)
                                .object(c);
                        return;
                    }
                }
            }

            @Override
            public boolean match(Json json) {
                if ( json.value("uri").startsWith("jbr:tanach-")) {
                    seferPosition = json.value("uri").split("-")[1];
                }
                return regex("jbr:tanach-\\d+-\\d+-\\d+|jbr:tanach-3[0-9]-\\d+-\\d+")
                        .match(json.value("uri"));
            }
        });

        // mefarshim
        registerGenerator(new TypeRegEx("jbr:tanach-\\D+-\\d+-\\d+-\\d+", JBO_C_PERUSHTANACH)); // \D matches non-digits

        String[] mefarshim = {"rashi", "ramban", "orhachaim", "ibnezra", "baalhaturim", "onkelos", "sforno", "kliyekar",
                "yonatan", "sifteychachamim", "midrashraba", "daatzkenim", "metzudatdavid", "metzudattzion", "malbiminyan",
                "malbimmilot", "ralbag", "malbim"};
        Resource[] resources = {JBO_C_PERUSHRASHI, JBO_C_PERUSHRAMBAN, JBO_C_PERUSHORHACHAYIM, JBO_C_PERUSHIBNEZRA, JBO_C_PERUSHBAALHATURIM, JBO_C_TARGUMONKELOS,
                JBO_C_PERUSHSEFORNO, JBO_C_PERUSHKELIYEKAR, JBO_C_TARGUMYONATAN, JBO_C_PERUSHSIFTEYCHACHAMIM, JBO_C_MIDRASHRABA, JBO_C_PERUSHDAATZEKENIM,
                JBO_C_PERUSHMETZUDATDAVID, JBO_C_PERUSHMETZUDATTZIYON, JBO_C_PERUSHMALBIMBEURHAINYAN, JBO_C_PERUSHMALBIMBEURHAMILOT, JBO_C_PERUSHRALBAG, JBO_C_PERUSHMALBIM};

        for(int index=0; index<mefarshim.length;index++)
            registerGenerator(new TypeRegEx("jbr:tanach-" + mefarshim[index] + "-\\d+-\\d+-\\d+", resources[index]));
    }

    @Override
    public List<Json> getJsonsToGenerate() {
        return json().getAsArray("subjects");
    }

    @Override
    public String getID() {
        return "rdfType";
    }

    @Override
    public void generate() throws IOException {
        JsonValidator v = new OntologyValidator("JbsOntology");
        v.registerValidators();
        v.validateSingleJson(Json.json());
        super._generate();
    }
}
