import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final String DUKE = "\nDuke:";
    private static final String USER = "\nUser:";
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) throws DukeException {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(DUKE + "Hi, what do you want from me?\n");

        Scanner scanner = new Scanner(System.in);
        String userInput;
        boolean exiting = false;

        do {
            System.out.print(USER);
            userInput = scanner.nextLine();

            Scanner userInputScanner = new Scanner(userInput);
            String operation = userInputScanner.next();

            try {
                switch (operation.toLowerCase()) {
                    case "bye":
                        exiting = true;
                        break;

                    case "list":
                        listTasks();
                        break;

                    case "done":
                        completeTask(userInputScanner.nextInt());
                        break;

                    case "todo":
                        createNewTask(userInputScanner, "ToDo");
                        break;

                    case "deadline":
                        createNewTask(userInputScanner, "Deadline");
                        break;

                    case "event":
                        createNewTask(userInputScanner, "Event");
                        break;

                    default:
                        throw new UnsupportedOperationException();
                }
            } catch (DukeException e) {
                printErrorMessage(e.getMessage());
            }
        } while (!exiting);

        System.out.println(DUKE + "Bye. Have a nice day.");
    }

    private static void listTasks() {
        System.out.println(DUKE + "\n\tTasks:");
        for (int i = 0; i < tasks.size(); i++)
            System.out.printf("\t\t%d.%s\n", i + 1, tasks.get(i));
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
    }

    private static void completeTask(int taskNum) {
        tasks.get(taskNum - 1).setDone(true);
        System.out.println(DUKE + "\n\tMarking task as completed:");
        System.out.printf("\t\t%s\n", tasks.get(taskNum - 1));
    }

    private static void createNewTask(Scanner userInputScanner, String taskType)
            throws MissingInputException {
        if (!userInputScanner.hasNext())
            throw new MissingInputException();
        else if (taskType.equalsIgnoreCase("ToDo")) {
            Task newTask = new ToDo(userInputScanner.nextLine());
            addNewTask(newTask);
        }
        else {
            if (taskType.equalsIgnoreCase("Deadline"))
                userInputScanner.useDelimiter(" /by ");
            else
                userInputScanner.useDelimiter(" /at ");

            Task newTask = new Event(userInputScanner.next(), userInputScanner.next());
            addNewTask(newTask);
        }
    }

    private static void addNewTask(Task newTask) {
        tasks.add(newTask);
        System.out.println(DUKE + "\n\tAdded:\n\t\t" + newTask);
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
    }

    private static void printErrorMessage(String message) {
        System.out.println(DUKE);
        System.out.println("\tError: " + message + ".");
    }
}
