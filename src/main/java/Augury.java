import java.util.ArrayList;
import java.util.Scanner;

public class Augury {
    static String VER     = "v0.1.0"; // Level-1: Greet, Echo Exit
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

    public void loop() {
        Scanner scan = new Scanner(System.in);
        speak("Hello! How may I help you?");

        while (true) {
            String input = scan.nextLine().trim().toLowerCase();
            if (input.equals("bye")) {
                speak("Goodbye!");
                break;
            } else {
                speak(input);
            }
        }

        scan.close();
    }
}
