package generators;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.OWL;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

/**
 * Created by yon_b on 07/12/16.
 */
public abstract class Generator {
    protected static Resource OWL_C_THING;
    protected static Resource OWL_C_CLASS;
    protected static Property RDF_P_TYPE;
    protected static Property RDFS_P_COMMENT;
    protected static Property RDFS_P_LABEL;
    protected static OntModel model;

    static {
        OWL_C_THING = OWL.Thing;
        OWL_C_CLASS= OWL.Class;
        RDF_P_TYPE = RDF.type;
        RDFS_P_COMMENT = RDFS.comment;
        RDFS_P_LABEL = RDFS.label;
        model = ModelFactory.createOntologyModel();
    }

    static public void Init(String ontPath) {
        if (!ontPath.equals("")) {
            model.read(ontPath);
        }
    }

    abstract public void generate();
}
