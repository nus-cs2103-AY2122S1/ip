import java.util.Scanner;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke: Incrementally building a Chatbot.
 *
 * Description:
 * On running the program, Duke greets the user and awaits for inputted commands:
 *   - 'todo x' -> adds a Task of x with no date/time attached
 *   - 'deadline x /by y' -> adds Deadline of x that needs to be done by y
 *   - 'event x /at y' -> adds an Event of x that starts and ends at a specific time y
 *   - 'list' -> displays current list of tasks
 *   - 'done x' -> marks Task x as done
 *   - 'delete x' -> deletes Task x from the task list
 *   - 'bye' -> exits the program
 *   - Other input patterns throw exceptions
 *
 * @author Lua Yi Da
 */

public class Side {
    private static final String LINEBREAK = "---------------------------------------------------------------------";
    private static final String GREETING = LINEBREAK + "" + "\nI'm Side, your unpaid personal assistant. " +
            "Please do less...\n" + LINEBREAK;
    private static final String GOODBYE = LINEBREAK + "\nOh, you have to go? What a pity...\n"
            + LINEBREAK;
    private enum COMMAND { TODO, DEADLINE, EVENT, LIST, DONE, DELETE, INVALID }

    private static void printLogo() {
        String logo = " ___  _____  _____   _____  \n"
                + "|  _|  | |  | ___ \\  | |__\n"
                + " \\ \\   | |  | |_| |  | |  \n"
                + "|___| _|_|_ |____/   |_|__\n";
        System.out.println(logo);
    }

    /**
     * Print response specific to adding tasks.
     *
     * @param input String representation of task to add.
     * @param tasks TaskList to be added to.
     */
    private static void echo(String input, TaskList tasks) {
        System.out.println(LINEBREAK);
        String taskQuantifier = tasks.length() == 1 ? "task..." : "tasks...";
        System.out.println("Fine, I'll add: " + input + "\nYou now have " + tasks.length() + " " + taskQuantifier);
        System.out.println(LINEBREAK);
    }

    /**
     * Generic print response.
     *
     * @param input String representation of String to format.
     */
    private static void printResponse(String input) {
        System.out.println(LINEBREAK);
        System.out.println(input);
        System.out.println(LINEBREAK);
    }

    /**
     * Helper method to isolate secondary commands like /at and /by.
     *
     * @param input String to be searched.
     * @param arg String to find.
     * @return String representing time given by usder input.
     */
    private static String findTime(String input, String arg) {
        int argIdx = input.lastIndexOf(arg);
        String output = input.substring(argIdx + arg.length());

        if (output.replaceAll("\\s", "").length() < 1) {
            return null;
        }
        return output;
    }

    /**
     * Handles the logic to add a deadline to TaskList.
     *
     * @param input String representation of user input.
     * @param taskList TaskList to be added to.
     * @throws WrongFormatException Catches incorrectly formatted input and returns error.
     */
    private static void addDeadline(String input, TaskList taskList) throws WrongFormatException {
        if (input.contains("/by") && (findTime(input, "/by") != null)) {
            String time = findTime(input, "/by");
            String description = input.replace("/by" + time, "");
            taskList.addDeadline(description, time);
            echo(new Deadline(description, time).toString(), taskList);
        } else {
            throw new WrongFormatException("deadline [task name] /by [time]");
        }
    }

    /**
     * Handles the logic to add an Event to TaskList.
     *
     * @param input String representation of user input.
     * @param taskList TaskList to be added to.
     * @throws WrongFormatException Catches incorrectly formatted input and returns error.
     */
    private static void addEvent(String input, TaskList taskList) throws WrongFormatException {
        if (input.contains("/at") && (findTime(input, "/at") != null)) {
            String time = findTime(input, "/at");
            String description = input.replace("/at" + time, "");
            taskList.addEvent(description, time);
            echo(new Event(description, time).toString(), taskList);
        } else {
            throw new WrongFormatException("event [task name] /at [time]");
        }
    }

