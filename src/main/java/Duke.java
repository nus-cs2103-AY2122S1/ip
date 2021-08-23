import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public enum TaskObjective {
        BYE,
        DEADLINE,
        DELETE,
        DONE,
        EVENT,
        LIST,
        TODO,
        UNKNOWN
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_RED = "\u001B[31m";

    private final List<Task> taskList;

    public Duke() {
        taskList = new ArrayList<>();
    }

    private void addTask(Task task) {
        taskList.add(task);
    }

    private Task deleteTaskAtIndex(int index) {
        return taskList.remove(index);
    }

    private Task getTask(int index) {
        return taskList.get(index);
    }

    private int getTaskListLength() {
        return taskList.size();
    }

    private void completeTask(int index) {
        taskList.get(index).markAsCompleted();
    }

    private void printTaskList() {
        if (taskList.size() > 0) {
            display("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                display((i + 1) + "." + getTask(i));
            }
        } else {
            display("The Task List is Empty");
        }
    }

    private void greet() {
        System.out.println("\n");
        String buffer = "     ";
        String logo =   buffer + " ____        _\n"
                + buffer + "|  _ \\ _   _| | _____\n"
                + buffer + "| | | | | | | |/ / _ \\\n"
                + buffer + "| |_| | |_| |   <  __/\n"
                + buffer + "|____/ \\__,_|_|\\_\\___|\n";
        display("Hello from\n" + logo);

        System.out.println("____________________________________________________________________________________" +
                "____________________________________");
        display("Hello! I'm Duke");
        display("What can I do for you?");
        System.out.println("____________________________________________________________________________________" +
                "____________________________________\n");
    }

    private void goodbye(String taskDetails) throws DukeException {
        if ((taskDetails != null) && (taskDetails.equals(""))) {
            display("Bye. Hope to see you again soon! \\_(\"v\")_/");
        } else {
            throw new DukeException("OOPS! I'm sorry, but I don't know that command");
        }
    }

    private void addDeadline(String taskDetails) throws DukeException {
        if ((taskDetails == null) || (taskDetails.equals("") || !(taskDetails.contains(" /by ")))) {
            throw new DukeException("Incorrect Format of the Deadline Command!!, Correct Format --> " +
                                        "deadline <Description> /at <Deadline>");
        } else {
            String[] values = taskDetails.split(" /by ", 2);
            DeadlineTask deadline = new DeadlineTask(values[0], values[1]);
            addTask(deadline);
            display("Got it. I've added this task:");
            display("  " + deadline);
            display("Now you have " + getTaskListLength() + " tasks in the list.");
        }
    }

    private void deleteTask(String taskDetails) throws DukeException {
        try {
            int index = Integer.parseInt(taskDetails) - 1;
            if ((index >= 0) && (index < taskList.size())) {
                display("Noted. I've removed this task:");
                display("  " + deleteTaskAtIndex(index).toString());
            } else {
                int numberOfTasks = getTaskListLength();
                if (numberOfTasks == 0) {
                    throw new DukeException("Incorrect Index!! There are no Tasks in the Task List");
                } else {
                    throw new DukeException("Incorrect Index!! There are " + numberOfTasks + " tasks in the Task List");
                }
            }
        } catch (NumberFormatException e) {
            throw new DukeException("Incorrect Format of the Delete Command!!, Correct Format --> delete <index>");
        }
    }

    private void markTaskAsCompleted(String taskDetails) throws DukeException {
        try {
            int index = Integer.parseInt(taskDetails) - 1;
            if ((index >= 0) && (index < taskList.size())) {
                completeTask(index);
                display("Nice! I've marked this task as done:");
                display("  " + getTask(index));
            } else {
                int numberOfTasks = getTaskListLength();
                if (numberOfTasks == 0) {
                    throw new DukeException("Incorrect Index!! There are no Tasks in the Task List");
                } else {
                    throw new DukeException("Incorrect Index!! There are " + numberOfTasks + " tasks in the Task List");
                }
            }
        } catch (NumberFormatException e) {
            throw new DukeException("Incorrect Format of the Done Command!!, Correct Format --> done <index>");
        }
    }

    private void addEvent(String taskDetails) throws DukeException {
        if ((taskDetails == null) || (taskDetails.equals("") || !(taskDetails.contains(" /at ")))) {
            throw new DukeException("Incorrect Format of the Event Command!!, " +
                                    "Correct Format --> event <Description> /at <Details>");
        } else {
            String[] values = taskDetails.split(" /at ", 2);
            EventTask event = new EventTask(values[0], values[1]);
            addTask(event);
            display("Got it. I've added this task:");
            display("  " + event);
            display("Now you have " + getTaskListLength() + " tasks in the list.");
        }
    }

    private void displayTaskList(String taskDetails) throws DukeException {
        if (taskDetails != null && taskDetails.equals("")) {
            printTaskList();
        } else {
            throw new DukeException("OOPS! I'm sorry, but I don't know that command");
        }
    }

    private void addTodo(String taskDetails) throws DukeException {
        if ((taskDetails == null) || (taskDetails.equals(""))) {
            throw new DukeException("Incorrect Format of the ToDo Command!!, Correct Format --> todo <Description>");
        } else {
            ToDoTask todo = new ToDoTask(taskDetails);
            addTask(todo);
            display("Got it. I've added this task:");
            display("  " + todo);
            display("Now you have " + getTaskListLength() + " tasks in the list.");
        }
    }

    private void display(String output) {
        System.out.println(ANSI_CYAN + "     " + output + ANSI_RESET);
    }

    private TaskObjective getCommand(String command) {
        try {
            if (command != null) {
                return TaskObjective.valueOf(command.toUpperCase());
            } else {
                return TaskObjective.UNKNOWN;
            }
        } catch (IllegalArgumentException e) {
            return TaskObjective.UNKNOWN;
        }
    }

    private void Interact() {
        Scanner getInput = new Scanner(System.in);
        String taskDescription, taskObjective, taskDetails;

        do {
            taskDescription = getInput.nextLine().trim();
            String[] task = taskDescription.split(" ", 2);
            taskObjective = task[0];
            taskDetails = task.length > 1 ? task[1].trim() : "";
            System.out.println("____________________________________________________________________________________" +
                    "____________________________________");
            try {
                switch (getCommand(taskObjective)) {
                    case BYE: {
                        goodbye(taskDetails);
                        break;
                    }

                    case DEADLINE: {
                        addDeadline(taskDetails);
                        break;
                    }

                    case DELETE: {
                        deleteTask(taskDetails);
                        break;
                    }

                    case DONE: {
                        markTaskAsCompleted(taskDetails);
                        break;
                    }

                    case EVENT: {
                        addEvent(taskDetails);
                        break;
                    }

                    case LIST: {
                        displayTaskList(taskDetails);
                        break;
                    }

                    case TODO: {
                        addTodo(taskDetails);
                        break;
                    }

                    case UNKNOWN: {
                        throw new DukeException("OOPS! I'm sorry, but I don't know that command");
                    }
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

//            try {
//                if ((TaskObjective.valueOf(taskObjective) == TaskObjective.bye) && (taskDetails.equals(""))) {
//                    display("Bye. Hope to see you again soon! \\_(\"v\")_/");
//
//                } else if((taskObjective.equals("list")) && (taskDetails.equals(""))) {
//                    if (taskList.size() == 0) {
//                        throw new DukeException("OOPS! There are no Tasks in the System");
//                    } else {
//                        display("Here are the tasks in your list:");
//                        for (int i = 0; i < taskList.size(); i++) {
//                            display((i + 1) + "." + taskList.get(i).toString());
//                        }
//                    }
//                } else if (taskObjective.equals("done")) {
//                    try {
//                        int index = Integer.parseInt(taskDetails) - 1;
//                        if ((index >= 0) && (index < taskList.size())) {
//                            taskList.get(index).markAsCompleted();
//                            display("Nice! I've marked this task as done:");
//                            display("  " + taskList.get(index).toString());
//                        } else {
//                            throw new DukeException("OOPS! The Wrong Index! The Number of Tasks in the list is "
//                                    + taskList.size());
//                        }
//                    } catch (NumberFormatException e) {
//                        throw new DukeException("OOPS! The done Command should be followed at a Task Index!");
//                    }
//                } else if (taskObjective.equals("delete")) {
//                    try {
//                        int index = Integer.valueOf(taskDetails) - 1;
//                        if (index >= 0 && index < taskList.size()) {
//                            Task removed = taskList.remove(index);
//                            display("Noted. I've removed this task:");
//                            display("  " + removed.toString());
//                        } else {
//                            throw new DukeException("OOPS! The Wrong Index! The Number of Tasks in the list is "
//                                    + taskList.size());
//                        }
//                    } catch (NumberFormatException e) {
//                        throw new DukeException("OOPS! The delete Command should be followed at a Task Index!");
//                    }
//                } else if (taskObjective.equals("todo")) {
//                    if (!(taskDetails.equals(""))) {
//                        ToDoTask todo = new ToDoTask(taskDetails);
//                        taskList.add(todo);
//                        display("Got it. I've added this task:");
//                        display("  " + todo);
//                        display("Now you have " + taskList.size() + " tasks in the list.");
//                    } else {
//                        throw new DukeException("OOPS! Please provide the Description");
//                    }
//                } else if (taskObjective.equals("deadline")) {
//                    if (taskDetails.equals("") || (((!(taskDetails.equals("")) && !(taskDetails.contains(" /at"))) &&
//                        (!(taskDetails.equals("")) && !(taskDetails.contains("/at ")))) &&
//                        (!(taskDetails.equals("")) && (taskDetails.contains("/at"))))) {
//                        throw new DukeException("OOPS! Please provide the Description and the Deadline");
//                    } else if(!(taskDetails.equals("")) && (taskDetails.contains(" /at"))) {
//                        throw new DukeException("OOPS! Please provide the Description");
//                    } else if(!(taskDetails.equals("")) && !(taskDetails.contains("/at "))) {
//                        throw new DukeException("OOPS! Please provide the Deadline");
//                    } else if(!(taskDetails.equals("")) && (taskDetails.contains(" /at "))) {
//                        String[] values = taskDetails.split(" /at ", 2);
//                        DeadlineTask deadline = new DeadlineTask(values[0], values[1]);
//                        taskList.add(deadline);
//                        display("Got it. I've added this task:");
//                        display("  " + deadline);
//                        display("Now you have " + taskList.size() + " tasks in the list.");
//                    } else {
//                        throw new DukeException("OOPS! I'm sorry, but I don't know that command");
//                    }
//                } else if (taskObjective.equals("event")) {
//                    if (taskDetails.equals("") || (((!(taskDetails.equals("")) && !(taskDetails.contains(" /at"))) &&
//                            (!(taskDetails.equals("")) && !(taskDetails.contains("/at ")))) &&
//                            (!(taskDetails.equals("")) && (taskDetails.contains("/at"))))) {
//                        throw new DukeException("OOPS! Please provide the Description and the Details");
//                    } else if(!(taskDetails.equals("")) && !(taskDetails.contains(" /at"))) {
//                        throw new DukeException("OOPS! Please provide the Description");
//                    } else if(!(taskDetails.equals("")) && !(taskDetails.contains("/at "))) {
//                        throw new DukeException("OOPS! Please provide the Details");
//                    } else if(!(taskDetails.equals("")) && (taskDetails.contains(" /at "))) {
//                        String[] values = taskDetails.split(" /at ", 2);
//                        EventTask event = new EventTask(values[0], values[1]);
//                        taskList.add(event);
//                        display("Got it. I've added this task:");
//                        display("  " + event);
//                        display("Now you have " + taskList.size() + " tasks in the list.");
//                    } else {
//                        throw new DukeException("OOPS! I'm sorry, but I don't know that command");
//                    }
//                } else {
//                    throw new DukeException("OOPS! I'm sorry, but I don't know that command");
//                }
//            } catch (DukeException e) {
//                System.out.println(e.getMessage());
//            }

            System.out.println("____________________________________________________________________________________" +
                                   "____________________________________");
        } while (!(taskObjective.equals("bye")));
    }

    public static void main(String[] args) {
        Duke instance = new Duke();
        instance.greet();
        instance.Interact();
    }
}