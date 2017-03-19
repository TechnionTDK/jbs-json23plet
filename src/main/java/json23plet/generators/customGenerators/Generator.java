package json23plet.generators.customGenerators;

import java.io.IOException;

/**
 * Created by yon_b on 19/01/17.
 */
abstract public class Generator {
    /**
     * The main function og any generator, you need to implement it on your generator.
     */
    abstract public void generate() throws IOException;

    /**
     * Define the id of the generator, this id might assign to thr output file name.
     * @return the ID of the generator.
     */
    abstract public String getID();
}
