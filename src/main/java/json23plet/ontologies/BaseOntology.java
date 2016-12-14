package json23plet.ontologies;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.OWL;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

/**
 * Created by yon_b on 14/12/16.
 */
public class BaseOntology {
    protected static Resource OWL_C_THING = OWL.Thing;
    protected static Resource OWL_C_CLASS = OWL.Class;
    protected static Property RDF_P_TYPE = RDF.type;
    protected static Property RDFS_P_COMMENT = RDFS.comment;
    protected static Property RDFS_P_LABEL = RDFS.label;
    protected static OntModel model = ModelFactory.createOntologyModel();
}
