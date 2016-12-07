package generators;


import json23plet.modules.Json;
import json23plet.modules.Triplet;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

import static json23plet.modules.Json.json;
import static json23plet.modules.Triplet.triplet;

/**
 * Created by yon_b on 06/12/16.
 */
public class JbsBaseGenerator extends Generator {
    protected static String JBO_URI;
    protected static String JBR_URI;
    protected static final String JBR_PREFIX;
    protected static final String JBO_PREFIX;

    protected static Resource JBO_C_PASUK;
    protected static Resource JBO_C_MITZVA;
    protected static Property JBO_P_TEXT;
    protected static Property JBO_P_CONTAINER;
    protected static Property JBO_P_POSITION_IN_PEREK;
    protected static Property JBO_P_POSITION_IN_PARASHA;

    static {
        JBR_PREFIX = "jbr";
        JBO_PREFIX = "jbo";
        JBR_URI =  Generator.model.getNsPrefixURI(JBR_PREFIX);
        JBO_URI =  Generator.model.getNsPrefixURI(JBO_PREFIX);
        JBO_C_PASUK = Generator.model.getOntClass(JBO_URI + "Pasuk");
        JBO_C_MITZVA = Generator.model.getOntClass(JBO_URI + "Mitzva");
        JBO_P_TEXT = Generator.model.getOntProperty(JBO_URI + "text");
        JBO_P_CONTAINER = Generator.model.getOntProperty(JBO_URI + "container");
        JBO_P_POSITION_IN_PEREK = Generator.model.getOntProperty(JBO_URI + "positionInPerek");
        JBO_P_POSITION_IN_PARASHA = Generator.model.getOntProperty(JBO_URI + "positionInParasha");
    }

    public void generate() {
        Triplet.addNSprefix(JBO_PREFIX, JBO_URI);
        Triplet.addNSprefix(JBR_PREFIX, JBR_URI);
        for (Json j : json().getAsArray("subjects")) {
            String sub = JBR_PREFIX + ":" + j.value("uri");
            triplet()
                    .subject(sub)
                    .predicate(JBO_P_TEXT)
                    .object(j.value("text"));
            triplet()
                    .subject(sub)
                    .predicate(JBO_P_CONTAINER)
                    .object(j.value("sefer"));
            for (Json js : j.getAsArray("titles")) {
                triplet()
                        .subject(sub)
                        .predicate(RDFS_P_LABEL)
                        .object(js.value("title"));
            }
        }

    }
}
