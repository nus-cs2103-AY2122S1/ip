package iris;

import iris.command.Command;

/**
 * Represents an Iris object
 */
public class Iris {
    private final Storage STORAGE;
    private final TaskList TASKS = new TaskList();

    /**
     * Creates a new Iris instance
     *
     * @param filePath Path to file containing tasks
     */
    public Iris(String filePath) {
        STORAGE = new Storage(filePath);
        try {
            STORAGE.readTasks(TASKS);
        } catch (IrisException exception) {
            System.out.printf("An error has occurred: %s%n", exception.toString());
        }
    }

    public Iris() {
        this("data/tasks.txt");
    }

    public String getResponse(String rawCommand) {
        try {
            Command command = Parser.parse(rawCommand);
            return command.runAndStore(TASKS, STORAGE);
        } catch (IrisException exception) {
            return exception.getMessage();
        }
    }
}
