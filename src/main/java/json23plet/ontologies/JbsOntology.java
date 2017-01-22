
package json23plet.ontologies;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

public class JbsOntology
    extends BaseOntology
{
    public static ModelFactory smodel;
    public static OntModel model = ((OntModel) ((OntModel) ModelFactory.createOntologyModel()).read("ontologies/ttl/JbsOntology.ttl"));
    public static String JBO_PREFIX = "jbo";
    public static String JBR_PREFIX = "jbr";
    public static String JBO_URI = "http://jbs.technion.ac.il/ontology/";
    public static String JBR_URI = "http://jbs.technion.ac.il/resource/";
    public static Resource JBO_C_TANACH = (model.getOntClass(JBO_URI + "Tanach"));
    public static Resource JBO_C_MISHNA = (model.getOntClass(JBO_URI + "Mishna"));
    public static Resource JBO_C_TALMUDBAVLI = (model.getOntClass(JBO_URI + "TalmudBavli"));
    public static Resource JBO_C_MISHNETORAH = (model.getOntClass(JBO_URI + "MishneTorah"));
    public static Resource JBO_C_SHULCHANARUCH = (model.getOntClass(JBO_URI + "ShulchanAruch"));
    public static Resource JBO_C_SIFREYHARAVKUK = (model.getOntClass(JBO_URI + "SifreyHaravKuk"));
    public static Resource JBO_C_TORAH = (model.getOntClass(JBO_URI + "Torah"));
    public static Resource JBO_C_NEVIYIM = (model.getOntClass(JBO_URI + "Neviyim"));
    public static Resource JBO_C_KETUVIM = (model.getOntClass(JBO_URI + "Ketuvim"));
    public static Resource JBO_C_PERUSHTANACH = (model.getOntClass(JBO_URI + "PerushTanach"));
    public static Resource JBO_C_SEFERBERESHIT = (model.getOntClass(JBO_URI + "SeferBereshit"));
    public static Resource JBO_C_SEFERSHEMOT = (model.getOntClass(JBO_URI + "SeferShemot"));
    public static Resource JBO_C_SEFERVAYIKRA = (model.getOntClass(JBO_URI + "SeferVayikra"));
    public static Resource JBO_C_SEFERBAMIDBAR = (model.getOntClass(JBO_URI + "SeferBamidbar"));
    public static Resource JBO_C_SEFERDEVARIM = (model.getOntClass(JBO_URI + "SeferDevarim"));
    public static Resource JBO_C_SEFERYEHOSHUA = (model.getOntClass(JBO_URI + "SeferYehoshua"));
    public static Resource JBO_C_SEFERSHOFTIM = (model.getOntClass(JBO_URI + "SeferShoftim"));
    public static Resource JBO_C_SEFERSHEMUELA = (model.getOntClass(JBO_URI + "SeferShemuelA"));
    public static Resource JBO_C_SEFERSHEMUELB = (model.getOntClass(JBO_URI + "SeferShemuelB"));
    public static Resource JBO_C_SEFERMELACHIMA = (model.getOntClass(JBO_URI + "SeferMelachimA"));
    public static Resource JBO_C_SEFERMELACHIMB = (model.getOntClass(JBO_URI + "SeferMelachimB"));
    public static Resource JBO_C_SEFERYESHAAYA = (model.getOntClass(JBO_URI + "SeferYeshaaya"));
    public static Resource JBO_C_SEFERYIRMIYA = (model.getOntClass(JBO_URI + "SeferYirmiya"));
    public static Resource JBO_C_SEFERYECHEZKEL = (model.getOntClass(JBO_URI + "SeferYechezkel"));
    public static Resource JBO_C_SEFERHOSHEA = (model.getOntClass(JBO_URI + "SeferHoshea"));
    public static Resource JBO_C_SEFERYOEL = (model.getOntClass(JBO_URI + "SeferYoel"));
    public static Resource JBO_C_SEFERAMOS = (model.getOntClass(JBO_URI + "SeferAmos"));
    public static Resource JBO_C_SEFEROVADYA = (model.getOntClass(JBO_URI + "SeferOvadya"));
    public static Resource JBO_C_SEFERYONA = (model.getOntClass(JBO_URI + "SeferYona"));
    public static Resource JBO_C_SEFERMICHA = (model.getOntClass(JBO_URI + "SeferMicha"));
    public static Resource JBO_C_SEFERNACHUM = (model.getOntClass(JBO_URI + "SeferNachum"));
    public static Resource JBO_C_SEFERCHAVAKUK = (model.getOntClass(JBO_URI + "SeferChavakuk"));
    public static Resource JBO_C_SEFERTZEFANYA = (model.getOntClass(JBO_URI + "SeferTzefanya"));
    public static Resource JBO_C_SEFERCHAGAY = (model.getOntClass(JBO_URI + "SeferChagay"));
    public static Resource JBO_C_SEFERZECHARYA = (model.getOntClass(JBO_URI + "SeferZecharya"));
    public static Resource JBO_C_SEFERMALACHI = (model.getOntClass(JBO_URI + "SeferMalachi"));
    public static Resource JBO_C_SEFERTEHILIM = (model.getOntClass(JBO_URI + "SeferTehilim"));
    public static Resource JBO_C_SEFERMISHLEY = (model.getOntClass(JBO_URI + "SeferMishley"));
    public static Resource JBO_C_SEFERIYOV = (model.getOntClass(JBO_URI + "SeferIyov"));
    public static Resource JBO_C_SEFERSHIRHASHIRIM = (model.getOntClass(JBO_URI + "SeferShirHashirim"));
    public static Resource JBO_C_SEFERRUT = (model.getOntClass(JBO_URI + "SeferRut"));
    public static Resource JBO_C_SEFEREYCHA = (model.getOntClass(JBO_URI + "SeferEycha"));
    public static Resource JBO_C_SEFERKOHELET = (model.getOntClass(JBO_URI + "SeferKohelet"));
    public static Resource JBO_C_SEFERESTER = (model.getOntClass(JBO_URI + "SeferEster"));
    public static Resource JBO_C_SEFERDANIEL = (model.getOntClass(JBO_URI + "SeferDaniel"));
    public static Resource JBO_C_SEFEREZRA = (model.getOntClass(JBO_URI + "SeferEzra"));
    public static Resource JBO_C_SEFERNECHEMYA = (model.getOntClass(JBO_URI + "SeferNechemya"));
    public static Resource JBO_C_SEFERDIVREYHAYAMIMA = (model.getOntClass(JBO_URI + "SeferDivreyHayamimA"));
    public static Resource JBO_C_SEFERDIVREYHAYAMIMB = (model.getOntClass(JBO_URI + "SeferDivreyHayamimB"));
    public static Resource JBO_C_PERUSHRASHI = (model.getOntClass(JBO_URI + "PerushRashi"));
    public static Resource JBO_C_PERUSHSIFTEYCHACHAMIN = (model.getOntClass(JBO_URI + "PerushSifteyChachamin"));
    public static Resource JBO_C_PERUSHRAMBAN = (model.getOntClass(JBO_URI + "PerushRamban"));
    public static Resource JBO_C_TARGUMYONATAN = (model.getOntClass(JBO_URI + "TargumYonatan"));
    public static Resource JBO_C_PERUSHORHACHAYIM = (model.getOntClass(JBO_URI + "PerushOrHachayim"));
    public static Resource JBO_C_PERUSHRABIAVRAHAMIBNEZRA = (model.getOntClass(JBO_URI + "PerushRabiAvrahamIbnEzra"));
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
    public static Resource JBO_C_SEFERSHEMONAKEVATZIM = (model.getOntClass(JBO_URI + "SeferShemonaKevatzim"));
    public static Property JBO_P_MENTIONS = (model.getOntProperty(JBO_URI + "mentions"));
    public static Property JBO_P_INTERPRETS = (model.getOntProperty(JBO_URI + "interprets"));
    public static Property JBO_P_CONTENT = (model.getOntProperty(JBO_URI + "content"));
    public static Property JBO_P_CONTAINER = (model.getOntProperty(JBO_URI + "container"));
    public static Property JBO_P_POSITION = (model.getOntProperty(JBO_URI + "position"));
    public static Property JBO_P_TEXT = (model.getOntProperty(JBO_URI + "text"));
    public static Property JBO_P_TEXTNIKUD = (model.getOntProperty(JBO_URI + "textNikud"));
    public static Property JBO_P_POSITIONINPEREK = (model.getOntProperty(JBO_URI + "positionInPerek"));
    public static Property JBO_P_POSITIONINPARASHA = (model.getOntProperty(JBO_URI + "positionInParasha"));
}
