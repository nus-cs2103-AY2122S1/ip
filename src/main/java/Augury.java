import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Augury {
    static String VER     = "v0.4.5"; // Level-4: ToDos, Events, Deadlines + A-Inheritance
    static String WELCOME =
            "\t+-------------------------------+\n" +
            "\t| *                 *         * |\n" +
            "\t|   (`<       augury     *      |\n" +
            "\t| __/_)_______________________  |\n" +
            "\t|   ||                      *   |\n" +
            "\t|   ||   a task manager         |\n" +
            "\t|      *             *          |\n" +
            "\t|             *         "+VER+"  |\n" +
            "\t+-------------------------------+";
    static TaskList taskList = new TaskList();

    public static void speak(String text) {
        System.out.println("\t_________________________________");
        System.out.println("\t " + text);
        System.out.println("\t_________________________________");
    }

    public static void greet() {
        System.out.println(WELCOME);
    }

    public void handleAddTask(String arg) {
        String type = arg.split(" ")[0];
        String description;
        String time;

        switch(type){
            case "todo":
                description = arg.substring(5).trim();
                speak(taskList.add(description, Task.TaskTypes.TODO));
                break;
            case "deadline":
                description = arg.split("/")[0].substring(9).trim();
                time = arg.split("/")[1].substring(3).trim();
                speak(taskList.add(description, Task.TaskTypes.DEADLINE, time));
                break;
            case "event":
                description = arg.split("/")[0].substring(6).trim();
                time = arg.split("/")[1].substring(3).trim();
                speak(taskList.add(description, Task.TaskTypes.EVENT, time));
                break;
            default:
                speak("Sorry, something went wrong.");
                break;
        }
    }

    public void handleListTasks() {
        speak(taskList.toString());
    }

    public void handleMarkAsDone(String args) {
        // clean input: split commas, trim whitespace
        String[] args_String = args.split(",");
        for (int i = 0; i < args_String.length; i++) {
            args_String[i] = args_String[i].trim();
        }
        // convert to ints
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
            if (input.equals("bye") || input.equals("exit")) {
                speak("Goodbye!");
                break;
            } else if (input.equals("list") || input.equals("ls")) {
                handleListTasks();
            } else if (input.length() > 4 && input.substring(0,4).equals("done")) {
                handleMarkAsDone(input.substring(5));
            } else {
                handleAddTask(input);
            }
        }

        scan.close();
    }
}
