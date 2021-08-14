import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        // Introduction message
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\nWhat can I do for you?");
        drawLine();

        //Initialise list to store tasks and Scanner to get user input
        List<Task> tasks = new ArrayList<>(100);
        Scanner sc = new Scanner(System.in);

        // Main Code
        while(true) {
            String userInput = sc.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                dukeReply("Bye. Hope to see you again soon!");
                break;
            } else if(userInput.equalsIgnoreCase("list")) {
                showTasks(tasks);
                // cant put done word as a task
            }else if (userInput.toLowerCase().startsWith("done")) {
                markTaskDone(userInput, tasks);
            } else {
                addTask(userInput, tasks);
            }
        }
    }

    /**
     * Adds a task to the List of tasks.
     * @param userInput String of task to add.
     * @param tasks List of current tasks.
     */

    public static void addTask(String userInput, List<Task> tasks) {
        tasks.add(new Task(userInput));
        dukeReply("added: " + userInput);
    }

    /**
     * Marks a task as complete.
     * @param userInput Text beginning with 'done' followed by a number.
     * @param tasks List of current tasks.
     */
    public static void markTaskDone(String userInput, List<Task> tasks) {
        // catch .NumberFormatException here and invalid inputs - neg, doesnt exisst
        // StringIndexOutOfBoundsException -- done
        //IndexOutOfBoundsException - done number not there
        int taskIndex = Integer.parseInt(userInput.substring(5)) - 1;
        Task task = tasks.get(taskIndex);
        if(task.checkStatus()) {
            dukeReply("Youve already marked this task as done!");
        } else {
            task.setDone();
            dukeReply("Nice! I've marked this task as done:\n" + task.getTask());
        }
    }

    /**
     * Prints the list of todos sequentially.
     * @param tasks List of current tasks.
     */
    public static void showTasks(List<Task> tasks) {
        drawLine();
        System.out.println("Here are the tasks in your list:");
        for( int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, tasks.get(i).getTask());
        }
        drawLine();
    }

    /**
     * Prints dukes reply in between two lines.
     * @param message Duke's reply.
     */
    public static void dukeReply(String message) {
        drawLine();
        System.out.println(message);
        drawLine();
    }

    /**
     * Draws a line on the screen to separate speech.
     */
    public static void drawLine() {
        System.out.println("------------------------");
    }

}
