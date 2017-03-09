package json23plet.modules;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yon_b on 04/01/17.
 */
public class Regex {
    String regex;

    private Regex(String regex) {
        this.regex = regex;
    }

    /**
     * Create new regex object.
     * @param regex represent the regex on java syntax.
     * @return new Regex object.
     */
    static public Regex regex(String regex) {
        return new Regex(regex);
    }

    /**
     * Check a string is match to the regex.
     * @param toMatch string to check if match.
     * @return true if match, false if not.
     */
    public boolean match(String toMatch) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(toMatch);
        return m.matches();
    }

}
