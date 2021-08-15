public class StringFormat {

    private static final String lineBreak = "    ____________________________________________________________\n";
    private static final String tab = "     ";

    public static String formatString(String one, String... strings) {
        String result = lineBreak + tab + one + "\n";
        for (String s : strings) {
            result += (tab + s + "\n");
        }
        result += lineBreak;
        return result;
    }

    public static String formatString(String[] strings) {
        String result = lineBreak;
        for (String s : strings) {
            if (s == null) break;
            result += (tab + s + "\n");
        }
        result += lineBreak;
        return result;
    }

    public static void main(String[] args) {
        // test:
        // System.out.println(StringFormat.formatString("Hello! I'm Duke", "What can I do for you?"));
        // String[] testArr = {"hello", "it's", "me"};
        // System.out.println(StringFormat.formatString(testArr));
    }
}
