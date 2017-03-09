package json23plet.generators.regexGenerators;

import json23plet.modules.Json;

/**
 * Created by yon_b on 02/01/17.
 */
public interface IRegexGenerator {
    /**
     * Generate a Json object
     * @param js the Json to process
     */
    public void generate(Json js);

    /**
     * Define match rule to Json object.
     * @param js the Json to check the match
     * @return true if the Json match, false otherwise.
     */
    public boolean match(Json js);

}
