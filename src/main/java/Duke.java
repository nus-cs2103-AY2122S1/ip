import java.util.Scanner;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 * Current Progress: Level 4. ToDos, Events, Deadlines
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

    public static void main(String[] args) {
        //initialize program
        String greeting = "Hello! I'm Duke"
                + "\nWhat can I do for you?";
        printMessage(greeting);
        Scanner commandScanner = new Scanner(System.in);
        String currentCommand = commandScanner.nextLine();
        taskList = new Tasklist();
        //awaits text
        while (!currentCommand.equals("bye")) {
            String[] checkCommand = currentCommand.split(" ", 2);
            if (checkCommand.length == 0) {
                printMessage("Please enter some text");
            } else if (currentCommand.equals("")) {
                printMessage("Please enter some text");
            } else {
                try {
                    switch(checkCommand[0]) {
                        case "list":
                            printMessage(taskList.toString());
                            break;
                        case "done":
                            int taskNumber = Integer.parseInt(checkCommand[1]);
                            String message = taskList.markTask(taskNumber);
                            printMessage(message);
                            break;
                        case "deadline":
                            printMessage(taskList.addTask(checkCommand[1], "deadline"));
                            break;
                        case "event":
                            printMessage(taskList.addTask(checkCommand[1], "event"));
                            break;
                        case "todo":
                            printMessage(taskList.addTask(checkCommand[1], "todo"));
                            break;
                        default:
                            printMessage("Invalid Command");
                            break;
                    }
                } catch (Exception e) {
                    printMessage("Please enter an Integer after the commmand 'done'");
                } finally {
                    currentCommand = commandScanner.nextLine();
                }
            }
        }
        String byeString = "Bye. Hope to see you again soon!";
        printMessage(byeString);
    }
}