import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    // Constant Strings
    private static final String HorizontalLine = "____________________________________________________________";
    private static final String LIndent = "    ";
    private static String nameOfRobot = "Duke";
    private static final String ExitWord = "bye";
    private static final String ListWord = "list";
    private static final String MarkWord = "done ";


    private static ArrayList<Task> taskList;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        taskList = new ArrayList<Task>();

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
            PrintWithIndent(HorizontalLine);
            // Check user input
            if (userInput.equals(ExitWord)) {
                PrintWithIndent("Bye. Hope to see you again soon!");
            } else if (userInput.equals(ListWord)) {
                // Print the list
                PrintList();
            } else if (IsValidMark(userInput)) {
                Task t = taskList.get(Integer.parseInt(userInput.substring(MarkWord.length())) - 1);
                t.isDone = true;
                PrintWithIndent("Nice! I've marked this task as done: ");
                PrintWithIndent("  " + t);
            }
            else{
                // Echos and add new task to the list
                taskList.add(new Task(userInput));
                PrintWithIndent("added: " + userInput);
            }
            PrintWithIndent(HorizontalLine);

            if (userInput.equals(ExitWord)) {
                // Exit the loop
                break;
            }
        }
    }

    private static void PrintWithIndent(String s) {
        System.out.println(LIndent + s);
    }

    private static void PrintList() {
        for (int i = 0; i < taskList.size(); i++)
            PrintWithIndent((i+1) + ". " + taskList.get(i));
    }

    private static boolean IsValidMark(String s) {
        if (s.length() > MarkWord.length() && s.substring(0, MarkWord.length()).equals(MarkWord)) {
            try {
                int taskIndex = Integer.parseInt(s.substring(MarkWord.length()));
                return taskIndex >= 1 && taskIndex <= taskList.size();
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }
}
