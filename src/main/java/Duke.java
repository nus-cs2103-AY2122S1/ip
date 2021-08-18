import java.util.Scanner;

public class Duke {
    // Constant Strings
    private static final String HorizontalLine = "____________________________________________________________";
    private static final String LIndent = "    ";
    private static String nameOfRobot = "Duke";
    private static final String ExitWord = "bye";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // print logo
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // print welcome words
        PrintWithIndent(HorizontalLine);
        PrintWithIndent("Hello! I'm " + nameOfRobot);
        PrintWithIndent("What can I do for you?");
        PrintWithIndent(HorizontalLine);

        // Echo loop till exit word is entered
        for (;;) {
            String userInput = sc.nextLine();
            if (userInput.equals(ExitWord)) {
                PrintWithIndent(HorizontalLine);
                PrintWithIndent("Bye. Hope to see you again soon!");
                PrintWithIndent(HorizontalLine);
                break;
            } else {
                PrintWithIndent(HorizontalLine);
                PrintWithIndent(userInput);
                PrintWithIndent(HorizontalLine);
            }
        }
    }

    public static void PrintWithIndent(String s) {
        System.out.println(LIndent + s);
    }
}
