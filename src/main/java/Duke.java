import java.io.*;
import java.util.*;

public class Duke {
    private final DukeCommandFormatter commandFormatter;
    private final List<DukeTask> taskList;
    private static final String SAVE_FILE_LOCATION = "duke-task-list.txt";

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
        this.taskList = loadTaskList();
    }

    private List<DukeTask> loadTaskList() {
        try {
            FileInputStream fileInputStream = new FileInputStream(SAVE_FILE_LOCATION);
            Scanner scanner = new Scanner(fileInputStream);
            List<DukeTask> tasks = new ArrayList<>();
            while (scanner.hasNext()) {
                tasks.add(DukeTask.fromSerializedString(scanner.nextLine()));
            }
            scanner.close();
            fileInputStream.close();
            return tasks;
        } catch (IOException e) {
            return new ArrayList<>();
        } catch (TaskParseException e) {
            output(String.format("Error when reading %s; file ignored", SAVE_FILE_LOCATION));
            return new ArrayList<>();
        }
    }

    private static void saveTaskList(List<DukeTask> taskList) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(SAVE_FILE_LOCATION);
            for (DukeTask task: taskList) {
                fileOutputStream.write(task.toSerializedString().getBytes());
                fileOutputStream.write("\n".getBytes());
            }
            fileOutputStream.close();
        } catch (IOException e) {
            // File not found
            e.printStackTrace();
        }
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
        taskList.add(task);
        saveTaskList(taskList);
    }

    public DukeTask removeTaskAt(int index) {
        DukeTask task = taskList.remove(index);
        saveTaskList(taskList);
        return task;
    }

    public void output(String outputLine) {
        commandFormatter.printOutputLine(outputLine);
    }

    public List<DukeTask> getTaskList() {
        return taskList;
    }

    public DukeTask markTaskAsDoneAt(int index) {
        DukeTask task = taskList.get(index);
        task.markAsDone();
        saveTaskList(taskList);
        return task;
    }

    public boolean isTaskDoneAt(int index) {
        return taskList.get(index).isDone;
    }
}
