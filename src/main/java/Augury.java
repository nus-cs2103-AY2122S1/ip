import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Augury {
    static String VER     = "v0.3.0"; // Level-3: Mark as Done
    static String WELCOME =
            "    +-------------------------------+\n" +
            "    | *                 *         * |\n" +
            "    |   (`<       augury     *      |\n" +
            "    | __/_)_______________________  |\n" +
            "    |   ||                      *   |\n" +
            "    |   ||   a task manager         |\n" +
            "    |      *             *          |\n" +
            "    |             *         "+VER+"  |\n" +
            "    +-------------------------------+";
    static TaskList taskList = new TaskList();

    public static void speak(String text) {
        System.out.println("\t_________________________________");
        System.out.println("\t " + text);
        System.out.println("\t_________________________________");
    }

    public static void greet() {
        System.out.println(WELCOME);
    }

    public void handleAddTask(String task) {
        taskList.add(new Task(task));
        speak("added task: " + task);
    }

    public void handleDisplayTasks() {
        speak(taskList.toString());
    }

    public void handleMarkAsDone(String args) {
        // clean input: split commas, trim whitespace
        String[] args_String = args.split(",");
        for (int i = 0; i < args_String.length; i++) {
            args_String[i] = args_String[i].trim();
        }
        // convert to ints and sort
        ArrayList<Integer> args_Integer = new ArrayList<>();
        for (String s : args_String) {
            args_Integer.add(Integer.parseInt(s));
        }

        speak(taskList.markAsDone(args_Integer));
    }

    public void loop() {
        Scanner scan = new Scanner(System.in);
        speak("Hello! How may I help you?");

        while (true) {
            String input = scan.nextLine().trim().toLowerCase();
            if (input.equals("bye")) {
                speak("Goodbye!");
                break;
            } else if (input.equals("list")) {
                handleDisplayTasks();
            } else if (input.length() > 4 && input.substring(0,4).equals("done")) {
                handleMarkAsDone(input.substring(5));
            } else {
                handleAddTask(input);
            }
        }

        scan.close();
    }
}
