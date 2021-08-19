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

            switch (command[0]) {
                case "bye":
                    echo(exitMessage());
                    break;
                case "list":
                    echo(displayTasksList(tasksList));
                    break;
                case "done":
                    int taskNum = Integer.parseInt(command[1]);
                    tasksList.get(taskNum - 1).setDone();
                    Task task = tasksList.get(taskNum - 1);
                    echo(taskDoneMessage(task));
                    break;
                default:
                    Task newTask = new Task(commandLine);
                    tasksList.add(newTask);
                    echo(taskAddedMessage(newTask));
                    break;
            }
        } while (!commandLine.equals("bye"));
    }

    private static String greetMessage() {
        return "\t" + "Hello! I'm Duke" + System.lineSeparator() +
                "\t" + "What can I do for you?";
    }

    private static String exitMessage() {
        return "\t" + "Bye. Hope to see you again soon!";
    }


    private static String taskAddedMessage(Task newTask) {
        return "\t" + "added: " + newTask.getDescription();
    }

    private static String taskDoneMessage(Task task) {
        return "\t" + "Nice! I've marked this task as done:" +
                System.lineSeparator() + "\t\t" + task.toString();
    }

    private static String displayTasksList(List<Task> tasksList) {
        String result = "\t" + "Here are the tasks in your list:"
                + System.lineSeparator();

        for (int i = 0; i < tasksList.size(); i++) {
            if (i != 0) {
                result += System.lineSeparator();
            }

            result += "\t\t" + (i + 1) + "." + "\t" + tasksList.get(i).toString();
        }

        return result;
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
