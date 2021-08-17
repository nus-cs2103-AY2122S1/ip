import java.util.List;

public class Echoer {

    private final static String LINE_SPLIT =
            "___________________________________________________________________________";

    public static void info(String message) {
        System.out.println(LINE_SPLIT);
        System.out.println('\t' + message);
        System.out.println(LINE_SPLIT + '\n');
    }

    public static void list(List<String> messageList) {
        System.out.println(LINE_SPLIT);
        for (String message: messageList) {
            System.out.println('\t' + message);
        }
        System.out.println(LINE_SPLIT + '\n');
    }

    public static void print(String string) {
        System.out.println(string);
    }

    public static void error(String errorMessage) {
        info("☹ Error: " + errorMessage);
    }
}
