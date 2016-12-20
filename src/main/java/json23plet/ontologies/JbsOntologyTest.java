
package json23plet.ontologies;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

public class JbsOntologyTest
    extends BaseOntology
{
    public static ModelFactory smodel;
    public static OntModel model = ((OntModel) ((OntModel) ModelFactory.createOntologyModel()).read("ontologies/ttl/JbsOntologyTest.ttl"));
    public static String JBO_PREFIX = "jbo";
    public static String JBR_PREFIX = "jbr";
    public static String JBO_URI = "http://jbs.technion.ac.il/ontology/";
    public static String JBR_URI = "http://jbs.technion.ac.il/resource/";
    public static Resource JBO_C_PASUK = (model.getOntClass(JBO_URI + "Pasuk"));
    public static Resource JBO_C_PEREK = (model.getOntClass(JBO_URI + "Perek"));
    public static Resource JBO_C_SEFER = (model.getOntClass(JBO_URI + "Sefer"));
    public static Resource JBO_C_PERUSH = (model.getOntClass(JBO_URI + "Perush"));
    public static Resource JBO_C_MISHNAYA = (model.getOntClass(JBO_URI + "Mishnaya"));
    public static Resource JBO_C_MITZVA = (model.getOntClass(JBO_URI + "Mitzva"));
    public static Resource JBO_C_HALACHA = (model.getOntClass(JBO_URI + "Halacha"));
    public static Resource JBO_C_DAFGMARA = (model.getOntClass(JBO_URI + "DafGmara"));
    public static Resource JBO_C_SEDER = (model.getOntClass(JBO_URI + "Seder"));
    public static Resource JBO_C_MASECHET = (model.getOntClass(JBO_URI + "Masechet"));
    public static Resource JBO_C_MITZVATASE = (model.getOntClass(JBO_URI + "MitzvatAse"));
    public static Resource JBO_C_MITZVATLOTAASE = (model.getOntClass(JBO_URI + "MitzvatLoTaase"));
    public static Property JBO_P_MENTIONS = (model.getOntProperty(JBO_URI + "mentions"));
    public static Property JBO_P_INTERPRETS = (model.getOntProperty(JBO_URI + "interprets"));
    public static Property JBO_P_CONTENT = (model.getOntProperty(JBO_URI + "content"));
    public static Property JBO_P_CONTAINER = (model.getOntProperty(JBO_URI + "container"));
    public static Property JBO_P_POSITION = (model.getOntProperty(JBO_URI + "position"));
    public static Property JBO_P_TEXT = (model.getOntProperty(JBO_URI + "text"));
    public static Property JBO_P_POSITIONINPEREK = (model.getOntProperty(JBO_URI + "positionInPerek"));
    public static Property JBO_P_POSITIONINPARASHA = (model.getOntProperty(JBO_URI + "positionInParasha"));
}
