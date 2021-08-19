import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        List<Task> tasksList = new ArrayList<Task>();

        echo(greetMessage());

        Scanner sc = new Scanner(System.in);
        String commandLine = "";

        do {
            System.out.print("Enter command: \t");
            commandLine = sc.nextLine().trim();
            String[] command = commandLine.split(" ", 2);
            System.out.println();

            switch (command[0]) {
                case "bye":
                    echo(exitMessage());
                    break;
                case "list":
                    displayTasksList(tasksList);
                    break;
                case "done":
                    markDone(command, tasksList);
                    break;
                case "todo":
                    addToDoTask(command, tasksList);
                    break;
                case "deadline":
                    addDeadlineTask(command, tasksList);
                    break;
                case "event":
                    addEventTask(command, tasksList);
                    break;
                default:
                    break;
            }
        } while (!commandLine.equals("bye"));
    }

    static void displayTasksList(List<Task> tasksList) {
        String response = "\t" + "Here are the tasks in your list:" +
                System.lineSeparator();

        for (int i = 0; i < tasksList.size(); i++) {
            if (i != 0) {
                response += System.lineSeparator();
            }

            response += "\t\t" + (i + 1) + "." + "\t" + tasksList.get(i).toString();
        }

        echo(response);
    }

    static void markDone(String[] command, List<Task> tasksList) {
        int taskNum = Integer.parseInt(command[1]);

        if (taskNum <= tasksList.size()) {
            tasksList.get(taskNum - 1).setDone();
            Task taskDone = tasksList.get(taskNum - 1);

            String response = taskDoneMessage() + System.lineSeparator() +
                    "\t\t" + taskDone;
            echo(response);
        }
    }

    static void addToDoTask(String[] command, List<Task> tasksList) {
        Task newToDoTask = new ToDo(TaskType.TODO, command[1]);
        tasksList.add(newToDoTask);

        String response = taskAddedMessage() + System.lineSeparator() +
                "\t\t" + newToDoTask + System.lineSeparator() +
                numOfTasksInList(tasksList);
        echo(response);
    }

    static void addDeadlineTask(String[] command, List<Task> tasksList) {
        String[] deadlineTaskDetails = command[1].split("/", 2);

        if (deadlineTaskDetails.length == 2) {
            Task newDeadlineTask = new Deadline(TaskType.DEADLINE,
                    deadlineTaskDetails[0], deadlineTaskDetails[1]);
            tasksList.add(newDeadlineTask);

            String response = taskAddedMessage() + System.lineSeparator() +
                    "\t\t" + newDeadlineTask + System.lineSeparator() +
                    numOfTasksInList(tasksList);
            echo(response);
        }
    }

    static void addEventTask(String[] command, List<Task> tasksList) {
        String[] eventTaskDetails = command[1].split("/", 2);

        if (eventTaskDetails.length == 2) {
            Task newEventTask = new Event(TaskType.EVENT,
                    eventTaskDetails[0], eventTaskDetails[1]);
            tasksList.add(newEventTask);

            String response = taskAddedMessage() + System.lineSeparator() +
                    "\t\t" + newEventTask + System.lineSeparator() +
                    numOfTasksInList(tasksList);
            echo(response);
        }
    }

    private static String greetMessage() {
        return "\t" + "Hello! I'm Duke" + System.lineSeparator() +
                "\t" + "What can I do for you?";
    }

    private static String exitMessage() {
        return "\t" + "Bye. Hope to see you again soon!";
    }

    private static String taskAddedMessage() {
        return "\t" + "Got it. I've added this task:";
    }

    private static String numOfTasksInList(List<Task> tasksList) {
        return "\t" + "Now you have " + tasksList.size() +
                (tasksList.size() > 1 ? " tasks" : " task") +
                " in the list.";
    }

    private static String taskDoneMessage() {
        return "\t" + "Nice! I've marked this task as done:";
    }

    private static void echo(String message) {
        String horizontalLine = "\t" +
                "____________________________________________________________";
        String nextLine = System.lineSeparator();
        String echoMessage = horizontalLine + nextLine +
                message + nextLine + horizontalLine;
        System.out.println(echoMessage);
    }
}
