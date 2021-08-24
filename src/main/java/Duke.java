import java.util.Objects;
import java.util.Scanner;

/**
 * This program is a chatbot that creates a task list for you and is able to mark tasks as done.
 */
public class Duke {
    public static void main(String[] args) {
        String inData = "yo";
        introMessage();

        TaskManager taskManager = new TaskManager();

        Scanner scan = new Scanner(System.in);
        while (!Objects.equals(inData, "bye")) {
            inData = scan.nextLine();
            taskManager.executeCommand(inData);
        }
    }

    public static void introMessage() {
        System.out.println("____________________________________________________________ \n"
                + "Hello! I'm Joker \n"
                + "What can I do for you? \n"
                + "____________________________________________________________");
    }
}
