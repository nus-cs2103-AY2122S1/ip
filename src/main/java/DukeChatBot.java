import java.util.List;

public class DukeChatBot {

    private final static String LINE_SPLIT =
            "___________________________________________________________________________";

    public void info(String message) {
        System.out.println(LINE_SPLIT);
        System.out.println('\t' + message);
        System.out.println(LINE_SPLIT + '\n');
    }

    public void list(List<String> messageList) {
        System.out.println(LINE_SPLIT);
        for (String message: messageList) {
            System.out.println('\t' + message);
        }
        System.out.println(LINE_SPLIT + '\n');
    }

    public void print(String string) {
        System.out.println(string);
    }

    public void error(String errorMessage) {
        info("☹ Error: " + errorMessage);
    }
}
