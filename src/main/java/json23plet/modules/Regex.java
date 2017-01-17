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

    static public Regex regex(String regex) {
        return new Regex(regex);
    }

    public boolean match(String toMatch) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(toMatch);
        return m.matches();
    }

}
