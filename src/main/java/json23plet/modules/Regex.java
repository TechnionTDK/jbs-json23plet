package json23plet.modules;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yon_b on 04/01/17.
 */
public class Regex {
    String regex;

    private Regex() {
        regex = "";
    }

    static public Regex regex() {
        return new Regex();
    }

    public Regex sequence(String seq) {
        regex += "(" + seq + ")";
        return this;
//        regex += wrap("(", seq);
    }
    public Regex onOf(String onOf) {
        regex += "([" + onOf + "])";
        return this;
//        regex += wrap("(", wrap("[", onOf));
    }
    public Regex exclude(String exclude) {
        regex += "([^" + exclude + "])";
        return this;
//        regex += wrap("(", wrap("[", "^" + exclude));
    }
    public Regex repeat(String seq, int num) {
        regex += "(" + seq + "{" + Integer.toString(num) + "})";
        return this;
//        regex += wrap("(", seq + wrap("{", (Integer.toString(num))));
    }
    public Regex repeat(String seq, String quan) throws Exception {
        if (!quan.equals("*") || !quan.equals("+") || !quan.equals("?")) throw new Exception("Invalid quantifier");
        regex += "(" + seq + quan + ")";
        return this;
//        regex += wrap("(", seq + quan);
    }
    public Regex all() {
        regex += "(.*)";
        return this;
    }
    public Regex repeat(String seq, int min, int max) {
        regex += "(" + seq + "{" + Integer.toString(min) + "," + Integer.toString(max) + "})";
        return this;
//        regex += wrap("(", seq + wrap("{", Integer.toString(min) + "," + Integer.toString(max)));
    }
    public Regex intersection(String seq1, String seq2) {
        regex += "([" + seq1 + "&&" + "[" + seq2 + "]])";
        return this;
//        regex += wrap("(", wrap("[", seq1 + "&&" + wrap("[", seq2)));
    }
    public Regex union(String seq1, String seq2) {
        regex += "([" + seq1 + "[" + seq2 + "]])";
        return this;
    }
    public Regex range(String char1, String char2) throws Exception {
        if (char1.length() > 1 || char2.length() > 1 || char1.compareTo(char2) < 0) throw new Exception("Invalid range");
        regex += "([" + char1 + "-" + char2 + "])";
        return this;
    }
    public Regex range(int num1, int num2) throws Exception {
        if (num1 > num2) throw new Exception("Invalid range");
        regex += "([" + Integer.toString(num1) + "-" + Integer.toString(num2) + "])";
        return this;
    }
    public String toRegexString() {
        return regex;
    }
    public boolean match(String toMatch) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(toMatch);
        return m.find();
    }



//    static private String wrap(String warpChar, String toWarp) {
//        return warpChar + toWarp + warpChar;
//    }
}
