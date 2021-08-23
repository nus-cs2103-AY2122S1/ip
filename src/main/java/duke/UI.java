package duke;

import java.util.Scanner;

public class UI {

    private static final String lineBreak = "\t____________________________________________________________\n";
    private final Scanner sc = new Scanner(System.in);

    // formats string argument(s) sequentially
    public static String formatString(String one, String... strings) {
        String result = lineBreak + "\t" + one + "\n";
        for (String s : strings) {
            result += ("\t" + s + "\n");
        }
        result += lineBreak;
        return result;
    }

    // formats an array of strings
    public static String formatString(String[] strings) {
        String result = lineBreak;
        for (String s : strings) {
            if (s == null) break;
            result += ("\t" + s + "\n");
        }
        result += lineBreak;
        return result;
    }

    // Add a tab after every newline
    public static String tabAllNewline(String str) {
        return str.replace("\n", "\n\t");
    }

    public static String tabAndFormat(String str) {
        return formatString(tabAllNewline(str));
    }

    public void showWelcome() {
        // Starting Message
        String[] startMessage = {" ____        _        ",
                "|  _ \\ _   _| | _____ ",
                "| | | | | | | |/ / _ \\",
                "| |_| | |_| |   <  __/",
                "|____/ \\__,_|_|\\_\\___|",
                "Hello! I'm Duke",
                "What can I do for you?"};
        System.out.println(UI.formatString(startMessage));
    }

    public String readCommand() {
        return sc.nextLine().trim();
    }
}
