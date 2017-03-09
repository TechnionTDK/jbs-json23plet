package json23plet.modules;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.*;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Triplet component of json23plet
 * Wraps Apache Jena API for clean and convenient generating triplet usage.
 */
public class Triplet {
    static private ThreadLocal<OntModel> model = new ThreadLocal<>();

    private Resource subject = null;
    private Property predicate = null;
    private RDFNode object = null;

    /**
     * Creates and returns an new object of Triplet.
     *
     * @return New object of Triplet.
     */
    static public Triplet triplet() {
        return new Triplet();
    }

    /**
     * Initialize the current apache jena model.
     * this function load all the ontologies in the
     * src/main/java/json23plet/ontologies directory.
     * This function called by GeneratorFactory class before loading the current generator.
     */
    static public void Init() {
        model.set(ModelFactory.createOntologyModel());
        try {
            Files.find(Paths.get("src", "main", "java", "json23plet", "ontologies"), 999, (p, bfa) -> bfa.isRegularFile()).forEach(file -> {
                try {
                    Class genClass = Class.forName("json23plet.ontologies." + file.getFileName().toString().replace(".java",""));
                    Constructor ctor = genClass.getConstructor();
                    Object instance = ctor.newInstance(null);

                    List<Field> perfixes =  Arrays.stream(genClass.getDeclaredFields())
                            .filter(f -> f.getName().contains("PREFIX"))
                            .collect(Collectors.toList());

                    List<Field> uris =  Arrays.stream(genClass.getDeclaredFields())
                            .filter(f -> f.getName().contains("URI"))
                            .collect(Collectors.toList());

                    for (Field f : perfixes) {
                        String uri = uris
                                .stream()
                                .filter(u -> u.getName()
                                        .split("_")[0]
                                        .equals(f.getName().split("_")[0]))
                                .collect(Collectors.toList())
                                .get(0)
                                .get(new Object())
                                .toString();
                        addNSprefix(f.get(new Object()).toString(), uri);
                    }

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void addStatement() {
        if (subject != null && predicate != null && object != null) {
            model.get().add(model.get().createStatement(subject, predicate, object));
        }
    }

    /**
     * Add namespace prefix to the current model.
     * @param prefix the prefix to the namespace (e.g jbo).
     * @param uri the actual uri prefix.
     */
    static public void addNSprefix(String prefix, String uri) {
        model.get().setNsPrefix(prefix,uri);
    }

    private String getUri(String uri) {
        boolean hasPrefix = uri.contains(":");
        if (!hasPrefix) {
            return "";
        }
        return model.get().getNsPrefixURI(uri.split(":")[0]);

    }
    private String getSuffix(String uri) {
        boolean hasPrefix = uri.contains(":");
        if (!hasPrefix) {
            return uri;
        }
        return uri.split(":")[1];
    }
    /**
     * Adds a subject to the triplet.
     *
     * @param sub The triplet's subject
     * @return The triplet after adding the subject.
     */
    public Triplet subject(String sub) {
        subject = ResourceFactory.createResource(getUri(sub) + getSuffix(sub));
        addStatement();
        return this;
    }

    /** Adds a predicate to the triplet.
    *
    * @param pre The triplet's predicate
    * @return The triplet after adding the predicate.
    */
    public Triplet predicate(String pre) {
        predicate = ResourceFactory.createProperty(getUri(pre) + getSuffix(pre));
        addStatement();
        return this;
    }
    /** Adds a predicate to the triplet.
     *
     * @param pre The triplet's predicate
     * @return The triplet after adding the predicate.
     */
    public Triplet predicate(Property pre) {
        predicate = pre;
        addStatement();
        return this;
    }

    /** Adds na object to the triplet.
     *
     * @param obj The triplet's object
     * @return The triplet after adding the object.
     */
    public Triplet object(String obj) {
        if (obj.contains(":") && isModelPrefix(obj.split(":")[0])) {
            object = ResourceFactory.createResource(getUri(obj) + getSuffix(obj));
        } else {
            object = ResourceFactory.createPlainLiteral(obj);
        }
        addStatement();
        return this;
    }

    /** Adds an object to the triplet.
     *
     * @param obj The triplet's object
     * @return The triplet after adding the object.
     */
    public Triplet object(Resource obj) {
        object = obj;
        addStatement();
        return this;
    }

    /**
     * Export the data to file in path.
     * @param path the path to the output file.
     * @param format the format of the file (usually TURTLE).
     */
    static public void Export(String path, String format) {
        try {
            FileWriter file = new FileWriter(path);
            model.get().write(file, format);
            file.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    static private boolean isModelPrefix(String obj) {
        return model.get().getNsPrefixMap().containsKey(obj);
    }

    /**
     * Clean the current model.
     * called by GeneratorFactory
     */
    static public void Close() {
        model.get().close();
    }
}
