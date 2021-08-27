package duke;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that handles all user interaction.
 * Responsible for outputs as well as reading inputs from users.
 */
public class Ui {
    private static final Scanner USER_INPUT = new Scanner(System.in);
    private static final String LINE_BREAKER = "____________________________________________________________";
    private static final  String GREETING = "Hello! I'm Duke\n" + "What can I do for you?";
    private static final String FAREWELL = "Bye. Hope to see you again soon!";
    private static final String LIST_MESSAGE = "Here are the tasks in your list:";
    private static final String DONE_MESSAGE = "Nice! I've marked this task as done:";
    private static  final String ADD_TASK_MESSAGE = "Got it. I've added this task:";
    private static final String DELETE_TASK_MESSAGE = "Noted. I've removed this task:";
    private static final String FIND_TAKS_MESSAGE = "Here are the matching tasks in your list:";

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
        return USER_INPUT.nextLine();
    }

    /**
     * Prints a line for separation.
     */
    public  void printLineBreak () {
        System.out.println(LINE_BREAKER);
    }

    /**
     * Prints the content of the task list in a form where users can see.
     *
     * @param taskList task list to be printed.
     */
    public void printArrayList (TaskList taskList) {
        System.out.println(LIST_MESSAGE);
        for(int i = 0; i < taskList.getSize(); i++) {
            System.out.println(String.valueOf(i+1) + "." + taskList.get(i).toString());
        }
        printLineBreak();
    }

    /**
     * Prints a greeting for the user on startup.
     */
    public void greet(){
        System.out.println(GREETING);
        printLineBreak();
    }

    /**
     * Prints a farewell message for the user when 'bye' command is inputted.
     */
    public void sayFarewell() {
        printLineBreak();
        System.out.println(FAREWELL);
        printLineBreak();
    }

    /**
     * Prints a notifications which informs the user
     * on which task is completed based on their command.
     *
     * @param task task that is completed.
     */
    public void completeTaskMessage (Task task) {
        System.out.println(DONE_MESSAGE + "\n" + task.toString());
        printLineBreak();
    }

    
    public void findTaskMessage (TaskList tasks) {
        System.out.println(FIND_TAKS_MESSAGE);
        for(int i = 0; i < tasks.getSize(); i++) {
            System.out.println(String.valueOf(i+1) + "." + tasks.get(i).toString());
        }
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
        System.out.println(DELETE_TASK_MESSAGE);
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
        System.out.println(ADD_TASK_MESSAGE);
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
