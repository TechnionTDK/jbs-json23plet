package Generators;

import JSONUtils.JsonAPI;
import TripletUtils.Triplet;
import org.apache.jena.base.Sys;

/**
 * Created by yon_b on 06/12/16.
 */
public class SeferHamitzvotGenerator extends JbsBaseGenerator implements IGenerator {
    @Override
    public void generate() {
        try {
            for (JsonAPI j : JsonAPI.json().getAsArray("subjects")) {
                Triplet
                        .triplet()
                        .subject("jbr:" + j.value("uri"))
                        .predicate(RDF_P_TYPE)
                        .object(JBO_C_MITZVA);
                Triplet
                        .triplet()
                        .object("jbr:" + j.value("uri"))
                        .predicate(RDF_P_TYPE)
                        .object("jbo:" + j.value("mitzvaType"));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
