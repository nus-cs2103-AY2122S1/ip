package duke;
import java.util.Scanner;

/**
 * Class that handles all user interaction.
 * Responsible for outputs as well as reading inputs from users.
 */
public class Ui {
    private static final Scanner userInput = new Scanner(System.in);
    private static String lineBreaker = "____________________________________________________________";
    private static String greeting = "Hello! I'm Duke\n" + "What can I do for you?";
    private static String farewell = "Bye. Hope to see you again soon!";
    private static String listMessage = "Here are the tasks in your list:";
    private static String doneMessage = "Nice! I've marked this task as done:";
    private static String addTaskMessage = "Got it. I've added this task:";
    private static String deleteTaskMessage = "Noted. I've removed this task:";

    /**
     * Prints a message indicating file loading error.
     */
    public void showLoadingError() {
        System.out.println("Can't load saved file");
    }

    /**
     * Returns a string which is the user's command.
     *
     * @return users command in String form.
     */
    public String getInstruction() {
        return userInput.nextLine();
    }

    /**
     * Prints a line for separation.
     */
    public  void printLineBreak () {
        System.out.println(lineBreaker);
    }

    /**
     * Prints the content of the task list in a form where users can see.
     *
     * @param taskList task list to be printed.
     */
    public void printArrayList (TaskList taskList) {
        System.out.println(listMessage);
        for(int i = 0; i < taskList.getSize(); i++) {
            System.out.println(String.valueOf(i+1) + "." + taskList.get(i).toString());
        }
        printLineBreak();
    }

    /**
     * Prints a greeting for the user on startup.
     */
    public void greet(){
        System.out.println(greeting);
        printLineBreak();
    }

    /**
     * Prints a farewell message for the user when 'bye' command is inputted.
     */
    public void sayFarewell() {
        printLineBreak();
        System.out.println(farewell);
        printLineBreak();
    }

    /**
     * Prints a notifications which informs the user
     * on which task is completed based on their command.
     *
     * @param task task that is completed.
     */
    public void completeTaskMessage (Task task) {
        System.out.println(doneMessage + "\n" + task.toString());
        printLineBreak();
    }

    /**
     * Prints a notifcation which informs the user
     * on which task is deleted based on their command.
     *
     * @param task task that is deleted.
     * @param size size of the task list.
     */
    public void deleteTaskMessage (Task task, int size) {
        System.out.println(deleteTaskMessage);
        System.out.println("  " + task.toString());
        taskCounterMessage(size);
        printLineBreak();
    }

    /**
     * Prints a notifcation which informs the user
     * on which task is added based on their command.
     *
     * @param task task that is added.
     * @param size size of the task list.
     */
    public void addedTaskMessage (Task task, int size) {
        System.out.println(addTaskMessage);
        System.out.println("  " + task.toString());
        taskCounterMessage(size);
        printLineBreak();
    }

    /**
     * Prints a message of how many task are in the task list.
     *
     * @param size size of task list.
     */
    void taskCounterMessage (int size) {
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Prints a message informing the user of an error that has occured.
     * Error is specific to the chat bot itself.
     *
     * @param e Duke Exception that has occurred.
     */
    void printErrorMessage(DukeException e) {
        System.out.println(e.toString());
        printLineBreak();
    }
}
