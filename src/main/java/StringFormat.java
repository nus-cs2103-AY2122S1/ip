public class StringFormat {

    private static final String lineBreak = "    ____________________________________________________________\n";
    private static final String tab = "     ";

    // formats string argument(s) sequentially
    public static String formatString(String one, String... strings) {
        String result = lineBreak + tab + one + "\n";
        for (String s : strings) {
            result += (tab + s + "\n");
        }
        result += lineBreak;
        return result;
    }

    // formats an array of strings
    public static String formatString(String[] strings) {
        String result = lineBreak;
        for (String s : strings) {
            if (s == null) break;
            result += (tab + s + "\n");
        }
        result += lineBreak;
        return result;
    }

    // Add a tab after every newline
    public static String tabAllNewline(String str) {
        return str.replace("\n", "\n" + tab);
    }

    public static String tabAndFormat(String str) {
        return formatString(tabAllNewline(str));
    }

    public static void main(String[] args) {
        // test:
        // System.out.println(StringFormat.formatString("Hello! I'm Duke", "What can I do for you?"));
        // String[] testArr = {"hello", "it's", "me"};
        // System.out.println(StringFormat.formatString(testArr));
        TaskStorage taskStorage = new TaskStorage();
        Task test1 = new Task("Hello there");
        Task test2 = new Task("General Kenobi");
        taskStorage.add(test1);
        taskStorage.add(test2);
        String testString = tabAllNewline(taskStorage.toString());
        System.out.println(testString);
    }
}
