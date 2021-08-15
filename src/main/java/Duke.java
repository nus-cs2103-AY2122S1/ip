import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Driver class to simulate the 'Annie' chat bot program.
 *
 * @author limzk126
 * @version Level-3
 */
public class Duke {
    private final String LINE = "______________________________________________________________\n";
    private final String WELCOME_MSG = "Hi I am Annie!\nHow can I assist you?";
    private final String GOODBYE_MSG = "Bye. See you soon!";
    private final String LIST_MSG = "Here are the tasks in your list:\n";

    // List to store user task inputs.
    private List<Task> taskList = new ArrayList<>();

    // Flag to indicate if program is ended by user.
    private boolean isEnded = false;

    /*
     * Prints a horizontal line, followed by the text input by user on a newline,
     * then finally a horizontal line on a newline.
     */
    private void printText(String text) {
        System.out.printf("%s", LINE);
        System.out.println(text);
        System.out.printf("%s\n", LINE);
    }

    // Adds task to the list and notifies the user that it has successfully done so.
    private void addTask(Task task) {
        String message = "Task added: " + task.getDescription();
        printText(message);
        taskList.add(task);
    }

    // Prints a numbered checklist of the user's task.
    private void printList() {
        int taskNum = 0;

        System.out.printf("%s", LINE);
        System.out.printf("%s", LIST_MSG);

        for (Task task : taskList) {
            System.out.printf("%d.[%s] %s\n", ++taskNum, task.getStatusIcon(),
                    task.getDescription());
        }

        System.out.printf("%s\n", LINE);
    }

    /*
     * Marks the specified task as done and notifies the user that it has successfully
     * done so.
     */
    private void completeTask(int num) {
        Task task = taskList.get(num - 1);

        String message = "I have marked this task as done: " + task.getDescription();
        printText(message);

        task.markDone();
    }

    // Retrieves the task number specified by the user when they issue the 'done' command.
    private int getTaskNum(String text) {
        String[] words = text.split(" ");
        int TaskNum = Integer.parseInt(words[1]);

        return TaskNum;
    }

    // Processes text to find out what command user has issued to the program.
    private void parseText(String text) {
        if (text.equals("bye")) {
            // End program.
            isEnded = true;
        } else if (text.equals("list")) {
            // List all previous text.
            printList();
        } else if (text.matches("done (.*)")) {
            // Complete task.
            int TaskNum = getTaskNum(text);
            completeTask(TaskNum);
        } else {
            // Add text to list.
            addTask(new Task(text));
        }
    }

    /**
     * Method to simulate the program.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        String textInput = "";

        // Program starts. Say hello.
        printText(WELCOME_MSG);

        // While loop to continuously receive user input.
        while (!isEnded) {
            textInput = sc.nextLine().trim();
            parseText(textInput);
        }

        // Program ends. Say goodbye.
        printText(GOODBYE_MSG);

        sc.close();
    }

    /**
     * Driver method to start program.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
