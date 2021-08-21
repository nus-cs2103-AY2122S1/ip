import exceptions.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Augury {
    static String VER     = "v0.6.0"; // Level-6 Delete + A-Exceptions + A-Enums
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

    public void handleAddTask(String arg) throws AuguryException {
        String type = arg.split(" ")[0];
        String description;
        String time;

        try {
            TaskFactory tf = new TaskFactory();
            Task newTask = tf.createTask(type, arg);

            if (newTask == null) {
                throw new UnknownCommandException("Invalid command entered.");
            }
            speak(taskList.addTask(type, newTask));
        } catch (AuguryException e) {
            throw new AuguryException(e.getMessage());
        }
    }

    public void handleListTasks() {
        speak(taskList.toString());
    }

    public void handleMarkAsDone(String args) throws InvalidActionException {
        // check if args exist
        if (args.length() <= 5) {
            throw new InvalidActionException("Please enter the task number which you want to mark as done.");
        }

        args = args.substring(5);
        ArrayList<Integer> listOfTasks = commaSeparatedStringToIntegerArrayList(args);

        for (Integer i : listOfTasks){
            if (i > taskList.size()) {
                throw new InvalidActionException("Task " + i + " does not exist, please try again");
            }
        }
        speak(taskList.markAsDone(listOfTasks));
    }

    public void handleDeleteTasks(String args) throws InvalidActionException {
        // check if args exist
        if (args.length() <= 7) {
            throw new InvalidActionException("Please enter the task number which you want to delete.");
        }

        args = args.substring(7);
        ArrayList<Integer> listOfTasks = commaSeparatedStringToIntegerArrayList(args);

        for (Integer i : listOfTasks){
            if (i > taskList.size()) {
                throw new InvalidActionException("Task " + i + " does not exist, please try again");
            }
        }
        speak(taskList.deleteTasks(listOfTasks));
    }

    private ArrayList<Integer> commaSeparatedStringToIntegerArrayList (String s) {
        // split comma-separated string to String[]
        String[] s_String = s.split(",");
        for (int i = 0; i < s_String.length; i++) {
            // trim whitespace on each item
            s_String[i] = s_String[i].trim();
        }
        // convert String[] to ArrayList<Integer>
        ArrayList<Integer> s_Integer = new ArrayList<>();
        for (String ss : s_String) {
            s_Integer.add(Integer.parseInt(ss));
        }
        return s_Integer;
    }

    public void loop() throws AuguryException {
        Scanner scan = new Scanner(System.in);
        speak("Hello! How may I help you?");

        while (true) {
            String input = scan.nextLine().trim().toLowerCase();
            try {
                if (input.equals("bye") || input.equals("exit") || input.equals("quit")) {
                    speak("The readiness is all.");
                    break;
                } else if (input.equals("list") || input.equals("ls")) {
                    handleListTasks();
                } else if (input.length() >= 4 && input.substring(0, 4).equals("done")) {
                    handleMarkAsDone(input);
                } else if (input.length() >= 6 && input.substring(0, 6).equals("delete")) {
                    handleDeleteTasks(input);
                } else {
                    handleAddTask(input);
                }
            } catch (AuguryException e) {
                speak(e.getMessage() + "\n\t Please try again.");
            }
        }

        scan.close();
    }
}
