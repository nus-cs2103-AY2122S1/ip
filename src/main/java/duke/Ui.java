package duke;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

/**
 * Represents the user interface which deals with interactions with the user.
 */
public class Ui {
    Scanner sc;
    private static TaskList taskList;
    private static Storage storage;

    /**
     * Represents a constructor for the Ui class where variables are initialized.
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
        String welcomeMessage = "Hello from duke :)) \n\n" + "Here is your current task list: \n" + showLine() + "\n" 
                + this.taskList.printList() + "\n" + showLine()  + "\nEnd of task list \n\n" + "How can I help you?";
        printInput(welcomeMessage);
        return welcomeMessage;
    }

    /**
     * Returns the string response to the bye command.
     * 
     * @return String response to the bye command.
     */
    public static String sayBye() {
        String byeMessage = "Bye. Have a great day!";
        printInput(byeMessage);
        return byeMessage;
    }

    /**
     * Returns a dashed line to enhance the user interface.
     * 
     * @return String representation of a dashed line.
     */
    public static String showLine() {
        String line = "-----------------------------------------------";
        printInput(line);
        return line;
    }

    /**
     * Returns a string value argument that is displayed on the screen.
     *
     * @param msg String value to be printed on the screen.
     * @return String value to be printed on the screen.
     */
    public static String printInput(String msg) {
        System.out.println(msg);
        return msg;
    }

    /**
     * Returns the string response to the task command.
     *
     * @param task Task object.
     * @return String representation of the task as well as the number of tasks in the task list.
     */
    public String taskResponse(Task task) {
        String taskMessage = "Got it. I've added this task:" + System.lineSeparator() + task.toString() 
                + System.lineSeparator() + "Now you have " + taskList.getSize() + " tasks in the list.";
        printInput(taskMessage);
        return taskMessage;
    }

    /**
     * Returns the string response to the done command.
     *
     * @param task Task object.
     * @return String representation of the task that is done by the user.
     */
    public String doneResponse(Task task) {
        String doneMessage = "Nice! I've marked this task as done:" + System.lineSeparator() + task.toString();
        printInput(doneMessage);
        return doneMessage;
    }

    /**
     * Returns the string response to the delete command.
     *
     * @param task Task object.
     * @return String representation of the task that is deleted by the user as well as the number of tasks 
     * remaining in the task list.
     */
    public String deleteResponse(Task task) {
        String deleteMessage = "Noted. I've removed this task:" + System.lineSeparator() + task.toString() 
                + System.lineSeparator() + "Now you have " + taskList.getSize() + " tasks in the list.";
        printInput(deleteMessage);
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
     * Returns the string response to the find command.
     *
     * @param wordToFind Task class.
     * @param taskList TaskList object.
     * @return String representation of the tasks in the task list that contain the wordToFind string.
     */
    public String findResponse(String wordToFind, TaskList taskList) {
        int count = 1;
        String response = "";
        
        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.getTask(i);
            String[] partsBeforeSlash = task.toString().split("\\(", 2);
            String[] furtherBreakdownIntoParts = partsBeforeSlash[0].split(" ");
            
            if (isWordPresent(furtherBreakdownIntoParts, wordToFind)) {
                response += count + ". " + task + "\n"; 
                System.lineSeparator();
                count++;
            }
        }
        
        assert count > 0: "count should be greater than 0";
        
        if (count == 1) {
            String message = printInput("Sorry, there are no matching tasks in your list :/");
            return message;
        }
        
        String findMessage = "Here are the matching tasks in your list:" + System.lineSeparator() + response;
        printInput(findMessage);
        return findMessage;
    }
}
