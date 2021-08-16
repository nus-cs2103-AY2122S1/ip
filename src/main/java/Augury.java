import exceptions.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Augury {
    static String VER     = "v0.5.0"; // Level-5: Handle Errors + A-Exceptions
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

        switch(type){
            case "todo":
                if (arg.length() <= 5) {
                    // command doesn't have any arguments
                    throw new InvalidTaskCreationException("Description of todo cannot be empty!");
                }
                description = arg.substring(5).trim();
                speak(taskList.add(description, Task.TaskTypes.TODO));
                break;
            case "deadline":
                if (arg.length() <= 9) {
                    // command doesn't have any arguments
                    throw new InvalidTaskCreationException("Description of deadline cannot be empty!");
                }
                if (arg.split("/by ").length < 2 || arg.split("/by ")[1].length() <= 4) {
                    // command doesn't include '/by' portion
                    throw new InvalidTaskCreationException("Deadline task must include time! (using /by time)");
                }
                description = arg.substring(9).split("/by ")[0].trim();
                time = arg.split("/by ")[1].trim();
                speak(taskList.add(description, Task.TaskTypes.DEADLINE, time));
                break;
            case "event":
                if (arg.length() <= 6) {
                    // command doesn't have any arguments
                    throw new InvalidTaskCreationException("Description of event cannot be empty!");
                }
                if (arg.split("/at ").length < 2 || arg.split("/at ")[1].length() <= 4) {
                    // command doesn't include '/at' portion
                    throw new InvalidTaskCreationException("Event task must include time! (using /at time)");
                }
                description = arg.substring(6).split("/at ")[0].trim();
                time = arg.split("/at ")[1].trim();
                speak(taskList.add(description, Task.TaskTypes.EVENT, time));
                break;
            default:
                throw new UnknownCommandException("I don't understand that command.");
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

        // clean input: split commas, trim whitespace
        args = args.substring(5);
        String[] args_String = args.split(",");
        for (int i = 0; i < args_String.length; i++) {
            args_String[i] = args_String[i].trim();
        }
        // convert to ints
        ArrayList<Integer> args_Integer = new ArrayList<>();
        for (String s : args_String) {
            args_Integer.add(Integer.parseInt(s));
            if (Integer.parseInt(s) > taskList.size()) {
                throw new InvalidActionException("Task " + s + " does not exist, please try again");
            }
        }

        speak(taskList.markAsDone(args_Integer));
    }

    public void loop() throws AuguryException {
        Scanner scan = new Scanner(System.in);
        speak("Hello! How may I help you?");

        while (true) {
            String input = scan.nextLine().trim().toLowerCase();
            try {
                if (input.equals("bye") || input.equals("exit") || input.equals("quit")) {
                    speak("Goodbye!");
                    break;
                } else if (input.equals("list") || input.equals("ls")) {
                    handleListTasks();
                } else if (input.substring(0, 4).equals("done")) {
                    handleMarkAsDone(input);
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
