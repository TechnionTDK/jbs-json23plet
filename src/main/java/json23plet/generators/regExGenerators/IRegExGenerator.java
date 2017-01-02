package json23plet.generators.regExGenerators;

import json23plet.modules.Json;

/**
 * Created by yon_b on 02/01/17.
 */
public interface IRegExGenerator {
    public void generate(Json js);

    public boolean match(String uri);

    public String outputPath();

    public String getRegEx();

}
