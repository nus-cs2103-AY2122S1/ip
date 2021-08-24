import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
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

    public Duke(File file) {
        taskList = new ArrayList<>();
        try {
            Scanner dataInput = new Scanner(file);
            String data[];
            boolean isDataCorrupted = false;
            while (dataInput.hasNextLine()) {
                data = dataInput.nextLine().split(" @ ");
                switch (getCommand(data[0])) {
                case DEADLINE:
                    if (data.length != 4) {
                        isDataCorrupted = true;
                        continue;
                    } else {
                        DeadlineTask deadline = new DeadlineTask(data[1], data[2], data[3]);
                        addTask(deadline);
                    }
                    break;
                case EVENT:
                    if (data.length != 4) {
                        isDataCorrupted = true;
                        continue;
                    } else {
                        EventTask event = new EventTask(data[1], data[2], data[3]);
                        addTask(event);
                    }
                    break;
                case TODO:
                    if (data.length != 3) {
                        isDataCorrupted = true;
                        continue;
                    } else {
                        ToDoTask todo = new ToDoTask(data[1], data[2]);
                        addTask(todo);
                    }
                    break;
                case UNKNOWN:
                    isDataCorrupted = true;
                }
            }

            if (isDataCorrupted) {
                System.out.println("____________________________________________________________________________________" +
                        "____________________________________");
                System.out.println("     Data was partially Corrupted");
                System.out.println("____________________________________________________________________________________" +
                        "____________________________________");
            }
        } catch (FileNotFoundException e) {
            System.out.println("____________________________________________________________________________________" +
                    "____________________________________");
            System.out.println(ANSI_RED + "     File Not Found" + ANSI_RESET);
            System.out.println("____________________________________________________________________________________" +
                    "____________________________________");
        }
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
                                        "deadline <Description> /by <dd/MM/yyyy HHmm>");
        } else {
            String[] values = taskDetails.split(" /by ", 2);
            try {
                DeadlineTask deadline = new DeadlineTask(values[0], values[1]);
                addTask(deadline);
                display("Got it. I've added this task:");
                display("  " + deadline);
                display("Now you have " + getTaskListLength() + " tasks in the list.");
            } catch (DateTimeParseException e) {
                throw new DukeException("Incorrect Format of the Deadline Command!!, Correct Format --> " +
                        "deadline <Description> /by <dd/MM/yyyy HHmm>");
            }
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
                                    "Correct Format --> event <Description> /at <dd/MM/yyyy HHmm>");
        } else {
            String[] values = taskDetails.split(" /at ", 2);
            try {
                EventTask event = new EventTask(values[0], values[1]);
                addTask(event);
                display("Got it. I've added this task:");
                display("  " + event);
                display("Now you have " + getTaskListLength() + " tasks in the list.");
            } catch (DateTimeParseException e) {
                throw new DukeException("Incorrect Format of the Event Command!!, " +
                        "Correct Format --> event <Description> /at <dd/MM/yyyy HHmm>");
            }
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

            System.out.println("____________________________________________________________________________________" +
                    "____________________________________");
        } while (!(taskObjective.equals("bye")));

        try {
            File dir = new File("data");
            dir.mkdirs();
            File data = new File(dir, "duke.txt");
            if (data.exists()) {
                data.delete();
            }
            data.createNewFile();
            FileWriter storeData = new FileWriter(data);
            Task task;
            String delimiter = " @ ";
            for(int i = 0; i < getTaskListLength(); i++) {
                task = getTask(i);
                if (task instanceof DeadlineTask) {
                    DeadlineTask deadline = (DeadlineTask) task;
                    storeData.write(deadline.getType() + delimiter + deadline.getIsCompleted() + delimiter
                            + deadline.getDescription() + delimiter + deadline.getDeadline() + "\n");
                } else if (task instanceof EventTask) {
                    EventTask event = (EventTask) task;
                    storeData.write(event.getType() + delimiter + event.getIsCompleted()+ delimiter
                            + event.getDescription() + delimiter + event.getDetails() + "\n");
                } else if (task instanceof ToDoTask) {
                    ToDoTask todo = (ToDoTask) task;
                    storeData.write(todo.getType() + delimiter + todo.getIsCompleted() + delimiter
                            + todo.getDescription() + "\n");
                } else {
                    System.out.println("\n____________________________________________________________________________________" +
                            "____________________________________");
                    System.out.println(ANSI_RED + "     Error Occurred While Storing Data" + ANSI_RESET);
                    System.out.println("____________________________________________________________________________________" +
                            "____________________________________");
                }
            }
            storeData.close();
        } catch (IOException e) {
            System.out.println("\n____________________________________________________________________________________" +
                    "____________________________________");
            System.out.println(ANSI_RED + "     123Error Occurred While Storing Data" + ANSI_RESET);
            System.out.println("____________________________________________________________________________________" +
                    "____________________________________");

            File deletedFile = new File("../../../data/duke.txt");
            deletedFile.delete();
        }
    }

    public static void main(String[] args) {
        File dataFile = new File("data", "duke.txt");
        Duke instance = new Duke();
        if (dataFile.exists()) {
            instance = new Duke(dataFile);
        } else {
            instance = new Duke();
        }
        instance.greet();
        instance.Interact();
    }
}