    /**
     * Handles the logic to add a task to TaskList.
     *
     * @param input String representation of user input.
     * @param taskList TaskList to be added to.
     * @throws WrongFormatException Catches incorrectly formatted input and returns error.
     */
    private static void addTask(String input, TaskList taskList) throws WrongFormatException {
        if (input.replace("todo", "").replaceAll(" ", "").length() > 0) {
            taskList.addTask(input);
            echo(new Task(input).toString(), taskList);
        } else {
            throw new WrongFormatException("todo [task name]");
        }
    }

    /**
     * Helper method to parse string and isolate index passed in by user
     *
     * @param s String to be parsed.
     * @return Integer from parsing String s.
     */
    private static Integer tryIntParsing(String s) {
        try {
            int parsedInt = Integer.parseInt(s);
            return parsedInt;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * Handles the logic of marking a task as done.
     *
     * @param input String representation of user input.
     * @param taskList TaskList in which task is to be marked.
     * @throws TaskIndexException Catches out of bounds task indexes and returns error.
     * @throws NoIndexException Catches no index input from user and returns error.
     * @throws TooManyIndexesException Catches too many index input from user and returns error.
     */
    private static void handleDone(String input, TaskList taskList) throws TaskIndexException, NoIndexException,
            TooManyIndexesException {
        if (input.split("\\s+").length == 2) {
            int taskNum = tryIntParsing(input.split("\\s+")[1]);
            if (taskNum > taskList.length() || taskNum <= 0) {
                throw new TaskIndexException();
            } else {
                printResponse(taskList.markTaskDone(taskNum - 1));
            }
        } else if (input.split("\\s+").length == 1) {
            throw new NoIndexException();
        } else {
            throw new TooManyIndexesException("mark");
        }
    }

    /**
     * Handles the logic of deleting a task
     *
     * @param input String representation of user input.
     * @param taskList TaskList in which task is to be marked.
     * @throws DeleteIndexException Catches out of bounds task indexes and returns error.
     * @throws NoIndexException Catches no index input from user and returns error.
     * @throws TooManyIndexesException Catches too many index input from user and returns error.
     */
    private static void handleDelete(String input, TaskList taskList) throws DeleteIndexException, NoIndexException,
            TooManyIndexesException {
        if (input.split("\\s+").length == 2) {
            int taskNum = tryIntParsing(input.split("\\s+")[1]);
            if (taskNum > taskList.length() || taskNum <= 0) {
                throw new DeleteIndexException();
            } else {
                printResponse(taskList.delete(taskNum - 1));
            }
        } else if (input.split("\\s+").length == 1) {
            throw new NoIndexException();
        } else {
            throw new TooManyIndexesException("delete");
        }
    }

    /**
     * Helper to convert parsed command to COMMAND constant.
     *
     * @param command String representing command input.
     * @return COMMAND corresponding to String input.
     */
    private static COMMAND toCommand(String command) {
        switch (command) {
            case "todo":
                return COMMAND.TODO;
            case "deadline":
                return COMMAND.DEADLINE;
            case "event":
                return COMMAND.EVENT;
            case "list":
                return COMMAND.LIST;
            case "done":
                return COMMAND.DONE;
            case "delete":
                return COMMAND.DELETE;
            default:
                return COMMAND.INVALID;
        }
    }

    public static void main(String[] args) {
        printLogo();
        System.out.println(GREETING);
        TaskList tasks = new TaskList();

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        while (!userInput.equalsIgnoreCase("bye")) {
            COMMAND command = toCommand(userInput.split("\\s+")[0]);

            try {
                switch (command) {
                    case TODO:
                        addTask(userInput, tasks);
                        break;
                    case DEADLINE:
                        addDeadline(userInput, tasks);
                        break;
                    case EVENT:
                        addEvent(userInput, tasks);
                        break;
                    case LIST:
                        printResponse(tasks.toString());
                        break;
                    case DONE:
                        handleDone(userInput, tasks);
                        break;
                    case DELETE:
                        handleDelete(userInput, tasks);
                        break;
                    default:
                        throw new UnknownCommandException();
                }
            } catch (SideException e) {
                printResponse(e.getMessage());
            }
            userInput = scanner.nextLine();
        }

        System.out.println(GOODBYE);
    }
}
