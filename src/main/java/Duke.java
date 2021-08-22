import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Specifies the type of action to be taken by the duke.
 */
enum ActionType {
    LIST, TODO, DEADLINE, EVENT, DONE, DELETE, BYE, UNRECOGNIZED
}

public class Duke {
    private final List<Task> tasks = new ArrayList<>();
    private boolean isLive = true;

    /**
     * Constructor for a duke.
     */
    public Duke() {
        init();
    }

    /**
     * Shows if the duke is still active.
     *
     * @return whether the duke is still active.
     */
    public boolean isLive() {
        return isLive;
    }

    /**
     * Processes the command input by user.
     *
     * @param command command input of the user.
     * @throws DukeException exceptions when processing the command
     */
    public void processCommand(String command) throws DukeException {
        String action = this.getAction(command);
        ActionType actionType = this.getActionType(action);
        switch (actionType) {
        case LIST:
            this.displayTasks();
            break;
        case TODO: {
            String task = this.getTask(command);
            ToDo toDo = this.createTodo(task);
            this.addTask(toDo);
            saveProgress();
            break;
        }
        case DEADLINE: {
            String task = this.getTask(command);
            DeadLine ddl = this.createDeadLine(task);
            this.addTask(ddl);
            saveProgress();
            break;
        }
        case EVENT: {
            String task = this.getTask(command);
            Event event = this.createEvent(task);
            this.addTask(event);
            saveProgress();
            break;
        }
        case DONE: {
            String task = this.getTask(command);
            int taskIdx = this.getTaskIdx(this.getAction(command), task);
            this.markTaskAsDone(taskIdx - 1);
            saveProgress();
            break;
        }
        case DELETE: {
            String task = this.getTask(command);
            int taskIdx = this.getTaskIdx(this.getAction(command), task);
            this.deleteTask(taskIdx - 1);
            saveProgress();
            break;
        }
        case BYE: {
            saveProgress();
            this.bye();
            this.isLive = false;
            break;
        }
        default:
            throw new UnrecognizableCommandException();
        }
    }

    private void init() {
        loadFromDisk();
        String greet = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greet);
    }

    private void displayTasks() {
        System.out.println("___________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
        System.out.println("___________________________________________________\n");
    }

    private void loadFromDisk() {
        File folder = new File("data");
        if (folder.isDirectory()) {
            try {
                File file = new File("data/duke.txt");
                Scanner s = new Scanner(file);
                readTasks(s);
            } catch (FileNotFoundException e) {
                //create the file if it does not exist.
                createFile();
            }
        } else {
            //create both directory and file if they do not exist.
            createDataFolder();
            createFile();
        }
    }

    private void createFile() {
        try {
            File dukeFile = new File("data/duke.txt");
            dukeFile.createNewFile();
        } catch (IOException e) {
            System.out.println(e);
            System.exit(-1);
        }
    }

    private void createDataFolder() {
        File dataFolder = new File("data");
        boolean isFolderCreated = dataFolder.mkdir();
        if (!isFolderCreated) {
            System.out.println("Could not create data directory");
            System.exit(-1);
        }
    }

    private void readTasks(Scanner scanner) {
        while (scanner.hasNext()) {
            String taskString = scanner.nextLine();
            Task task = taskStringToTask(taskString);
            this.tasks.add(task);
        }
        saveProgress();
        scanner.close();
    }

    private Task taskStringToTask(String taskString) {
        String[] taskInfo = taskString.split(" \\| ");
        String taskType = taskInfo[0];
        boolean isDone = taskInfo[1].equals("[X]");
        String taskDescription = taskInfo[2];
        switch (taskType) {
        case "[D]":
            return new DeadLine(taskDescription, taskInfo[3], isDone);
        case "[E]":
            return new Event(taskDescription, taskInfo[3], isDone);
        default:
            return new ToDo(taskDescription, isDone);
        }
    }

    private void saveProgress() {
        try {
            FileWriter fw = new FileWriter("data/duke.txt");
            for (int i = 0; i < tasks.size(); i++) {
                fw.write(String.valueOf(tasks.get(i)) + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("No File Found");
            System.exit(-1);
        }

    }

    private void bye() {
        System.out.println("___________________________________________________");
        String byeCommand = "Bye. Hope to see you again soon!";
        System.out.println(byeCommand);
        System.out.println("___________________________________________________\n");
    }

    private void addTask(Task task) {
        this.tasks.add(task);
        String output = "Added: " + task.toString();
        System.out.println("___________________________________________________");
        System.out.println(output);
        System.out.println("Now you have " + this.tasks.size() + " task" + ((tasks.size() <= 1) ? "" : "s") + " in the list");
        System.out.println("___________________________________________________\n");
    }

    private void markTaskAsDone(int taskIdx) {
        Task task = this.tasks.get(taskIdx);
        task.markAsDone();
        System.out.println("___________________________________________________");
        System.out.println(" Nice! I've marked this task as done:\n" + task);
        System.out.println("___________________________________________________\n");
    }

    private String getAction(String command) {
        return command.split(" ")[0];
    }

    private ActionType getActionType(String action) {
        switch (action) {
        case "list":
            return ActionType.LIST;
        case "done":
            return ActionType.DONE;
        case "deadline":
            return ActionType.DEADLINE;
        case "event":
            return ActionType.EVENT;
        case "todo":
            return ActionType.TODO;
        case "delete":
            return ActionType.DELETE;
        case "bye":
            return ActionType.BYE;
        default:
            return ActionType.UNRECOGNIZED;
        }
    }

    private String getTask(String command) throws EmptyDescriptionException {
        String action = this.getAction(command);
        try {
            return command.substring(action.length() + 1);
        } catch (StringIndexOutOfBoundsException e) {
            throw new EmptyDescriptionException(action);
        }

    }

    private ToDo createTodo(String command) {
        return new ToDo(command, false);
    }

    private DeadLine createDeadLine(String command) throws UnrecognizableCommandException {
        if (!command.contains(" /by ")) {
            throw new UnrecognizableCommandException();
        }
        try {
            String[] lst = command.split(" /by ");
            String description = lst[0];
            String ddl = lst[1];
            return new DeadLine(description, ddl, false);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new UnrecognizableCommandException();
        }

    }

    private Event createEvent(String command) throws UnrecognizableCommandException {
        if (!command.contains(" /at ")) {
            throw new UnrecognizableCommandException();
        }
        try {
            String[] lst = command.split(" /at ");
            String description = lst[0];
            String period = lst[1];
            return new Event(description, period, false);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new UnrecognizableCommandException();
        }
    }

    private int getTaskIdx(String action, String idxString) throws InvalidTaskIndexException, NoSuchTaskException {
        int taskIdx;
        try {
            taskIdx = Integer.parseInt(idxString);
        } catch (NumberFormatException e) {
            throw new InvalidTaskIndexException(action);
        }

        if (taskIdx > 0 && taskIdx <= this.tasks.size()) {
            return taskIdx;
        } else {
            throw new NoSuchTaskException();
        }
    }

    private void deleteTask(int taskIdx) {
        Task toBeDeleted = this.tasks.get(taskIdx);
        System.out.println("___________________________________________________");
        System.out.println(" Noted. I've removed this task:");
        System.out.println(toBeDeleted);
        tasks.remove(toBeDeleted);
        System.out.println("Now you have " + this.tasks.size() + " task" + ((tasks.size() <= 1) ? "" : "s") + " in the list");
        System.out.println("___________________________________________________\n");
    }
}
