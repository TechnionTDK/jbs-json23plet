package json23plet.modules;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.*;

import java.io.FileWriter;

/**
 * Created by yon_b on 28/11/16.
 */
public class Triplet {
    static private OntModel model;

    private Resource subject = null;
    private Property predicate = null;
    private RDFNode object = null;


    static public Triplet triplet() {
        return new Triplet();
    }
    static public void Init() {
        model = ModelFactory.createOntologyModel();
    }
    private void addStatement() {
        if (subject != null && predicate != null && object != null) {
            model.add(model.createStatement(subject, predicate, object));
        }
    }

    static public void addNSprefix(String prefix, String uri) {
        model.setNsPrefix(prefix,uri);
    }

    private String getUri(String uri) {
        boolean hasPrefix = uri.contains(":");
        if (!hasPrefix) {
            return "";
        }
        return model.getNsPrefixURI(uri.split(":")[0]);

    }
    private String getSuffix(String uri) {
        boolean hasPrefix = uri.contains(":");
        if (!hasPrefix) {
            return uri;
        }
        return uri.split(":")[1];
    }
    public Triplet subject(String sub) {
        subject = ResourceFactory.createResource(getUri(sub) + getSuffix(sub));
        addStatement();
        return this;
    }

    public Triplet predicate(String pre) {
        predicate = ResourceFactory.createProperty(getUri(pre) + getSuffix(pre));
        addStatement();
        return this;
    }

    public Triplet predicate(Property pre) {
        predicate = pre;
        addStatement();
        return this;
    }

    public Triplet object(String obj) {
        if (obj.contains(":") && isModelPrefix(obj.split(":")[0])) { //TODO: bug alert!!
            object = ResourceFactory.createResource(getUri(obj) + getSuffix(obj));
        } else {
            object = ResourceFactory.createPlainLiteral(obj);
        }
        addStatement();
        return this;
    }
    public Triplet object(Resource obj) {
        object = obj;
        addStatement();
        return this;
    }

    static public void Export(String path, String format) {
        try {
            model.write(new FileWriter(path), format);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    static private boolean isModelPrefix(String obj) {
        return model.getNsPrefixMap().containsKey(obj);
    }
    static public void Close() {
        model.close();
    }
}
