import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Duke {
    static String SEPARATOR = "____________________________________________________________";
    static String PREFIX = "\t";
    static String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    static String WELCOME_MSG = "Hello from\n" + LOGO + "What can I do for you?";
    static String BYE_MSG = "Bye. Hope to see you again soon!";

    static ArrayList<String> messages = new ArrayList<String>();

    public static void main(String[] args) {
        printBanner(WELCOME_MSG.split("\n"));

        var sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            var command = sc.nextLine();
            boolean isEnd = false;
            switch (command) {
                case "list":
                    printBanner(retrieveTaskList());
                    break;
                case "bye":
                    printBanner(BYE_MSG.split("\n"));
                    isEnd = true;
                    break;
                default:
                    messages.add(command);
                    printBanner(new String[] {"added: " + command});
                    break;
            }

            if (isEnd) {
                break;
            }
        }

        sc.close();
    }

    public static String[] retrieveTaskList() {
        return IntStream.range(0, messages.size())
            .mapToObj(i -> String.format("%d. %s", i + 1, messages.get(i)))
            .toArray(String[]::new);
    }

    public static void printBanner(String[] lines) {
        System.out.println(PREFIX + SEPARATOR);
        for (String line : lines) {
            System.out.println(PREFIX + line);
        }
        System.out.println(PREFIX + SEPARATOR);
    }
}
