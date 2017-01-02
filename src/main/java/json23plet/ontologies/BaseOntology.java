package json23plet.ontologies;

import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.OWL;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

/**
 * Created by yon_b on 14/12/16.
 */
public class BaseOntology {
    public static Resource OWL_C_THING = OWL.Thing;
    public static Resource OWL_C_CLASS = OWL.Class;
    public static Property RDF_P_TYPE = RDF.type;

    public static Property RDFS_P_SUB_CLASS_OF = RDFS.subClassOf;
    public static Property RDFS_P_COMMENT = RDFS.comment;
    public static Property RDFS_P_LABEL = RDFS.label;
}
