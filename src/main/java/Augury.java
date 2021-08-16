import java.util.ArrayList;
import java.util.Scanner;

public class Augury {
    static String VER     = "v0.2.0"; // Level-2: Add, List
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
    static ArrayList<String> taskList = new ArrayList<>();

    public static void speak(String text) {
        System.out.println("\t_________________________________");
        System.out.println("\t " + text);
        System.out.println("\t_________________________________");
    }

    public static void greet() {
        System.out.println(WELCOME);
    }

    public void handleAddTask(String task) {
        taskList.add(task);
        speak("added task: " + task);
    }

    public void handleDisplayTasks() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            res.append(Integer.toString(i + 1))
                .append(". ")
                .append(taskList.get(i))
                .append("\n\t ");
        }
        speak(res.toString());
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
            } else {
                handleAddTask(input);
            }
        }

        scan.close();
    }
}
