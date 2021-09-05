package iris;

import iris.command.Command;

/**
 * Represents an Iris object
 */
public class Iris {
    private final Storage storage;
    private final TaskList tasks = new TaskList();

    /**
     * Creates a new Iris instance
     *
     * @param filePath Path to file containing tasks
     */
    public Iris(String filePath) {
        storage = new Storage(filePath);
        try {
            storage.readTasks(tasks);
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
            return command.runAndStore(tasks, storage);
        } catch (IrisException exception) {
            return exception.getMessage();
        }
    }
}
