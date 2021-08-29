package duke.main;

import duke.task.Task;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents user interface abstraction.
 * 
 * @author Gordon Yit
 * @Version CS2103T
 */
public class Ui {
    private final String LOGO = " ____        _        \n" 
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private String c = "~";
    private String line1 = " Hello! I'm Duke.Duke ";
    private String line2 = " What do you wanna do today? ";
    private int bufferLength = 5;
    private int limit = Integer.max(line1.length(), line2.length()) + bufferLength;
    private String buffer1 = c.repeat((limit - line1.length()) / 2);
    private String buffer2 = c.repeat((limit - line2.length()) / 2);
    private String str = buffer1 + line1 + buffer1 + "\n" + buffer2 + line2 + buffer2 + "\n";
    private final String GREETING = "Hello from\n" + LOGO + str;
    private final String FAREWELL = "@@@@ Till we meet again, my friend @@@@"; 
    private Scanner sc;
    private String command;
    
    /**
     * Class constructor.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Welcomes the user.
     */
    public void showWelcome() {
        System.out.println(GREETING);
    }
    
    /**
     * Reads user commands
     * 
     * @return user commands as a string.
     */
    public String readCommand() {
        command = sc.nextLine();
        return command;
    }
    
    /**
     * Shows farewell message to user.
     */
    public void showFarewell() {
        System.out.println(FAREWELL);
        sc.close();
    }
    
    /**
     * Shows the diving line.
     */
    public void showLine() {
        String line = "_________________________________";
        System.out.println(line);
    }

    /**
     * Shows the error message from loading the file.
     */
    public void showLoadingError() {
        String loadingError = "There was a problem loading the file.";
        System.out.println(loadingError);
    }

    /**
     * Shows the error message from dukeException class.
     *
     * @return the error message.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Displays a specific task that was marked done.
     * 
     * @param task the task marked as done.
     */
    public void showMarkTaskDone(Task task) {
        String doneMessage = "Yay, one task down!";
        System.out.println(doneMessage);
        String taskMarkedAsDone = String.format("~~%s~~", task.toString());
        System.out.println(taskMarkedAsDone);
    }

    /**
     * Displays the task deleted.
     * 
     * @param task the deleted task.
     * @param taskArrayList an arraylist of task after deleting the task.
     */
    public void showTaskDeleted(Task task, ArrayList<Task> taskArrayList) {
        String deletionMessage = "Alrighty, I've removed this task:";
        System.out.println(deletionMessage);
        String deletedTask = String.format("~~%s~~", task.toString());
        System.out.println(deletedTask);
        String tasksRemaining = String.format("Now, you have %s %s remaining", taskArrayList.size(), 
                                    (taskArrayList.size() > 1 ? "tasks" : "task"));
        System.out.println(tasksRemaining);
    }

    /**
     * Displays the task added.
     * 
     * @param task the task added.
     * @param taskArrayList arraylist of tasks after adding the new task.
     */
    public void showTaskAdded(Task task, ArrayList<Task> taskArrayList) {
        String additionMessage = "Got it. I've added this task:";
        System.out.println(additionMessage);
        String addedTask = String.format("~~%S~~", task.toString());
        System.out.println(addedTask);
        String newTally = String.format("Now you have %s %s in the list.", taskArrayList.size(), 
                                    (taskArrayList.size() > 1 ? "tasks" : "task"));
        System.out.println(newTally);
    }

    /**
     * Lists out all the tasks Duke.Duke is keeping track of.
     * 
     * @param taskArrayList current arraylist of tasks.
     */
    public void showListOfTasks(ArrayList<Task> taskArrayList) {
        String header = "Here are the tasks in your list:";
        iterate(header, taskArrayList);
    }

    /**
     * Lists out all the tasks that falls on a specified date.
     * 
     * @param taskArrayList arraylist of task that fall on the specified date.
     */
    public void showFilteredTasks(ArrayList<Task> taskArrayList, Date date) {
        String header = String.format("On %s, you have: ", date.toString());
        iterate(header, taskArrayList);
    }
    
    private void iterate(String headerMessage, ArrayList<Task> tasks) {
        System.out.println(headerMessage);
        for(int i = 0; i < tasks.size(); i++) {
            System.out.println(String.format("%s.%s", i + 1, tasks.get(i).toString()));
        }
    }
}
