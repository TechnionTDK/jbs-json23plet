
package json23plet.ontologies;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

public class JbsOntology
    extends BaseOntology
{
    private static Paths paths;
    public static Path ontPath = (Paths.get("ontologies", "ttl", "JbsOntology" + ".ttl"));
    public static ModelFactory smodel;
    public static OntModel model = ((OntModel) ((OntModel) ModelFactory.createOntologyModel()).read(ontPath.toString()));
    public static String JBO_PREFIX = "jbo";
    public static String JBR_PREFIX = "jbr";
    public static String JBO_URI = "http://jbs.technion.ac.il/ontology/";
    public static String JBR_URI = "http://jbs.technion.ac.il/resource/";
    public static Resource JBO_C_CONTAINER = (model.getOntClass(JBO_URI + "Container"));
    public static Resource JBO_C_SEFER = (model.getOntClass(JBO_URI + "Sefer"));
    public static Resource JBO_C_SEDER = (model.getOntClass(JBO_URI + "Seder"));
    public static Resource JBO_C_PARASHA = (model.getOntClass(JBO_URI + "Parasha"));
    public static Resource JBO_C_PEREK = (model.getOntClass(JBO_URI + "Perek"));
    public static Resource JBO_C_MASECHET = (model.getOntClass(JBO_URI + "Masechet"));
    public static Resource JBO_C_CHELEK = (model.getOntClass(JBO_URI + "Chelek"));
    public static Resource JBO_C_HALACHOT = (model.getOntClass(JBO_URI + "Halachot"));
    public static Resource JBO_C_SIMAN = (model.getOntClass(JBO_URI + "Siman"));
    public static Resource JBO_C_TANACH = (model.getOntClass(JBO_URI + "Tanach"));
    public static Resource JBO_C_MACHASHAVA = (model.getOntClass(JBO_URI + "Machashava"));
    public static Resource JBO_C_MUSAR = (model.getOntClass(JBO_URI + "Musar"));
    public static Resource JBO_C_MENTION = (model.getOntClass(JBO_URI + "Mention"));
    public static Resource JBO_C_HALACHA = (model.getOntClass(JBO_URI + "Halacha"));
    public static Resource JBO_C_CHASIDUT = (model.getOntClass(JBO_URI + "Chasidut"));
    public static Resource JBO_C_LIKUTEYMOHARAN = (model.getOntClass(JBO_URI + "LikuteyMoharan"));
    public static Resource JBO_C_MISHNA = (model.getOntClass(JBO_URI + "Mishna"));
    public static Resource JBO_C_MISHNAYA = (model.getOntClass(JBO_URI + "Mishnaya"));
    public static Resource JBO_C_MISHNACONTAINER = (model.getOntClass(JBO_URI + "MishnaContainer"));
    public static Resource JBO_C_PERUSHMISHNA = (model.getOntClass(JBO_URI + "PerushMishna"));
    public static Resource JBO_C_TALMUDBAVLI = (model.getOntClass(JBO_URI + "TalmudBavli"));
    public static Resource JBO_C_AMUDBAVLI = (model.getOntClass(JBO_URI + "AmudBavli"));
    public static Resource JBO_C_BAVLICONTAINER = (model.getOntClass(JBO_URI + "BavliContainer"));
    public static Resource JBO_C_PERUSHBAVLI = (model.getOntClass(JBO_URI + "PerushBavli"));
    public static Resource JBO_C_PERUSHBAVLIRASHI = (model.getOntClass(JBO_URI + "PerushBavliRashi"));
    public static Resource JBO_C_PERUSHBAVLITOSAFOT = (model.getOntClass(JBO_URI + "PerushBavliTosafot"));
    public static Resource JBO_C_PERUSHBAVLIRASHBAM = (model.getOntClass(JBO_URI + "PerushBavliRashbam"));
    public static Resource JBO_C_PERUSHBAVLIRAN = (model.getOntClass(JBO_URI + "PerushBavliRan"));
    public static Resource JBO_C_MISHNETORAH = (model.getOntClass(JBO_URI + "MishneTorah"));
    public static Resource JBO_C_MISHNETORAHCONTAINER = (model.getOntClass(JBO_URI + "MishneTorahContainer"));
    public static Resource JBO_C_SEFERHAMITZVOT = (model.getOntClass(JBO_URI + "SeferHamitzvot"));
    public static Resource JBO_C_MITZVATASE = (model.getOntClass(JBO_URI + "MitzvatAse"));
    public static Resource JBO_C_MITZVATLOTAASE = (model.getOntClass(JBO_URI + "MitzvatLoTaase"));
    public static Resource JBO_C_HALACHAMISHNETORAH = (model.getOntClass(JBO_URI + "HalachaMishneTorah"));
    public static Resource JBO_C_PERUSHMISHNETORAH = (model.getOntClass(JBO_URI + "PerushMishneTorah"));
    public static Resource JBO_C_PERUSHPERUSH = (model.getOntClass(JBO_URI + "PerushPerush"));
    public static Resource JBO_C_PERUSHKESEFMISHNE = (model.getOntClass(JBO_URI + "PerushKesefMishne"));
    public static Resource JBO_C_PERUSHLECHEMMISHNE = (model.getOntClass(JBO_URI + "PerushLechemMishne"));
    public static Resource JBO_C_PERUSHRAABAD = (model.getOntClass(JBO_URI + "PerushRaabad"));
    public static Resource JBO_C_PERUSHMAGIDMISHNE = (model.getOntClass(JBO_URI + "PerushMagidMishne"));
    public static Resource JBO_C_SHULCHANARUCH = (model.getOntClass(JBO_URI + "ShulchanAruch"));
    public static Resource JBO_C_HALACHASHULCHANARUCH = (model.getOntClass(JBO_URI + "HalachaShulchanAruch"));
    public static Resource JBO_C_PERUSHSHULCHANARUCH = (model.getOntClass(JBO_URI + "PerushShulchanAruch"));
    public static Resource JBO_C_HARAVKUK = (model.getOntClass(JBO_URI + "HaravKuk"));
    public static Resource JBO_C_PASUK = (model.getOntClass(JBO_URI + "Pasuk"));
    public static Resource JBO_C_PERUSHTANACH = (model.getOntClass(JBO_URI + "PerushTanach"));
    public static Resource JBO_C_TANACHCONTAINER = (model.getOntClass(JBO_URI + "TanachContainer"));
    public static Resource JBO_C_PASUKTORAH = (model.getOntClass(JBO_URI + "PasukTorah"));
    public static Resource JBO_C_PASUKNEVIYIM = (model.getOntClass(JBO_URI + "PasukNeviyim"));
    public static Resource JBO_C_PASUKKETUVIM = (model.getOntClass(JBO_URI + "PasukKetuvim"));
    public static Resource JBO_C_PERUSHRASHI = (model.getOntClass(JBO_URI + "PerushRashi"));
    public static Resource JBO_C_PERUSHSIFTEYCHACHAMIM = (model.getOntClass(JBO_URI + "PerushSifteyChachamim"));
    public static Resource JBO_C_PERUSHRAMBAN = (model.getOntClass(JBO_URI + "PerushRamban"));
    public static Resource JBO_C_TARGUMYONATAN = (model.getOntClass(JBO_URI + "TargumYonatan"));
    public static Resource JBO_C_PERUSHORHACHAYIM = (model.getOntClass(JBO_URI + "PerushOrHachayim"));
    public static Resource JBO_C_PERUSHIBNEZRA = (model.getOntClass(JBO_URI + "PerushIbnEzra"));
    public static Resource JBO_C_PERUSHBAALHATURIM = (model.getOntClass(JBO_URI + "PerushBaalHaturim"));
    public static Resource JBO_C_TARGUMONKELOS = (model.getOntClass(JBO_URI + "TargumOnkelos"));
    public static Resource JBO_C_PERUSHSEFORNO = (model.getOntClass(JBO_URI + "PerushSeforno"));
    public static Resource JBO_C_PERUSHKELIYEKAR = (model.getOntClass(JBO_URI + "PerushKeliYekar"));
    public static Resource JBO_C_PERUSHDAATZEKENIM = (model.getOntClass(JBO_URI + "PerushDaatZekenim"));
    public static Resource JBO_C_MIDRASHRABA = (model.getOntClass(JBO_URI + "MidrashRaba"));
    public static Resource JBO_C_MIDRASHTANCHUMA = (model.getOntClass(JBO_URI + "MidrashTanchuma"));
    public static Resource JBO_C_PERUSHMETZUDATDAVID = (model.getOntClass(JBO_URI + "PerushMetzudatDavid"));
    public static Resource JBO_C_PERUSHMETZUDATTZIYON = (model.getOntClass(JBO_URI + "PerushMetzudatTziyon"));
    public static Resource JBO_C_PERUSHRALBAG = (model.getOntClass(JBO_URI + "PerushRalbag"));
    public static Resource JBO_C_PERUSHMALBIM = (model.getOntClass(JBO_URI + "PerushMalbim"));
    public static Resource JBO_C_PERUSHMALBIMBEURHAINYAN = (model.getOntClass(JBO_URI + "PerushMalbimBeurHainyan"));
    public static Resource JBO_C_PERUSHMALBIMBEURHAMILOT = (model.getOntClass(JBO_URI + "PerushMalbimBeurHamilot"));
    public static Resource JBO_C_SHEMONAKEVATZIM = (model.getOntClass(JBO_URI + "ShemonaKevatzim"));
    public static Resource JBO_C_OROT = (model.getOntClass(JBO_URI + "Orot"));
    public static Resource JBO_C_MESILATYESHARIM = (model.getOntClass(JBO_URI + "MesilatYesharim"));
    public static Property JBO_P_MENTIONS = (model.getOntProperty(JBO_URI + "mentions"));
    public static Property JBO_P_EXPLAINS = (model.getOntProperty(JBO_URI + "explains"));
    public static Property JBO_P_POSITION = (model.getOntProperty(JBO_URI + "position"));
    public static Property JBO_P_TEXT = (model.getOntProperty(JBO_URI + "text"));
    public static Property JBO_P_TEXTNIKUD = (model.getOntProperty(JBO_URI + "textNikud"));
    public static Property JBO_P_POSITIONINPEREK = (model.getOntProperty(JBO_URI + "positionInPerek"));
    public static Property JBO_P_POSITIONINPARASHA = (model.getOntProperty(JBO_URI + "positionInParasha"));
    public static Property JBO_P_SOURCE = (model.getOntProperty(JBO_URI + "source"));
    public static Property JBO_P_TARGET = (model.getOntProperty(JBO_URI + "target"));
    public static Property JBO_P_SPAN = (model.getOntProperty(JBO_URI + "span"));
    public static Property JBO_P_NUMOFMENTIONS = (model.getOntProperty(JBO_URI + "numOfMentions"));
    public static Property JBO_P_WITHIN = (model.getOntProperty(JBO_URI + "within"));
}
