import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final int lv = 4;
    private static final String[] features = {
            "",
            "Greet, Echo, Exit",
            ", Add, List",
            ", Mark as Done",
            ", ToDos, Events, Deadlines"};
    private static boolean canExit = false;
    private static final ArrayList<Task> taskArrayList = new ArrayList<>();

    /**
     * This function takes an input string and formats it by including horizontal lines above
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
     * This function takes the user's input list and beautifies it for display.
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

    public static void main(String[] args) {
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
                    if (userInput.equals("list")) { // user inputs 'list', return all text stored
                        System.out.println(sandwich(listBeautify(taskArrayList)));
                    } else if (userInput.equals("done")) { // first input is done, check second input for integer
                        if (scanner.hasNextInt()) {
                            int taskNum = scanner.nextInt();
                            taskArrayList.get(taskNum - 1).markAsDone();
                            System.out.println(sandwich("Congratulations! You have finished this task: "
                                    + taskArrayList.get(taskNum - 1).toString()));
                        } else throw new DukeException("unspecified task to mark as done");

                    } else {
                        switch (userInput) {
                            case "todo":
                                if (scanner.hasNextLine()) {
                                    String todoName = scanner.nextLine();
                                    Task newestTodo = new ToDo(todoName);
                                    taskArrayList.add(newestTodo);
                                    System.out.println(sandwich("New todo task added:\n"
                                            + newestTodo
                                            + "\n You now have "
                                            + taskArrayList.size()
                                            + " item(s) in your task list."));
                                    break;
                                } else throw new DukeException("No task description");
                            case "deadline":
                                if (scanner.hasNextLine()) {
                                    String[] deadlineTokens = scanner.nextLine().split("\\s*/by\\s*");
                                    String deadlineName = deadlineTokens[0];
                                    String deadlineReminder = deadlineTokens[1];
                                    Task newestDeadline = new Deadline(deadlineName, deadlineReminder);
                                    taskArrayList.add(newestDeadline);
                                    System.out.println(sandwich("New deadline task added:\n"
                                            + newestDeadline
                                            + "\n You now have "
                                            + taskArrayList.size()
                                            + " item(s) in your task list."));
                                    break;
                                } else throw new DukeException("No task description");
                            case "event":
                                if (scanner.hasNextLine()) {
                                    String[] eventTokens = scanner.nextLine().split("\\s*/at\\s*");
                                    String eventName = eventTokens[0];
                                    String eventReminder = eventTokens[1];
                                    Task newestEvent = new Event(eventName, eventReminder);
                                    taskArrayList.add(newestEvent);
                                    System.out.println(sandwich("New event task added:\n"
                                            + newestEvent
                                            + "\n You now have "
                                            + taskArrayList.size()
                                            + " item(s) in your task list."));
                                    break;
                                } else throw new DukeException("No task description");
                            default:
                                throw new DukeException("Unknown Input"); // unknown input
                        }
                    }
                }
            } catch (DukeException e) {
                e.printStackTrace(); // print stack trace for e
            }
        }
    }
}
