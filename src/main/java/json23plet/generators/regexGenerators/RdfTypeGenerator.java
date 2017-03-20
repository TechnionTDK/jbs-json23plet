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

        registerTanachGenerators();
        registerBavliGenerators();
        registerMishneTorahGenerators();



        registerGenerator(new TypeRegEx("jbr:shmonakvatzim-.*", JBO_C_SIFREYRAVKUK));
        registerGenerator(new TypeRegEx("jbr:shmonakvatzim-.*", JBO_C_SHEMONAKEVATZIM));

        registerGenerator(new TypeRegEx("jbr:mesilatyesharim-.*", JBO_C_MESILATYESHARIM));
        registerGenerator(new TypeRegEx("jbr:mesilatyesharim-.*", JBO_C_SIFREYRAMCHAL));
    }

    private void registerMishneTorahGenerators() {
        registerGenerator(new TypeRegEx("jbr:mishnetorah-.*", JBO_C_MISHNETORAH));
        registerGenerator(new TypeRegEx("jbr:seferhamitzvot-.*", JBO_C_MISHNETORAH));

        // sefer hamitzvot
        registerGenerator(new TypeRegEx("jbr:seferhamitzvot-\\d+-\\d+", JBO_C_MITZVASEFERHAMITZVOT));
        registerGenerator(new TypeRegEx("jbr:seferhamitzvot-3-\\d+", JBO_C_MITZVATASESEFERHAMITZVOT));
        registerGenerator(new TypeRegEx("jbr:seferhamitzvot-4-\\d+", JBO_C_MITZVATLOTAASESEFERHAMITZVOT));

        // register halachot based on sefarim
        Resource[] sefarim = {JBO_C_HALACHASEFERHAMADA, JBO_C_HALACHASEFERAHAVA, JBO_C_HALACHASEFERZEMANIM, JBO_C_HALACHASEFERNASHIM,
                JBO_C_HALACHASEFERKEDUSHA, JBO_C_HALACHASEFERHAFLAA, JBO_C_HALACHASEFERZERAIM, JBO_C_HALACHASEFERAVODA, JBO_C_HALACHASEFERKORBANOT,
                JBO_C_HALACHASEFERTAHARA, JBO_C_HALACHASEFERNEZIKIN, JBO_C_HALACHASEFERKINYAN, JBO_C_HALACHASEFERMISHPATIM, JBO_C_HALACHASEFERSHOFTIM};

        for (int i = 0; i < sefarim.length; i++) {
            int seferNum = i + 1;
            registerGenerator(new TypeRegEx("jbr:mishnetorah-" + seferNum + "-\\d+-\\d+-\\d+", JBO_C_HALACHAMISHNETORAH, sefarim[i]));
        }

        // register mishne torah packages
        registerGenerator(new TypeRegEx("jbr:mishnetorah-\\d+", JBO_C_MISHNETORAHPACKAGE, JBO_C_SEFER));
        registerGenerator(new TypeRegEx("jbr:mishnetorah-\\d+-\\d+", JBO_C_MISHNETORAHPACKAGE)); // currently no JBO class
        registerGenerator(new TypeRegEx("jbr:mishnetorah-\\d+-\\d+-\\d+", JBO_C_MISHNETORAHPACKAGE, JBO_C_PEREK));

        // register mishne torah mefarshim
        registerGenerator(new TypeRegEx("jbr:mishnetorah-perush-\\d+-\\d+-\\d+-\\d+", JBO_C_PERUSHMISHNETORAH, JBO_C_PERUSHPERUSH));
        registerGenerator(new TypeRegEx("jbr:mishnetorah-kesefmishne-\\d+-\\d+-\\d+-\\d+", JBO_C_PERUSHMISHNETORAH, JBO_C_PERUSHKESEFMISHNE));
        registerGenerator(new TypeRegEx("jbr:mishnetorah-lechemmishne-\\d+-\\d+-\\d+-\\d+", JBO_C_PERUSHMISHNETORAH, JBO_C_PERUSHLECHEMMISHNE));
        registerGenerator(new TypeRegEx("jbr:mishnetorah-raabad-\\d+-\\d+-\\d+-\\d+", JBO_C_PERUSHMISHNETORAH, JBO_C_PERUSHRAABAD));
    }

    private void registerBavliGenerators() {
        registerGenerator(new TypeRegEx("jbr:bavli-.*", JBO_C_TALMUDBAVLI));

        Resource[] masachtot = {JBO_C_AMUDBERACHOT, JBO_C_AMUDSHABAT, JBO_C_AMUDERUVIN, JBO_C_AMUDPESACHIM, JBO_C_AMUDROSHHASHANA,
                JBO_C_AMUDYOMA, JBO_C_AMUDSUKA, JBO_C_AMUDBETZA, JBO_C_AMUDTAANIT, JBO_C_AMUDMEGILA,
                JBO_C_AMUDMOEDKATAN, JBO_C_AMUDCHAGIGA, JBO_C_AMUDYEVAMOT, JBO_C_AMUDKETUVOT, JBO_C_AMUDNEDARIM,
                JBO_C_AMUDNAZIR, JBO_C_AMUDSOTA, JBO_C_AMUDGITIN, JBO_C_AMUDKIDUSHIN, JBO_C_AMUDBAVAKAMA,
                JBO_C_AMUDBAVAMETZIA, JBO_C_AMUDBAVABATRA, JBO_C_AMUDSANHEDRIN, JBO_C_AMUDMAKOT, JBO_C_AMUDSHEVUOT,
                JBO_C_AMUDAVODAZARA, JBO_C_AMUDHORAYOT, JBO_C_AMUDZEVACHIM, JBO_C_AMUDMENACHOT, JBO_C_AMUDCHULIN,
                JBO_C_AMUDBECHOROT, JBO_C_AMUDARACHIN, JBO_C_AMUDTEMURA, JBO_C_AMUDKERITUT, JBO_C_AMUDMEILA,
                JBO_C_AMUDTAMID, JBO_C_AMUDNIDA};

        for (int i = 0; i < masachtot.length; i++) {
            int masechetNum = i + 1;
            registerGenerator(new TypeRegEx("jbr:bavli-" + masechetNum + "-\\d+-\\d+", JBO_C_AMUDBAVLI, masachtot[i]));
        }

        // TODO add packages
    }

    private void registerTanachGenerators() {
        registerGenerator(new TypeRegEx("jbr:tanach-.*", JBO_C_TANACH));
        registerGenerator(new TypeRegEx("jbr:tanach-\\d+-\\d+-\\d+", JBO_C_PASUK));
        registerGenerator(new TypeRegEx("jbr:tanach-[1-5]-\\d+-\\d+", JBO_C_PASUKTORAH));
        registerGenerator(new TypeRegEx("jbr:tanach-[6-9]-\\d+-\\d+|jbr:tanach-1[0-9]-\\d+-\\d+|jbr:tanach-2[0-6]-\\d+-\\d+", JBO_C_PASUKNEVIYIM));
        registerGenerator(new TypeRegEx("jbr:tanach-2[7-9]-\\d+-\\d+|jbr:tanach-3[0-9]-\\d+-\\d+", JBO_C_PASUKKETUVIM));

        registerGenerator(new TypeRegEx("jbr:tanach-\\d+", JBO_C_JBSPACKAGE, JBO_C_TANACHPACKAGE, JBO_C_SEFER));
        registerGenerator(new TypeRegEx("jbr:tanach-\\d+-\\d+", JBO_C_JBSPACKAGE, JBO_C_TANACHPACKAGE, JBO_C_PEREK));
        registerGenerator(new TypeRegEx("jbr:tanach-parasha-\\d+", JBO_C_JBSPACKAGE, JBO_C_TANACHPACKAGE, JBO_C_PARASHA));

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
        String[] mefarshim = {"rashi", "ramban", "orhachaim", "ibnezra", "baalhaturim", "onkelos", "sforno", "kliyekar",
                "daatzkenim", "metzudatdavid", "metzudattzion", "malbiminyan", "malbimmilot", "ralbag", "malbim"};
        registerGenerator(new TypeRegEx("jbr:tanach-\\D+-\\d+-\\d+-\\d+", JBO_C_PERUSHTANACH)); // \D matches non-digits
        registerGenerator(new TypeRegEx("jbr:tanach-Rashi-\\d+-\\d+-\\d+", JBO_C_PERUSHRASHI)); // \D matches non-digits
        registerGenerator(new TypeRegEx("jbr:tanach-ramban-\\d+-\\d+-\\d+", JBO_C_PERUSHRAMBAN)); // \D matches non-digits
        registerGenerator(new TypeRegEx("jbr:tanach-orhachaim-\\d+-\\d+-\\d+", JBO_C_PERUSHORHACHAYIM)); // \D matches non-digits
        registerGenerator(new TypeRegEx("jbr:tanach-ibnezra-\\d+-\\d+-\\d+", JBO_C_PERUSHRABIAVRAHAMIBNEZRA)); // \D matches non-digits
        registerGenerator(new TypeRegEx("jbr:tanach-baalhaturim-\\d+-\\d+-\\d+", JBO_C_PERUSHBAALHATURIM)); // \D matches non-digits
        registerGenerator(new TypeRegEx("jbr:tanach-onkelos-\\d+-\\d+-\\d+", JBO_C_TARGUMONKELOS)); // \D matches non-digits
        registerGenerator(new TypeRegEx("jbr:tanach-sforno-\\d+-\\d+-\\d+", JBO_C_PERUSHSEFORNO)); // \D matches non-digits
        registerGenerator(new TypeRegEx("jbr:tanach-kliyekar-\\d+-\\d+-\\d+", JBO_C_PERUSHKELIYEKAR)); // \D matches non-digits
        registerGenerator(new TypeRegEx("jbr:tanach-daatzkenim-\\d+-\\d+-\\d+", JBO_C_PERUSHDAATZEKENIM)); // \D matches non-digits
        registerGenerator(new TypeRegEx("jbr:tanach-metzudatdavid-\\d+-\\d+-\\d+", JBO_C_PERUSHMETZUDATDAVID)); // \D matches non-digits
        registerGenerator(new TypeRegEx("jbr:tanach-metzudattzion-\\d+-\\d+-\\d+", JBO_C_PERUSHMETZUDATTZIYON)); // \D matches non-digits
        registerGenerator(new TypeRegEx("jbr:tanach-malbiminyan-\\d+-\\d+-\\d+", JBO_C_PERUSHMALBIMBEURHAINYAN)); // \D matches non-digits
        registerGenerator(new TypeRegEx("jbr:tanach-malbimmilot-\\d+-\\d+-\\d+", JBO_C_PERUSHMALBIMBEURHAMILOT)); // \D matches non-digits
        registerGenerator(new TypeRegEx("jbr:tanach-ralbag-\\d+-\\d+-\\d+", JBO_C_PERUSHRALBAG)); // \D matches non-digits
        registerGenerator(new TypeRegEx("jbr:tanach-malbim-\\d+-\\d+-\\d+", JBO_C_PERUSHMALBIM)); // \D matches non-digits
        // TODO Find a more elegant approach for the above

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
        JsonValidator v = new OntologyValidator();
        v.registerValidators();
        v.validateSingleJson(Json.json());
        super._generate();
    }
}
