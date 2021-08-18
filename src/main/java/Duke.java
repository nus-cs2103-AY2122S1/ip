import java.util.Scanner;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 * Current Progress: A-Enums
 *
 * Description:
 * On running the program, Duke greets the user and awaits for inputted text.
 * Program stores whatever text entered by the user and display them back
 * to the user when requested.
 *
 * @author Keith Tan
 */
public class Duke {

    private static final String horizontalLine = "____________________________________________________________";
    private static Tasklist taskList;

    /**
     * Prints out message according to desired format to user
     *
     * @param message message to bve printed
     */
    private static void printMessage(String message) {

        String finalMessage = horizontalLine
                + "\n" + message + "\n"
                + horizontalLine;
        System.out.println(finalMessage);
    }

    /**
     * Checks whether given string is an integer
     *
     * @param str string to determine whether it is an integer
     * @return boolean returns true if string is an integer and
     *         returns false otherwise
     */
    private static boolean isNumeric(String str) {

        // null or empty
        if (str == null || str.length() == 0) {
            return false;
        }

        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks whether user inputted the description for the task
     *
     * @param strArr String array containing the command
     * @param event String stating the type of task to be added
     * @return String Returns the description of the task
     * @throws MissingArgumentException throws a MissingArgumentException if no description found
     */
    private static String checkDescription(String[] strArr, String event) throws MissingArgumentException {

        if (strArr.length < 2) {
            throw new MissingArgumentException("description", event);
        } else if (strArr[1].trim().isEmpty()) {
            throw new MissingArgumentException("description", event);
        } else {
            return strArr[1];
        }

    }

    /**
     * Checks whether user inputted an appropriate integer to mark the task
     *
     * @param strArr String array containing the integer
     * @param event String stating whether task is to be marked or deleted
     * @return int Returns the task number to be marked
     * @throws MissingArgumentException throws a MissingArgumentException if no integer found
     * @throws InvalidArgumentException throws a InvalidArgumentException if no integer inputted after done command
     */
    private static int checkInteger(String[] strArr, String event) throws MissingArgumentException, InvalidArgumentException {

        if (strArr.length < 2) {
            throw new MissingArgumentException("integer", event);
        } else if (!isNumeric(strArr[1])) {
            throw new InvalidArgumentException("integer", event);
        } else {
            return Integer.parseInt(strArr[1]);
        }

    }

    public static void main(String[] args) {
        //initialize program
        String greeting = "Hello! I'm Duke"
                + "\nWhat can I do for you?";
        printMessage(greeting);
        Scanner commandScanner = new Scanner(System.in);
        String currentCommand = commandScanner.nextLine();
        String[] checkCommand = currentCommand.split(" ", 2);
        Command thisCommand = Command.changeToCommand(checkCommand[0]);
        taskList = new Tasklist();
        //awaits text
        while (!thisCommand.equals(Command.BYE)) {
            try {
                switch(thisCommand) {
                    case LIST:
                        printMessage(taskList.toString());
                        break;
                    case DONE:
                        int taskNumber = checkInteger(checkCommand, "marking of task");
                        String message = taskList.markTask(taskNumber);
                        printMessage(message);
                        break;
                    case DEADLINE:
                        printMessage(taskList.addTask(checkDescription(checkCommand, "deadline"), thisCommand));
                        break;
                    case EVENT:
                        printMessage(taskList.addTask(checkDescription(checkCommand, "event"), thisCommand));
                        break;
                    case TODO:
                        printMessage(taskList.addTask(checkDescription(checkCommand, "todo"), thisCommand));
                        break;
                    case DELETE:
                        int taskDeletedNumber = checkInteger(checkCommand, "delete");
                        String deleteMessage = taskList.deleteTask(taskDeletedNumber);
                        printMessage(deleteMessage);
                        break;
                    default:
                        throw new InvalidCommandException();
                }
            } catch (DukeException e) {
                printMessage(e.toString());
            } finally {
                currentCommand = commandScanner.nextLine();
                checkCommand = currentCommand.split(" ", 2);
                thisCommand = Command.changeToCommand(checkCommand[0]);
            }
        }
        String byeString = "Bye. Hope to see you again soon!";
        printMessage(byeString);
    }
}