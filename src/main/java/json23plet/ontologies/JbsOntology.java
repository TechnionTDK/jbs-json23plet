
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
    public static String DC_PREFIX = "dc";
    public static String JBO_URI = "http://jbs.technion.ac.il/ontology/";
    public static String JBR_URI = "http://jbs.technion.ac.il/resource/";
    public static String DC_URI = "http://purl.org/dc/terms/";
    public static Resource JBO_C_TEXT = (model.getOntClass(JBO_URI + "Text"));
    public static Resource JBO_C_SECTION = (model.getOntClass(JBO_URI + "Section"));
    public static Resource JBO_C_BOOK = (model.getOntClass(JBO_URI + "Book"));
    public static Resource JBO_C_BOOKTANACH = (model.getOntClass(JBO_URI + "BookTanach"));
    public static Resource JBO_C_PERSON = (model.getOntClass(JBO_URI + "Person"));
    public static Resource JBO_C_CATEGORY = (model.getOntClass(JBO_URI + "Category"));
    public static Resource JBO_C_SEDER = (model.getOntClass(JBO_URI + "Seder"));
    public static Resource JBO_C_PARASHATORAH = (model.getOntClass(JBO_URI + "ParashaTorah"));
    public static Resource JBO_C_PEREK = (model.getOntClass(JBO_URI + "Perek"));
    public static Resource JBO_C_MASECHET = (model.getOntClass(JBO_URI + "Masechet"));
    public static Resource JBO_C_CHELEK = (model.getOntClass(JBO_URI + "Chelek"));
    public static Resource JBO_C_HALACHOT = (model.getOntClass(JBO_URI + "Halachot"));
    public static Resource JBO_C_SIMAN = (model.getOntClass(JBO_URI + "Siman"));
    public static Resource JBO_C_MENTION = (model.getOntClass(JBO_URI + "Mention"));
    public static Resource JBO_C_MISHNAYA = (model.getOntClass(JBO_URI + "Mishnaya"));
    public static Resource JBO_C_PERUSH = (model.getOntClass(JBO_URI + "Perush"));
    public static Resource JBO_C_PERUSHTANACH = (model.getOntClass(JBO_URI + "PerushTanach"));
    public static Resource JBO_C_AMUDBAVLI = (model.getOntClass(JBO_URI + "AmudBavli"));
    public static Resource JBO_C_MITZVA = (model.getOntClass(JBO_URI + "Mitzva"));
    public static Resource JBO_C_HALACHA = (model.getOntClass(JBO_URI + "Halacha"));
    public static Resource JBO_C_PASUK = (model.getOntClass(JBO_URI + "Pasuk"));
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
