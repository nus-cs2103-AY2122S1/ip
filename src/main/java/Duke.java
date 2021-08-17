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
            String[] commandSplit = command.split(" ");
            if (command.equals("bye")) {
                System.out.println("Goodbye!");
                break;
            } else if (commandSplit[0].equals("done") && Duke.isInteger(commandSplit[1])) {
                int taskIndex = Integer.parseInt(commandSplit[1]);
                tasks.markTaskDone(taskIndex);
                String task = tasks.getTask(taskIndex);
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(task);
            } else if (command.equals("list")) {
                tasks.listTasks();
            } else {
                tasks.addTask(command);
                System.out.println("added: " + command);
            }
        }
    }

    /**
     * Checks if the string is an integer.
     * @param input String to check.
     * @return Whether string is an integer.
     */
    private static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e){
            return false;
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
