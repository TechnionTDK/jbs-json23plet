package Generators;

import JSONUtils.JsonAPI;
import TripletUtils.Triplet;

/**
 * Created by yon_b on 03/12/16.
 */
public class OntologyGenerator implements IGenerator{
    @Override
    public void generate() {
        for (JsonAPI j : JsonAPI.json().getAsArray("prefixes")) {
            Triplet.addNSprefix(j.value("prefix"), j.value("uri"));
        }
        for (JsonAPI j : JsonAPI.json().getAsArray("metadata")) {
            Triplet
                    .triplet()
                    .subject(j.value("subject"))
                    .predicate(j.value("predicate"))
                    .object(j.value("object"));
        }
    }
}
