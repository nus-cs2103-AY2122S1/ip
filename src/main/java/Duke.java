import java.util.*;

public class Duke {
    private static final String SAVE_FILE_LOCATION = "duke-task-list.txt";
    private final DukeCommandFormatter commandFormatter;
    private final TaskList taskList;
    private final Storage storage;

    final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }

    public Duke() {
        this.commandFormatter = new DukeCommandFormatter(System.in, System.out);
        this.storage = new Storage(SAVE_FILE_LOCATION);
        TaskList taskList;
        try {
            taskList = storage.loadTaskList();
        } catch (DukeStorageException e) {
            output(e.getMessage());
            taskList = new TaskList();
        }
        this.taskList = taskList;
    }

    public void start() {
        printWelcomeMessage();
        try {
            DukeCommand.HELP.apply(this, "", Map.of());
        } catch (InvalidCommandException e) {
            e.printStackTrace();
        }
        boolean shouldListen;
        do {
            String command = commandFormatter.nextCommand();
            shouldListen = processCommand(command);
        } while (shouldListen);
        printExitMessage();
    }

    public void printWelcomeMessage() {
        commandFormatter.printOutputLine("Hello from\n" + logo);
    }

    public void printExitMessage() {
        commandFormatter.printOutputLine("Goodbye from\n" + logo);
    }

    /**
     * Processes the given command (a line). Returns true if more commands are to be listened to.
     *
     * @param command The command to be processed.
     * @return If Duke should continue listening to commands.
     */
    public boolean processCommand(String command) {
        // Get the longest duke command that matches to command
        Optional<DukeCommand> dukeCommand = DukeCommand.getClosestMatch(command);
        if (dukeCommand.isPresent()) {
            DukeCommand actualCommand = dukeCommand.get();
            String arguments = command.substring(actualCommand.getName().length());
            String[] tokens = arguments.split("/");
            String positionalArg = tokens[0].trim();
            Map<String, String> namedArgs = new HashMap<>();
            for (int i = 1; i < tokens.length; i++) {
                String[] namedArg = tokens[i].trim().split(" ", 2);
                namedArgs.put(namedArg[0].trim(), namedArg[1].trim());
            }
            try {
                return dukeCommand.get().apply(this, positionalArg, namedArgs);
            } catch (InvalidCommandException e) {
                commandFormatter.printOutputLine(String.format("Error in \"%s\": %s\nType \"help %s\" to view proper usage of the command.", actualCommand.getName(), e.getMessage(), actualCommand.getName()));
                return true;
            }
        } else {
            commandFormatter.printOutputLine(String.format("Unknown command: %s. Type \"help\" for a list of available commands.", command));
            return true;
        }
    }

    public void addTask(DukeTask task) {
        taskList.addTask(task);
        storage.saveTaskList(taskList);
    }

    public DukeTask removeTaskAt(int index) {
        DukeTask task = taskList.removeTaskAt(index);
        storage.saveTaskList(taskList);
        return task;
    }

    public void output(String outputLine) {
        commandFormatter.printOutputLine(outputLine);
    }

    public List<DukeTask> getTaskList() {
        List<DukeTask> tasks = new ArrayList<>();
        for (DukeTask task: taskList.getTasks()) {
            tasks.add(task);
        }
        return tasks;
    }

    public DukeTask markTaskAsDoneAt(int index) {
        DukeTask task = taskList.getTaskAt(index);
        task.markAsDone();
        storage.saveTaskList(taskList);
        return task;
    }

    public boolean isTaskDoneAt(int index) {
        return taskList.getTaskAt(index).isDone;
    }
}
