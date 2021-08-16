import java.util.ArrayList;
import java.util.Scanner;

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
                case "bye":
                    printBanner(BYE_MSG.split("\n"));
                    isEnd = true;
                    break;
                default:
                    printBanner(new String[] {command});
                    break;
            }

            if (isEnd) {
                break;
            }
        }

        sc.close();
    }

    public static void printBanner(String[] lines) {
        System.out.println(PREFIX + SEPARATOR);
        for (String line : lines) {
            System.out.println(PREFIX + line);
        }
        System.out.println(PREFIX + SEPARATOR);
    }
}
