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
            String firstCommand = commandSplit[0];
            if (command.equals("bye")) {
                System.out.println("Goodbye!");
                break;
            } else if (commandSplit[0].equals("done") && Duke.isInteger(commandSplit[1])) {
                int taskIndex = Integer.parseInt(commandSplit[1]);
                tasks.markTaskDone(taskIndex);
                String task = tasks.getTask(taskIndex);
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(task);
            } else if (firstCommand.equals("deadline") || firstCommand.equals("todo") || firstCommand.equals("event") ) {
                String date = Duke.findDateInCommand(commandSplit);
                String taskDesc = Duke.findTaskDescription(commandSplit);
                tasks.addTask(taskDesc, firstCommand, date);
                System.out.println("Got it. I've added this task: ");
//                System.out.println(tasks.getTasksLength());
                System.out.println(tasks.getTask(tasks.getTasksLength()));
                System.out.println("Now you have " + tasks.getTasksLength() + " tasks in the list.");
            } else if (command.equals("list")) {
                tasks.listTasks();
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

    /**
     * Finds the date in the command (if any).
     * @param commandList The array of the split up command.
     * @return The date in the command (if any).
     */
    private static String findDateInCommand(String[] commandList) {
        int startingIndexOfDate = -1;
        for (int i = 0; i < commandList.length; i++) {
            if (commandList[i].equals("/by") || commandList[i].equals("/at")) {
                startingIndexOfDate = i + 1;
                break;
            }
        }
        if (startingIndexOfDate == -1) {
            return "";
        } else {
            StringBuilder fullDate = new StringBuilder();
            for (int i = startingIndexOfDate; i < commandList.length; i++) {
                fullDate.append(commandList[i]);
                if (i != commandList.length - 1) {
                    fullDate.append(" ");
                }
            }
            return fullDate.toString();
        }
    }

    /**
     * Finds the task description in the command list.
     * @param commandList The array of the split up command.
     * @return The description of the task.
     */
    public static String findTaskDescription(String[] commandList) {
        StringBuilder taskDesc = new StringBuilder();
        for (int i = 1; i < commandList.length; i++) {
            if (commandList[i].equals("/by") || commandList[i].equals("/at")) {
                break;
            } else {
                taskDesc.append(commandList[i]).append(" ");
            }
        }
        return taskDesc.toString();
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
