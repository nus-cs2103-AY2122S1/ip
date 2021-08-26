package duke;

import java.util.Scanner;

public class Ui {
    Scanner sc;
    
    public Ui() {
        sc = new Scanner(System.in);
    }
    
    public void closeScanner() {
        sc.close();
    }

    /**
     * Prints the greeting message of the Chatbot.
     */
    public void printGreetings() {
        String message = "Hello! I'm Duke\nWhat can I do for you?";
        printMessage(message);
    }
    
    public String getInput() {
        return sc.nextLine();
    }
    
    /**
     * Prints the formatted list of content in <code>list</code>.
     */
    public void printList(TaskList list) {
        String message = list.generateMessage();
        printMessage(message);
    }

    /**
     * Prints the log for task object being added
     *
     * @param task The task object to be printed.
     */
    public void printAddedTaskMessage(Task task, TaskList list) {
        String message =
                "Got it. I've added this task:\n" +
                "  " + task + "\n" +
                String.format("Now you have %d tasks in the list.", list.size());
        printMessage(message);
    }
    
    /**
     * Prints the input content to console with formatting.
     *
     * @param content The content to be printed, wrapped between horizontal lines.
     */
    public void printMessage(String content) {
        String format =
                "        ____________________________________________________________\n" +
                "        %s\n" +
                "        ____________________________________________________________\n";
        System.out.printf(format, content.replaceAll("\n", "\n        "));
    }
}
