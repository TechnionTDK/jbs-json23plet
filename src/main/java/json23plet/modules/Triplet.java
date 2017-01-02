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
