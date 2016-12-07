package Generators;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.OWL;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;
import org.apache.jena.vocabulary.XSD;

/**
 * Created by ysokolik on 12/2/2016.
 */
public class KnownOntologies {
    protected static final Resource OWL_C_THING = OWL.Thing;
    protected static final Resource OWL_C_CLASS= OWL.Class;
    protected static final Property RDF_P_TYPE = RDF.type;
    protected static final Property RDFS_P_COMMENT = RDFS.comment;
    protected static final Property RDFS_P_LABEL = RDFS.label;
    protected static OntModel model = ModelFactory.createOntologyModel();


    static public void Init(String ontPath) {
        if (!ontPath.equals("")) {
            model.read(ontPath);
        }
    }
}
