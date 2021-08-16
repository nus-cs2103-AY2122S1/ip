import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    /** The data structure to store all the tasks. **/
    ArrayList<String> tasks = new ArrayList<>();

    /**
     * A public method to print message with certain indentation and format.
     * Receive an array of String, and output one String per line.
     *
     * @param messages
     */
    public void printMessage(String messages[]) {
        System.out.println("    ____________________________________________________________");
        int n = messages.length;
        for (int i = 0; i < n; i++) {
            System.out.print("     ");
            System.out.println(messages[i]);
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("");
    }

    /**
     * A public method to output the greeting message.
     */
    public void greet() {
        String greetMessage[] = new String[2];
        greetMessage[0] = "Hello! I'm Duke";
        greetMessage[1] = "What can I do for you?";
        printMessage(greetMessage);
    }

    /**
     * A public method to check whether the input command is an exit command.
     *
     * @param inputCommand The user input command.
     * @return A boolean value indicates whether the input command is an exit command.
     */
    public boolean isExitCommand(String inputCommand) {
        if (inputCommand.equals("bye")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * A method that list all current tasks.
     *
     * @return An array of String, each String contains the number and name of a task.
     */
    public String[] listAllTasks() {
        int n = tasks.size();
        String taskList[] = new String[n];
        for (int i = 0; i < n; i++) {
            taskList[i] = String.format("%d. %s", i + 1, tasks.get(i));
        }
        return taskList;
    }

    /**
     * A method that read the name of a task, and then add the task to the task list.
     *
     * @param task The name of the task.
     */
    public void addTask(String task) {
        tasks.add(task);
    }

    /**
     * A method that allows Duke to read the input command and react.
     */
    public void chat() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String inputCommand = sc.nextLine();
            if (isExitCommand(inputCommand)) {
                printMessage(new String[] {"Bye. Hope to see you again soon!"});
                return;
            } else if (inputCommand.equals("list")){
                printMessage(listAllTasks());
            } else {
                addTask(inputCommand);
                printMessage(new String[] {"added: " + inputCommand});
            }
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke chatBot = new Duke();
        chatBot.greet();
        chatBot.chat();
    }
}
