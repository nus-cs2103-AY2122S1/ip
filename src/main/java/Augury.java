import exceptions.*;
import tasks.Task;
import tasks.TaskFactory;
import tasks.TaskList;
import storage.Storage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Augury {
    static String VER     = "v0.7.0"; // Level-7 Save
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
    private TaskList taskList = new TaskList();
    private Storage storage;

    public Augury(String path) {
        this.storage = new Storage(path);
    }

    public void init() throws AuguryException {
        try {
            this.storage.initializeTaskList(this.taskList);
        } catch (IOException e) {
            throw new FileIOException(e.getMessage());
        }
    }

    public static void speak(String text) {
        System.out.println("\t_________________________________");
        System.out.println("\t " + text);
        System.out.println("\t_________________________________");
    }

    public void greet() {
        System.out.println(WELCOME);
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
                } else if (input.length() >= 4 && input.startsWith("done")) {
                    handleMarkAsDone(input);
                } else if (input.length() >= 6 && input.startsWith("delete")) {
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

    private void handleAddTask(String arg) throws AuguryException {
        String type = arg.split(" ")[0];

        try {
            TaskFactory tf = new TaskFactory();
            Task newTask = tf.createTask(type, arg);

            if (newTask == null) {
                throw new UnknownCommandException("Invalid command entered.");
            }
            speak(taskList.addTaskAndAnnounce(type, newTask));
            storage.saveTaskListToStorage(taskList);
        } catch (AuguryException e) {
            throw new AuguryException(e.getMessage());
        }
    }

    private void handleListTasks() {
        speak(taskList.toString());
    }

    private void handleMarkAsDone(String args) throws AuguryException {
        // check if args exist
        if (args.length() <= 5) {
            throw new InvalidActionException("Please enter the task number which you want to mark as done.");
        }

        args = args.substring(5);
        ArrayList<Integer> listOfTasks = convertCommaSeparatedStringToIntegerArrayList(args);

        for (Integer i : listOfTasks){
            if (i > taskList.size()) {
                throw new InvalidActionException("tasks.Task " + i + " does not exist, please try again");
            }
        }
        speak(taskList.markAsDone(listOfTasks));
        storage.saveTaskListToStorage(taskList);
    }

    private void handleDeleteTasks(String args) throws AuguryException {
        // check if args exist
        if (args.length() <= 7) {
            throw new InvalidActionException("Please enter the task number which you want to delete.");
        }

        args = args.substring(7);
        ArrayList<Integer> listOfTasks = convertCommaSeparatedStringToIntegerArrayList(args);

        for (Integer i : listOfTasks){
            if (i > taskList.size()) {
                throw new InvalidActionException("tasks.Task " + i + " does not exist, please try again");
            }
        }
        speak(taskList.deleteTasks(listOfTasks));
        storage.saveTaskListToStorage(taskList);
    }

    private ArrayList<Integer> convertCommaSeparatedStringToIntegerArrayList (String s) {
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
}
