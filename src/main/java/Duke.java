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
            System.out.println("Enter command: \t");
            commandLine = sc.nextLine().trim();
            String[] command = commandLine.split(" ", 2);

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
                case "delete":
                    try {
                        deleteTask(command, tasksList);
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
                        throw new DukeException("\t" + "☹ OOPS!!! I'm sorry, but I don't know what that means!");
                    } catch (DukeException e) {
                        echo(e.getMessage());
                    }
                    break;
            }
        } while (!commandLine.equals("bye"));
    }

    static void displayTasksList(List<Task> tasksList) throws DukeException {
        if (tasksList.size() != 0) {
            String response = "\t" + "Here are the tasks in your list:" + "\n";

            for (int i = 0; i < tasksList.size(); i++) {
                if (i != 0) {
                    response += "\n";
                }

                response += "\t\t" + (i + 1) + "." + "\t" + tasksList.get(i).toString();
            }

            echo(response);
        } else {
            throw new DukeException("\t" + "There are no tasks in your list!");
        }
    }

    static void markDone(String[] command, List<Task> tasksList) throws DukeException {
        if (command.length == 2) {
            int taskNum = Integer.parseInt(command[1]);

            if (taskNum <= tasksList.size()) {
                tasksList.get(taskNum - 1).setDone();
                Task taskDone = tasksList.get(taskNum - 1);

                String response = doneTaskMessage() + "\n" +
                        "\t\t" + taskDone;
                echo(response);
            } else {
                throw new DukeException("\t" + "☹ Please select a valid task number to be marked as done.");
            }

        } else {
            throw new DukeException("\t" + "☹ Please select the task number to be marked as done.");
        }
    }

    static void deleteTask(String[] command, List<Task> tasksList) throws DukeException {
        if (command.length == 2) {
            int taskNum = Integer.parseInt(command[1]);

            if (taskNum <= tasksList.size()) {
                Task taskToDelete = tasksList.get(taskNum - 1);
                tasksList.remove(taskToDelete);

                String response = deleteTaskMessage() + "\n" +
                        "\t\t" + taskToDelete + "\n" +
                        numOfTasksInList(tasksList);
                echo(response);
            } else {
                throw new DukeException("\t" + "☹ Please select a valid task number to be deleted.");
            }

        } else {
            throw new DukeException("\t" + "☹ Please select the task number to be deleted.");
        }
    }

    static void addToDoTask(String[] command, List<Task> tasksList) throws DukeException {
        if (command.length == 2) {
            Task newToDoTask = new ToDo(TaskType.TODO, command[1]);
            tasksList.add(newToDoTask);

            String response = addTaskMessage() + "\n" +
                    "\t\t" + newToDoTask + "\n" +
                    numOfTasksInList(tasksList);
            echo(response);
        } else {
            throw new DukeException("\t" + "☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    static void addDeadlineTask(String[] command, List<Task> tasksList) throws DukeException {
        if (command.length == 2) {
            String[] deadlineTaskDetails = command[1].split("/", 2);

            if (deadlineTaskDetails.length == 2) {
                String description = deadlineTaskDetails[0].trim();
                String beforeDateTime = deadlineTaskDetails[1].trim();
                String[] beforeDateTimeParts = beforeDateTime.split("\\s+", 2);

                Task newDeadlineTask = new Deadline(TaskType.DEADLINE,
                        description, beforeDateTimeParts[1]);
                tasksList.add(newDeadlineTask);

                String response = addTaskMessage() + "\n" +
                        "\t\t" + newDeadlineTask + "\n" +
                        numOfTasksInList(tasksList);
                echo(response);
            }
        } else {
            throw new DukeException("\t" + "☹ OOPS!!! The description and/or " +
                    "specific date/time of a deadline cannot be empty.");
        }
    }

    static void addEventTask(String[] command, List<Task> tasksList) throws DukeException {
        if (command.length == 2) {
            String[] eventTaskDetails = command[1].split("/", 2);

            if (eventTaskDetails.length == 2) {
                String description = eventTaskDetails[0].trim();
                String startEndDateTime = eventTaskDetails[1].trim();
                String[] startEndDateTimeParts = startEndDateTime.split("\\s+", 2);

                Task newEventTask = new Event(TaskType.EVENT,
                        description, startEndDateTimeParts[1]);
                tasksList.add(newEventTask);

                String response = addTaskMessage() + "\n" +
                        "\t\t" + newEventTask + "\n" +
                        numOfTasksInList(tasksList);
                echo(response);
            }
        } else {
            throw new DukeException("☹ OOPS!!! The description and/or " +
                    "specific date/time of an event cannot be empty.");
        }
    }

    private static String greetMessage() {
        return "\t" + "Hello! I'm Duke, your Personal Assistant Chatbot." + "\n" +
                "\t" + "What can I do for you?\n\n" +
                instructions();
    }

    private static String instructions() {
        return "\t" + "Menu Options:" + "\n" +
                "\t\t" + "1." + "\t" + "list" + "\n" +
                "\t\t\t" + "[List the tasks in your list]" + "\n" +
                "\t\t" + "2." + "\t" + "todo ABC" + "\n" +
                "\t\t\t" + "[Add a todo task, ABC, into your list]" + "\n" +
                "\t\t" + "3." + "\t" + "deadline ABC /by XYZ" + "\n" +
                "\t\t\t" + "[Add a deadline task, ABC, into your list." +
                " Specify the date/time, XYZ, it needs to be completed by.]" + "\n" +
                "\t\t" + "4." + "\t" + "event ABC /at XYZ" + "\n" +
                "\t\t\t" + "[Add an event task, ABC, into your list."  +
                " Specify the start and end date/time, XYZ.]" + "\n" +
                "\t\t" + "5." + "\t" + "done N" + "\n" +
                "\t\t\t" + "[Mark a task number, N, as done]" + "\n" +
                "\t\t" + "6." + "\t" + "delete N" + "\n" +
                "\t\t\t" + "[Delete a task number, N, from your list]" + "\n" +
                "\t\t" + "7." + "\t" + "bye" + "\n" +
                "\t\t\t" + "[Exit the chatbot]";
    }

    private static String exitMessage() {
        return "\t" + "Bye. Hope to see you again soon!";
    }

    public static String addTaskMessage() {
        return "\t" + "Got it. I've added this task:";
    }

    private static String doneTaskMessage() {
        return "\t" + "Nice! I've marked this task as done:";
    }

    private static String deleteTaskMessage() {
        return "\t" + "Noted. I've removed this task:";
    }

    private static String numOfTasksInList(List<Task> tasksList) {
        return "\t" + "Now you have " + tasksList.size() +
                (tasksList.size() > 1 ? " tasks" : " task") +
                " in the list.";
    }

    private static void echo(String message) {
        String horizontalLine = "\t" + "------------------------------------------------------------";
        String nextLine = "\n";
        String echoMessage = horizontalLine + nextLine +
                message + nextLine + horizontalLine;
        System.out.println(echoMessage);
    }
}
