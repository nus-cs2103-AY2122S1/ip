package duke;

import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents the user interface which deals with interactions with the user
 */
public class Ui {
    Scanner sc;
    private static TaskList taskList;
    private static Storage storage;

    /**
     * Constructor for the Ui class where variables are initialized
     *
     * @param taskList TaskList that stores the tasks.
     * @param storage Storage that deals with loading tasks from the file and saving tasks in the file.
     */
    public Ui(TaskList taskList, Storage storage) {
        sc = new Scanner(System.in);
        this.taskList = taskList;
        this.storage = storage;
    }
    
    /**
     * Returns the command typed by the user.
     * If the user doesn't type any command and clicks enter, an empty string is returned.
     *
     * @return String representation of the command entered by the user.
     */
    public String getCommand() {
        if (sc.hasNextLine()) {
            String str = sc.nextLine();
            return str;
        }
        return "";
    }

    /**
     * Returns the appropriate welcome message along with the existing task list when duke starts up.
     * 
     * @return String representation of welcome commands and the current task list.
     */
    public String showWelcome() {
        String welcomeMessage = "Hello from duke! \n\n" + "Here is your current task list: \n" + this.taskList.printList() 
                + "\nEnd of task list \n\n" + "Hope you are doing well. How can I help you?";
        System.out.println(welcomeMessage);
        return welcomeMessage;
    }

    /**
     * Class method that returns the string response to the bye command.
     * 
     * @return String response to the bye command.
     */
    public static String sayBye() {
        String byeMessage = "Bye. Have a great day!";
        System.out.println(byeMessage);
        return byeMessage;
    }

    /**
     * Class method that returns a dashed line to enhance user interface.
     * 
     * @return String representation of a dashed line.
     */
    public static String showLine() {
        String line = "-----------------------------------------------";
        System.out.println(line);
        return line;
    }

    /**
     * Class method that returns a string value argument on the screen.
     *
     * @param msg String value to be printed on the screen.
     * @return String value to be printed on the screen.
     */
    public static String printMessage(String msg) {
        System.out.println(msg);
        return msg;
    }

    /**
     * Class method that returns the string response to the task command.
     *
     * @param task Task object.
     * @return String representation of the task as well as the number of tasks in the task list.
     */
    public static String taskResponse(Task task) {
        String taskMessage = "Got it. I've added this task:" + System.lineSeparator() + task.toString() + System.lineSeparator()
                + "Now you have " + taskList.getSize() + " tasks in the list.";
        System.out.println(taskMessage);
        return taskMessage;
    }

    /**
     * Class method that returns the string response to the done command.
     *
     * @param task Task object.
     * @return String representation of the task that is done by the user.
     */
    public static String doneResponse(Task task) {
        String doneMessage = "Nice! I've marked this task as done:" + System.lineSeparator() + task.toString();
        System.out.println(doneMessage);
        return doneMessage;
    }

    /**
     * Class method that returns the string response to the delete command.
     *
     * @param task Task object.
     * @return String representation of the task that is deleted by the user as well as the number of tasks 
     * remaining in the task list.
     */
    public static String deleteResponse(Task task) {
        String deleteMessage = "Noted. I've removed this task:" + System.lineSeparator() + task.toString() + System.lineSeparator()
                + "Now you have " + taskList.getSize() + " tasks in the list.";
        System.out.println(deleteMessage);
        return deleteMessage;
    }

    private static boolean isWordPresent(String[] furtherBreakdownIntoParts, String wordToFind) {
        for (int j = 0; j < furtherBreakdownIntoParts.length; j++) {
            if (wordToFind.equals(furtherBreakdownIntoParts[j].trim())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Class method that returns the string response to the find command.
     *
     * @param wordToFind Task class.
     * @param taskList TaskList object.
     * @return String representation of the tasks in the task list that contain the wordToFind string.
     */
    public static String findResponse(String wordToFind, TaskList taskList) {
        int count = 1;
        String response = "";
        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.getTask(i);
            String[] partsBeforeSlash = task.toString().split("\\(", 2);
            String[] furtherBreakdownIntoParts = partsBeforeSlash[0].split(" ");
            if (isWordPresent(furtherBreakdownIntoParts, wordToFind)) {
                response += count + ". " + task.toString() + "\n"; 
                System.lineSeparator();
                count++;
            }
        }
        
        if (count == 1) {
            String message = Ui.printMessage("Sorry, there are no matching tasks in your list :/");
            return message;
        }
        
        String findMessage = "Here are the matching tasks in your list:" + System.lineSeparator() + response;
        System.out.println(findMessage);
        return findMessage;
    }
}
