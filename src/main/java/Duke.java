import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    // Constant Strings
    private static final String HorizontalLine = "____________________________________________________________";
    private static final String LIndent = "    ";
    private static String nameOfRobot = "Duke";
    private static final String ExitWord = "bye";
    private static final String ListWord = "list";


    private static ArrayList<String> dukeList;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        dukeList = new ArrayList<String>();

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

            // Check user input
            if (userInput.equals(ExitWord)) {
                // Exit the robot
                PrintWithIndent(HorizontalLine);
                PrintWithIndent("Bye. Hope to see you again soon!");
                PrintWithIndent(HorizontalLine);
                break;
            } else if (userInput.equals(ListWord)) {
                // Print the list
                PrintWithIndent(HorizontalLine);
                PrintList();
                PrintWithIndent(HorizontalLine);
            } else{
                // Echos and add user input to the list
                PrintWithIndent(HorizontalLine);
                dukeList.add(userInput);
                PrintWithIndent("added: " + userInput);
                PrintWithIndent(HorizontalLine);
            }
        }
    }

    public static void PrintWithIndent(String s) {
        System.out.println(LIndent + s);
    }

    public static void PrintList() {
        for (int i = 0; i < dukeList.size(); i++)
            PrintWithIndent(i + ". " + dukeList.get(i));
    }
}
