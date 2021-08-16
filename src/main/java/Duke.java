import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    /** The data structure to store all the tasks. **/
    private ArrayList<Task> tasks = new ArrayList<>();

    /** A enum type indicating the type of the task. **/
    private enum taskType {
        TODO, DEADLINE, EVENT
    }

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
     *
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
     * A public method to check whether the command entered is to mark the task as done.
     *
     * @param inputCommand The user input command.
     *
     * @return A boolean value indivates whether the input command is a mark-as-done command.
     */
    public boolean isMarkAsDoneCommand(String inputCommand) {
        if (inputCommand.length() < 6) {
            return false;
        }
        String firstHalf = inputCommand.substring(0, 4);
        String secondHalf = inputCommand.substring(5, inputCommand.length());
        if (!firstHalf.equals("done")) {
            return false;
        }
        for (int i = 0; i < secondHalf.length(); i++) {
            if (secondHalf.charAt(i) > '9' || secondHalf.charAt(i) < '0') return false;
        }
        return true;
    }

    /**
     * A method that list all current tasks.
     *
     * @return An array of String, each String contains a task.
     */
    public String[] listAllTasks() {
        int n = tasks.size();
        String taskList[] = new String[n + 1];
        taskList[0] = "Here are the tasks in your list:";
        for (int i = 1; i <= n; i++) {
            taskList[i] = String.format("%d. %s", i, tasks.get(i - 1));
        }
        return taskList;
    }

    /**
     * A method that read a add-task-command, and then add the task to the task list.
     *
     * @param type The type of the task.
     *
     * @param s The input instruction.
     */
    public void addTask(taskType type, String s) {
        if (type == taskType.TODO) {
            tasks.add(new Todo(s));
        } else {
            String description = s.substring(0, s.indexOf("/"));
            String time = s.substring(s.indexOf("/") + 4, s.length());
            if (type == taskType.DEADLINE) {
                tasks.add(new Deadline(description, time));
            } else {
                tasks.add(new Event(description, time));
            }
        }
    }

    /**
     * A method that read the number of a task, and mark it as done.
     *
     * @param taskNumber The unique number of a task.
     */
    public void markAsDone(int taskNumber) {
        tasks.get(taskNumber).markAsDone();
    }

    /**
     * A method that decode the add-task-command.
     *
     * @param inputCommand The command entered.
     *
     * @throws descriptionEmptyException
     *
     * @throws timeEmptyException
     *
     * @throws noMeaningCommandException
     */
    public void addTaskDecode(String inputCommand)
            throws descriptionEmptyException, timeEmptyException, noMeaningCommandException{
        taskType type = null;
        int len = inputCommand.length();
        String s1 = inputCommand.substring(0, Math.min(4, len));
        String s2 = inputCommand.substring(0, Math.min(8, len));
        String s3 = inputCommand.substring(0, Math.min(5, len));

        if (s1.equals("todo")) {
            type = taskType.TODO;
        } else if (s2.equals("deadline")) {
            type = taskType.DEADLINE;
        } else if (s3.equals("event")) {
            type = taskType.EVENT;
        } else {
            throw new noMeaningCommandException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        if (inputCommand.indexOf(" ") == -1) {
            throw new descriptionEmptyException(" ☹ OOPS!!! The description of a " + inputCommand + " cannot be empty.");
        }
        if (type != taskType.TODO && inputCommand.indexOf("/") == -1) {
            throw new timeEmptyException(" ☹ OOPS!!! The time of a "
                    + (type == taskType.EVENT ? "event" : "deadline")
                    + " cannot be empty.");
        }

        addTask(type, inputCommand.substring(inputCommand.indexOf(" ") + 1, inputCommand.length()));
    }

    /**
     * A method that allows Duke to read the input command and react.
     *
     * @throws Exception Task number larger than total number of tasks.
     */
    public void chat() throws taskNumberOutOfBoundException{
        Scanner sc = new Scanner(System.in);
        while (true) {
            String inputCommand = sc.nextLine();
            if (isExitCommand(inputCommand)) {
                printMessage(new String[] {"Bye. Hope to see you again soon!"});
                return;
            } else if (inputCommand.length() < 1) {
                continue;
            } else if (inputCommand.equals("list")){
                printMessage(listAllTasks());
            } else if (isMarkAsDoneCommand(inputCommand)) {
                int taskNumber = 0;
                for (int i = 5; i < inputCommand.length(); i++) {
                    taskNumber = taskNumber * 10 + (int) (inputCommand.charAt(i) - '0');
                }
                taskNumber --;
                if (taskNumber >= tasks.size()) {
                    throw new taskNumberOutOfBoundException(
                            "Input task number is larger than total number of tasks.");
                }
                markAsDone(taskNumber);
                printMessage(new String[] {
                        "Nice! I've marked this task as done:",
                        tasks.get(taskNumber).toString()});
            } else {
                try {
                    addTaskDecode(inputCommand);
                } catch (descriptionEmptyException e) {
                    printMessage(new String[] {e.toString()});
                    continue;
                } catch (timeEmptyException e) {
                    printMessage(new String[] {e.toString()});
                    continue;
                } catch (noMeaningCommandException e) {
                    printMessage(new String[] {e.toString()});
                    continue;
                }
                printMessage(new String[] {
                        "Got it. I've added this task:",
                        "  " + tasks.get(tasks.size() - 1).toString(),
                        "Now you have " + tasks.size() + " tasks in the list."});
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
        try {
            chatBot.chat();
        } catch (taskNumberOutOfBoundException e) {
            chatBot.printMessage(new String[] {e.toString()});
        }
    }
}
