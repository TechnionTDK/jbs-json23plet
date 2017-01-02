
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
    public static Resource JBO_C_PASUK = (model.getOntClass(JBO_URI + "pasuk"));
    public static Resource JBO_C_PEREK = (model.getOntClass(JBO_URI + "perek"));
    public static Resource JBO_C_SEFER = (model.getOntClass(JBO_URI + "sefer"));
    public static Resource JBO_C_PARASHA = (model.getOntClass(JBO_URI + "parasha"));
    public static Resource JBO_C_PERUSH = (model.getOntClass(JBO_URI + "perush"));
    public static Resource JBO_C_MISHNAYA = (model.getOntClass(JBO_URI + "mishnaya"));
    public static Resource JBO_C_MITZVA = (model.getOntClass(JBO_URI + "mitzva"));
    public static Resource JBO_C_HALACHA = (model.getOntClass(JBO_URI + "halacha"));
    public static Resource JBO_C_DAFGMARA = (model.getOntClass(JBO_URI + "dafGmara"));
    public static Resource JBO_C_SEDER = (model.getOntClass(JBO_URI + "seder"));
    public static Resource JBO_C_MASECHET = (model.getOntClass(JBO_URI + "masechet"));
    public static Resource JBO_C_MITZVATASE = (model.getOntClass(JBO_URI + "mitzvatAse"));
    public static Resource JBO_C_MITZVATLOTAASE = (model.getOntClass(JBO_URI + "mitzvatLoTaase"));
    public static Property JBO_P_MENTIONS = (model.getOntProperty(JBO_URI + "mentions"));
    public static Property JBO_P_INTERPRETS = (model.getOntProperty(JBO_URI + "interprets"));
    public static Property JBO_P_CONTENT = (model.getOntProperty(JBO_URI + "content"));
    public static Property JBO_P_CONTAINER = (model.getOntProperty(JBO_URI + "container"));
    public static Property JBO_P_POSITION = (model.getOntProperty(JBO_URI + "position"));
    public static Property JBO_P_TEXT = (model.getOntProperty(JBO_URI + "text"));
    public static Property JBO_P_POSITIONINPEREK = (model.getOntProperty(JBO_URI + "positionInPerek"));
    public static Property JBO_P_POSITIONINPARASHA = (model.getOntProperty(JBO_URI + "positionInParasha"));
}
