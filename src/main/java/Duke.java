import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String LINE = "-------------------------------------------------------";
    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        printWithLines("Hello! I'm 8-Bit!\nWhat can I do for you?");
        getUserInput();
    }

    private static void printWithLines(String msg) {
        System.out.println(LINE + "\n" + msg + "\n" + LINE);
    }

    private static void getUserInput() {
        Scanner sc = new Scanner(System.in);

        String command = sc.nextLine();
        while (!command.equals("bye")) {
            processInput(command);
            command = sc.nextLine();
        }

        printWithLines("Bye. Hope to see you again soon!");
        sc.close();
    }

    private static void processInput(String msg) {
        String[] commands = msg.split(" ");

        try {
            switch (commands[0]) {
                case "list":
                    processList(msg);
                    break;
                case "done":
                    processDone(msg);
                    break;
                case "todo":
                    processToDo(msg);
                    break;
                case "deadline":
                    processDeadline(msg);
                    break;
                case "event":
                    processEvent(msg);
                    break;
                case "delete":
                    processDelete(msg);
                    break;
                default:
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :(");
            }
        } catch (DukeException e) {
            printWithLines(e.toString());
        }
    }

    private static void addTask(Task task) {
        tasks.add(task);

        printWithLines("Got it. I've added this task:\n  " + task
                + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    private static void processList(String msg) throws DukeException {
        if (msg.split(" ").length > 1) { // extra words after "list"
            throw new DukeException("OOPS!!! Please remove words after \"list\"");
        }

        StringBuilder listOfTasks = new StringBuilder();
        if (tasks.size() == 0) {
            listOfTasks = new StringBuilder("There are no tasks in your list currently.");
            printWithLines(listOfTasks.toString());
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                String newTask = (i + 1) + ". " + tasks.get(i) + "\n";
                listOfTasks.append(newTask);
            }
            printWithLines("Here are the tasks in your list:\n" + listOfTasks);
        }
    }

    private static void processDone(String msg) throws DukeException {
        if (msg.split(" ").length != 2) { // not in the format "done <integer>"
            throw new DukeException("OOPS!!! Please enter in this format:\ndone <integer>");
        }
        try {
            Integer.parseInt(msg.split(" ")[1]);
        } catch (NumberFormatException e) { // not integer
            throw new DukeException("OOPS!!! Please enter in this format:\ndone <integer>");
        }

        int index = Integer.parseInt(msg.split(" ")[1]) - 1;
        if (index >= tasks.size() || index < 0) { // number exceeding no. of tasks in list or negative
            throw new DukeException("OOPS!!! Task " + (index + 1) + " does not exist.");
        }
        tasks.get(index).markAsDone();
        printWithLines("Great job on completing this task!\n" + tasks.get(index).toString());
    }

    private static void processToDo(String msg) throws DukeException {
        if (msg.split(" ").length < 2) { // missing description
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }

        String todoDescription = msg.substring(5);
        ToDo todo = new ToDo(todoDescription);
        addTask(todo);
    }

    private static void processDeadline(String msg) throws DukeException {
        if (msg.split(" ").length == 1 // missing description and deadline
                || msg.substring(9).trim().split(" /by ").length < 2) { // missing either description or deadline
            throw new DukeException("OOPS!!! Please enter your deadline in this format:\n"
                    + "deadline <description> /by <date/time>");
        }

        String[] descriptionAndBy = msg.substring(9).split(" /by ");
        String deadlineDescription = descriptionAndBy[0];
        String deadlineBy = descriptionAndBy[1];
        Deadline deadline = new Deadline(deadlineDescription, deadlineBy);
        addTask(deadline);
    }

    private static void processEvent(String msg) throws DukeException {
        if (msg.split(" ").length == 1 // missing description and date
                || msg.substring(6).trim().split(" /at ").length < 2) { // missing either description or date
            throw new DukeException("OOPS!!! Please enter your event in this format:\n"
                    + "event <description> /at <date/time>");
        }

        String[] descriptionAndAt = msg.substring(6).split(" /at ");
        String eventDescription = descriptionAndAt[0];
        String eventAt = descriptionAndAt[1];
        Event event = new Event(eventDescription, eventAt);
        addTask(event);
    }

    private static void processDelete(String msg) throws DukeException {
        if (msg.split(" ").length != 2) { // not in the format "delete <integer>"
            throw new DukeException("OOPS!!! Please enter in this format:\ndelete <integer>");
        }
        try {
            Integer.parseInt(msg.split(" ")[1]);
        } catch (NumberFormatException e) { // not integer
            throw new DukeException("OOPS!!! Please enter in this format:\ndelete <integer>");
        }

        int index = Integer.parseInt(msg.split(" ")[1]) - 1;
        if (index >= tasks.size() || index < 0) { // number exceeding no. of tasks in list or negative
            throw new DukeException("OOPS!!! Task " + (index + 1) + " does not exist.");
        }
        Task deletedTask = tasks.remove(index);
        printWithLines("Noted. I've removed this task:\n  " + deletedTask
                + "\nNow you have " + tasks.size() + " tasks in the list.");
    }
}
