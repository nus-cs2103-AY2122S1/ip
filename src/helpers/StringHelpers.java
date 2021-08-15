package helpers;

public class StringHelpers {
    // reference
    // https://stackoverflow.com/questions/2255500/can-i-multiply-strings-in-java-to-repeat-sequences
    public static String repeat(int count, String with) {
        return new String(new char[count]).replace("\0", with);
    }

    public static String repeat(int count) {
        return repeat(count, " ");
    }

    public static String wrap(String inner, String wrapper) {
        return wrapper + inner + wrapper;
    }

    public static String bracketWrap(String inner) {
        return String.format("[%s]", inner);
    }

    // reference
    // https://stackoverflow.com/questions/15888934/how-to-indent-a-multi-line-paragraph-being-written-to-the-console-in-java
    public static String indent(String s) {
        return s.replaceAll("(?m)^", "\t");
    }
}
