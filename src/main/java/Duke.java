import java.util.Scanner;

/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 * Current Progress: Level 2. Mark As Done
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
    private static Task[] taskList;

    /**
     * Stores text entered by user into list
     *
     * @param newText new text entered by user to be stored
     */
    private static void addText(String newText) {

        if (taskList == null) {
            taskList = new Task[1];
            Task newTask = new Task(newText);
            taskList[0] = newTask;

        } else {

            Task[] newTaskList = new Task[taskList.length + 1];
            for (int i = 0; i < taskList.length; i++) {
                newTaskList[0] = taskList[0];
            }
            Task newTask = new Task(newText);
            newTaskList[taskList.length] = newTask;
            taskList = newTaskList;

        }

    }

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
     * Creates list string according to current list size
     *
     * @return String returns current text list as a string
     */
    private static String createListString() {

        if (taskList == null) {
            return "Enter Text First!";
        } else {
            int lens = taskList.length;
            String listString = "";
            for (int i = 0; i < lens; i++ ) {
                String nextItem = String.format("%d.%s", (i + 1), taskList[i].toString());
                listString = listString + nextItem + "\n";
            }
            return listString;
        }
    }

    /**
     * Marks the designated tasks as complete
     *
     * @param taskNumber task to be marked as complete
     */
    private static void markTask(int taskNumber) {

        if (taskList == null) {

            printMessage("Add something to tasklist first");
        } else if ((taskNumber - 1) > taskList.length) {

            printMessage("No such task exists");

        } else {

            taskList[taskNumber - 1].completeTask();
            String markTaskMessage = "Nice! I've marked this task as done: \n"
                    + taskList[taskNumber - 1].toString();
            printMessage(markTaskMessage);

        }

    }

    public static void main(String[] args) {
        //initialize program
        String currentCommand = "greeting";
        String greeting = "Hello! I'm Duke"
                + "\nWhat can I do for you?";
        printMessage(greeting);
        Scanner commandScanner = new Scanner(System.in);

        //awaits text
        while (!currentCommand.equals("bye")) {
            currentCommand = commandScanner.nextLine();
            String[] checkCommand = currentCommand.split(" ");
            if (checkCommand.length == 0) {
                printMessage("Please enter some text");
            } else if (currentCommand.equals("")) {
                printMessage("Please enter some text");
            } else {
                if (checkCommand[0].equals("bye")) {
                    String byeString = "Bye. Hope to see you again soon!";
                    printMessage(byeString);
                } else if (checkCommand[0].equals("list")) {
                    printMessage(createListString());
                } else if (checkCommand[0].equals("done")) {
                    try {
                        int taskNumber = Integer.parseInt(checkCommand[1]);
                        markTask(taskNumber);

                    } catch (Exception e) {
                        printMessage("Please enter an Integer after the commmand 'done'");
                    }
                } else {
                    //add task to task list
                    addText(currentCommand);
                    String newText = "added: " + currentCommand;
                    printMessage(newText);
                }

            }

        }
    }
}