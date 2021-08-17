import java.util.Scanner;

/**
 * This class represents the chat bot, Duke.
 */
public class Duke {
    /**
     * The static method that runs in Main to reply to the user.
     */
    public static void reply() {
        Scanner myObj = new Scanner(System.in);
        String command;
        TaskList tasks = TaskList.createTaskList();
        while (true) {
            command = myObj.nextLine();
            if (command.equals("bye")) {
                System.out.println("Goodbye!");
                break;
            } else if (command.equals("list")) {
                tasks.listTasks();
            } else {
                tasks.addTask(command);
                System.out.println("added: " + command);
            }
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from \n" + logo);
        System.out.println("What can I do for you?");
        Duke.reply();
    }
}
