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
                    try {
                        displayTasksList(tasksList);
                    } catch (DukeException e) {
                        echo(e.getMessage());
                    }
                    break;
                case "done":
                    try {
                        markDone(command, tasksList);
                    } catch (DukeException e) {
                        echo(e.getMessage());
                    }
                    break;
                case "todo":
                    try {
                        addToDoTask(command, tasksList);
                    } catch (DukeException e) {
                        echo(e.getMessage());
                    }
                    break;
                case "deadline":
                    try {
                        addDeadlineTask(command, tasksList);
                    } catch (DukeException e) {
                        echo(e.getMessage());
                    }
                    break;
                case "event":
                    try {
                        addEventTask(command, tasksList);
                    } catch (DukeException e) {
                        echo(e.getMessage());
                    }
                    break;
                default:
                    try {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means!");
                    } catch (DukeException e) {
                        echo(e.getMessage());
                    }
                    break;
            }
        } while (!commandLine.equals("bye"));
    }

    static void displayTasksList(List<Task> tasksList) throws DukeException {
        if (tasksList.size() != 0) {
            String response = "\t" + "Here are the tasks in your list:" +
                    System.lineSeparator();

            for (int i = 0; i < tasksList.size(); i++) {
                if (i != 0) {
                    response += System.lineSeparator();
                }

                response += "\t\t" + (i + 1) + "." + "\t" + tasksList.get(i).toString();
            }

            echo(response);
        } else {
            throw new DukeException("There are no tasks in your list!");
        }
    }

    static void markDone(String[] command, List<Task> tasksList) throws DukeException {
        if (command.length == 2) {
            int taskNum = Integer.parseInt(command[1]);

            if (taskNum <= tasksList.size()) {
                tasksList.get(taskNum - 1).setDone();
                Task taskDone = tasksList.get(taskNum - 1);

                String response = taskDoneMessage() + System.lineSeparator() +
                        "\t\t" + taskDone;
                echo(response);
            } else {
                throw new DukeException("☹ Please select a valid task number to be marked as done.");
            }
        } else {
            throw new DukeException("☹ Please select the task number to be marked as done.");
        }
    }

    static void addToDoTask(String[] command, List<Task> tasksList) throws DukeException {
        if (command.length == 2) {
            Task newToDoTask = new ToDo(TaskType.TODO, command[1]);
            tasksList.add(newToDoTask);

            String response = taskAddedMessage() + System.lineSeparator() +
                    "\t\t" + newToDoTask + System.lineSeparator() +
                    numOfTasksInList(tasksList);
            echo(response);
        } else {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    static void addDeadlineTask(String[] command, List<Task> tasksList) throws DukeException {
        if (command.length == 2) {
            String[] deadlineTaskDetails = command[1].split("/", 2);

            if (deadlineTaskDetails.length == 2) {
                Task newDeadlineTask = new Deadline(TaskType.DEADLINE,
                        deadlineTaskDetails[0], deadlineTaskDetails[1]);
                tasksList.add(newDeadlineTask);

                String response = taskAddedMessage() + System.lineSeparator() +
                        "\t\t" + newDeadlineTask + System.lineSeparator() +
                        numOfTasksInList(tasksList);
                echo(response);
            } else {
                throw new DukeException("☹ OOPS!!! The description and/or " +
                        "specific date/time of a deadline is not valid.");
            }
        } else {
            throw new DukeException("☹ OOPS!!! The description and " +
                    "specific date/time of a deadline cannot be empty.");
        }
    }

    static void addEventTask(String[] command, List<Task> tasksList) throws DukeException {
        if (command.length == 2) {
            String[] eventTaskDetails = command[1].split("/", 2);

            if (eventTaskDetails.length == 2) {
                Task newEventTask = new Event(TaskType.EVENT,
                        eventTaskDetails[0], eventTaskDetails[1]);
                tasksList.add(newEventTask);

                String response = taskAddedMessage() + System.lineSeparator() +
                        "\t\t" + newEventTask + System.lineSeparator() +
                        numOfTasksInList(tasksList);
                echo(response);
            } else {
                throw new DukeException("☹ OOPS!!! The description and/or " +
                        "specific date/time of a deadline is not valid.");
            }
        } else {
            throw new DukeException("☹ OOPS!!! The description and " +
                    "specific date/time of an event cannot be empty.");
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
