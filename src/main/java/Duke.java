import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Duke {
    private static final int lv = 7;
    private static final String[] features = {
            "",
            "Greet, Echo, Exit",
            ", Add, List",
            ", Mark as Done",
            ", ToDos, Events, Deadlines",
            ", Handle Errors",
            ", Delete",
            ", Save"
    };
    private static boolean canExit = false;
    private static final ArrayList<Task> taskArrayList = new ArrayList<>();
    private static final String dukeFilePath = "data/duke.txt";

    /**
     * This method takes an input string and formats it by including horizontal lines above
     * and below the input string
     *
     * @param str input string to be sandwiched
     * @return the original string sandwiched between two horizontal lines
     */
    public static String sandwich(String str) {
        return "____________________________________________________________\n"
                + str + "\n"
                + "____________________________________________________________";
    }

    /**
     * This method takes the user's input list and beautifies it for display.
     *
     * @param taskArrayList the user's input list to be beautified
     * @return the beautified string to display
     */
    public static String listBeautify(ArrayList<Task> taskArrayList) {
        StringBuilder listBeautified = new StringBuilder();
        for (int i = 0; i < taskArrayList.size(); i++) {
            listBeautified.append(i + 1)
                    .append(".")
                    .append(taskArrayList.get(i).toString());
            if (i < taskArrayList.size() - 1) { // new line except for last item
                listBeautified.append("\n");
            }
        }
        return listBeautified.toString();
    }

    /**
     * This method appends to the file instead of overwrites.
     *
     * @param filePath the path to the file to append to
     * @param textToAppend the text to append
     * @throws IOException if filePath does not exist
     */

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }


    public static void main(String[] args) throws IOException {
        // commented out logo
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        // populating featuresCombined so each level has all elements of levels before it
        StringBuilder featuresCombined = new StringBuilder();
        for (int count = 0; count <= lv; count++) {
            featuresCombined.append(features[count]);
        }

        // Welcome message
        String welcome = "Hello! I'm Duke: Level " + lv + "\n"
                + "What would you like to do today?\n"
                + "My current features are: " + featuresCombined;

        System.out.println(sandwich(welcome));

        // load the data from the hard disk for dukeFile
        File dukeFile = new File(dukeFilePath);

        // creates directory if it does not exist
        if (dukeFile.mkdir()) {
            System.out.println("folder: 'data/' has been created");
        }
        // creates file if it does not exist
        if (dukeFile.createNewFile()) {
            System.out.println("'duke.txt' has been created in the 'data/' folder ");
        }

        // Goodbye message
        String goodbye = "Thank you for using Duke: Level " + lv + "\n"
                + "See you soon!";

        // Scanner to read user inputs
        Scanner scanner = new Scanner(System.in);

        while (!canExit) {
            String userInput = scanner.next();
            try {
                if (userInput.equals("bye")) { // user inputs "bye, set canExit to true and Exit
                    canExit = true;
                    System.out.println(sandwich(goodbye));
                } else { // check first input
                    switch (userInput) {
                    case "list":  // user inputs 'list', return all text stored
                        System.out.println(sandwich(listBeautify(taskArrayList)));
                        break;
                    case "done":  // first input is done, check second input for integer
                        if (scanner.hasNextInt()) {
                            int taskNum = scanner.nextInt();
                            if (taskNum > taskArrayList.size()) {
                                throw new DukeException("No such task");
                            }
                            taskArrayList.get(taskNum - 1).markAsDone();
                            System.out.println(sandwich("Congratulations! You have finished this task: "
                                    + taskArrayList.get(taskNum - 1).toString()));
                        } else throw new DukeException("unspecified task to mark as done");
                        break;
                    case "delete":
                        if (scanner.hasNextInt()) {
                            int taskNum = scanner.nextInt();
                            if (taskNum > taskArrayList.size()) {
                                throw new DukeException("No such task");
                            }
                            System.out.println(sandwich("Got it, I have deleted this task: "
                                    + taskArrayList.get(taskNum - 1).toString()
                                    + "\nYou now have "
                                    + taskArrayList.size()
                                    + " item(s) in your task list."));
                            // actual logic of deletion
                            taskArrayList.remove(taskNum - 1);
                        } else throw new DukeException("unspecified task to delete");
                        break;
                    case "todo":
                        String todoName = scanner.nextLine();
                        if (todoName.trim().equals("")) {
                            throw new DukeException("No task description");
                        }
                        Task newestTodo = new ToDo(todoName);
                        taskArrayList.add(newestTodo);
                        System.out.println(sandwich("New todo task added:\n"
                                + newestTodo
                                + "\nYou now have "
                                + taskArrayList.size()
                                + " item(s) in your task list."));
                        break;

                    case "deadline":
                        String[] deadlineTokens = scanner.nextLine().split("\\s*/by\\s*");
                        if (deadlineTokens.length == 0) {
                            throw new DukeException("No task description");
                        } else if (deadlineTokens.length == 1) {
                            throw new DukeException("No task deadline");
                        }
                        String deadlineName = deadlineTokens[0];
                        String deadlineReminder = deadlineTokens[1];
                        Task newestDeadline = new Deadline(deadlineName, deadlineReminder);
                        taskArrayList.add(newestDeadline);
                        System.out.println(sandwich("New deadline task added:\n"
                                + newestDeadline
                                + "\nYou now have "
                                + taskArrayList.size()
                                + " item(s) in your task list."));
                        break;
                    case "event":
                        String[] eventTokens = scanner.nextLine().split("\\s*/at\\s*");
                        if (eventTokens.length == 0) {
                            throw new DukeException("No task description");
                        } else if (eventTokens.length == 1) {
                            throw new DukeException("No task duration");
                        }
                        String eventName = eventTokens[0];
                        String eventReminder = eventTokens[1];
                        Task newestEvent = new Event(eventName, eventReminder);
                        taskArrayList.add(newestEvent);
                        System.out.println(sandwich("New event task added:\n"
                                + newestEvent
                                + "\nYou now have "
                                + taskArrayList.size()
                                + " item(s) in your task list."));
                        break;
                    default:
                        throw new DukeException("Unknown Input"); // unknown input
                    }
                }
            } catch (DukeException e) {
                e.printStackTrace(); // print stack trace for e
            }
        }
    }
}
