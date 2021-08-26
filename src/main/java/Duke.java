import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private enum Command {
        TODO("todo"),
        EVENT("event"),
        DEADLINE("deadline"),
        DONE("done"),
        DELETE("delete"),
        LIST("list"),
        BYE("bye");

        private final String name;

        Command(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
        public Boolean equalsInput(String input) {
            return name.equals(input);
        }
    }

    public static void main(String[] args) {
        loadTasks();
        System.out.println("Hello...\nWhat do you want?\n");
        Scanner myScanner = new Scanner(System.in);
        String answer = myScanner.nextLine();

        while (!answer.equals("bye")) {
            String[] parts = answer.split(" ");
            String taskType = parts[0];
            try {
                handleUserCommand(taskType, answer);
            } catch (DukeException e){
                System.out.println(e.getMessage());
            }
            answer = myScanner.nextLine();
        }
        myScanner.close();
        saveTasks();
        System.out.println("Whatever...");
    }

    private static void handleUserCommand(String taskType, String answer) throws DukeException {
        Command command = checkValidTaskType(taskType);
        switch (command) {
            case DONE:
                int taskIndex = getValidTaskIndex(answer);
                markTaskAsDone(taskIndex);
                break;
            case DELETE:
                taskIndex = getValidTaskIndex(answer);
                deleteTask(taskIndex);
                break;
            case LIST:
                printTaskList();
                break;
            default:
                addNewTask(command, answer);
        }
    }
    
    private static Task parseTask(String fileLine, int lineNo) throws DukeException {
        try {
            String[] parts = fileLine.split(" \\| ");
            String taskType = parts[0];
            int isDoneInt = Integer.parseInt(parts[1]);
            boolean isDone = (isDoneInt == 1);
            String description = parts[2];
            switch(taskType) {
                case "T":
                    return new Todo(description, isDone);
                case "D":
                    String date = parts[3];
                    return new Deadline(description, date, isDone);
                case "E":
                    date = parts[3];
                    return new Event(description, date, isDone);
                default:
                    throw new DukeException("Could not parse task type on file line " + lineNo);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Task details are missing on file line " + lineNo);
        }
    }

    private static void printTaskList() {
        if (tasks.size() == 0) {
            System.out.println("There are no tasks in the list.");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    private static void addNewTask(Command command, String answer) throws DukeException {
        String taskDetails = answer.substring(answer.indexOf(" ") + 1);
        if (!answer.contains(" ") || taskDetails.isEmpty()) {
            throw new DukeException("Description of " + command.getName() + " cannot be empty");
        }
        switch (command) {
            case TODO:
                addTodo(taskDetails);
                break;
            case EVENT:
                addEvent(taskDetails);
                break;
            case DEADLINE:
                addDeadline(taskDetails);
                break;
            default:
                throw new DukeException("-_-+ Invalid command.");
        }
        System.out.println("Got it. I've added this task:\n\t" + tasks.get(tasks.size() - 1));
        printTasksCount();
    }

    private static void addTodo(String taskDetails) {
        tasks.add(new Todo(taskDetails, false));
    }

    private static void addEvent(String taskDetails) throws DukeException {
        String[] parts = taskDetails.split(" /at ");
        if (parts.length < 2) {
            throw new DukeException("Event descriptions must contain /at [time]");
        }
        String description = parts[0];
        String at = parts[1];
        tasks.add(new Event(description, at, false));
    }

    private static void addDeadline(String taskDetails) throws DukeException {
        String[] parts = taskDetails.split(" /by ");
        if (parts.length < 2) {
            throw new DukeException("Deadline descriptions must contain /by [time]");
        }
        String description = parts[0];
        String by = parts[1];
        tasks.add(new Deadline(description, by, false));
    }

    private static void markTaskAsDone(int taskIndex) throws DukeException {
        tasks.get(taskIndex).markAsDone();
        System.out.println("I've marked this task as done:");
        System.out.println("\t" + tasks.get(taskIndex));
    }

    private static void deleteTask(int taskIndex) throws DukeException {
        Task deletedTask = tasks.get(taskIndex);
        tasks.remove(deletedTask);
        System.out.println("Noted. I've removed this task:\n\t" + deletedTask);
        printTasksCount();
    }

    private static int getValidTaskIndex(String answer) throws DukeException {
        String taskNo = answer.substring(answer.indexOf(" ") + 1);
        try {
            int taskIndex = Integer.parseInt(taskNo) - 1;
            int taskCount = tasks.size();
            if (taskCount == 0) {
                throw new DukeException("There are no tasks in the list.");
            } else if (taskIndex < 0 || taskIndex >= taskCount) {
                throw new DukeException("Invalid task number. Task number should be between 1 and " + taskCount
                                        + " inclusive.");
            }
            return taskIndex;
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid task number. Sample input with correct format: [command] [taskNo]"
                                    + " eg. 'done 2'");
        }
    }

    private static Command checkValidTaskType(String taskType) throws DukeException {
        for (Command c : Command.values()) {
            if (c.equalsInput(taskType)) return c;
        }
        throw new DukeException("Unknown command...");
    }

    private static void printTasksCount() {
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
    
    private static File getStoreFile() throws IOException {
        String filePath = "./data/duke.txt";
        File store = new File(filePath);
        String dirPath = store.getParent();
        File directory = new File(dirPath);
        // creates parent directories if they do not exist
        directory.mkdirs();
        // creates file if it does not exist
        store.createNewFile();
        return store;
    }
    
    private static void loadTasks() {
        try {
            File store = getStoreFile();
            BufferedReader reader = new BufferedReader(new FileReader(store));
            String fileLine = reader.readLine();
            int lineNo = 1;
            while (fileLine != null) {
                Task task = parseTask(fileLine, lineNo);
                tasks.add(task);
                lineNo++;
                fileLine = reader.readLine();
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private static void saveTasks() {
        try {
            File store = getStoreFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(store));
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                writer.write(task.toFileString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
