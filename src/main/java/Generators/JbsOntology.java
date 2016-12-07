package Generators;

import TripletUtils.Triplet;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

/**
 * Created by yon_b on 03/12/16.
 */
public class JbsOntology extends KnownOntologies {


    protected static String JBO_URI;
    protected static String JBR_URI;
    protected static final String JBR_PREFIX = "jbr";
    protected static final String JBO_PREFIX = "jbo";

    protected static Resource JBO_C_PASUK;
    protected static Resource JBO_C_MITZVA;
    protected static Property JBO_P_TEXT;
    protected static Property JBO_P_CONTAINER;
    protected static Property JBO_P_POSITION_IN_PEREK;
    protected static Property JBO_P_POSITION_IN_PARASHA;


    public JbsOntology() {
        JBR_URI =  super.model.getNsPrefixURI(JBR_PREFIX);
        JBO_URI =  super.model.getNsPrefixURI(JBO_PREFIX);

        JBO_C_PASUK = super.model.getOntClass(JBO_URI + "Pasuk");
        JBO_C_MITZVA = super.model.getOntClass(JBO_URI + "Mitzva");
        JBO_P_TEXT = super.model.getOntProperty(JBO_URI + "text");
        JBO_P_CONTAINER = super.model.getOntProperty(JBO_URI + "container");
        JBO_P_POSITION_IN_PEREK = super.model.getOntProperty(JBO_URI + "positionInPerek");
        JBO_P_POSITION_IN_PARASHA = super.model.getOntProperty(JBO_URI + "positionInParasha");


        Triplet.addNSprefix(JBO_PREFIX, JBO_URI);
        Triplet.addNSprefix(JBR_PREFIX, JBR_URI);
    }
}
