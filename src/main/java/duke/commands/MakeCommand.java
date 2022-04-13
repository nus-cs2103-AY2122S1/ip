package duke.commands;

import duke.exceptions.AuguryException;
import duke.exceptions.InvalidActionException;
import duke.exceptions.UnknownCommandException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskFactory;
import duke.tasks.TaskList;

/**
 * The {@code MakeCommand} class extends from {@code Command}, which
 * creates a new {@code Task} in {@code Augury}.
 */
public class MakeCommand extends Command {

    private final String[] args;

    /**
     * Initializes a {@code MakeCommand} with user-supplied {@code String[] arguments}.
     *
     * @param arguments User-supplied arguments
     */
    public MakeCommand(String[] arguments) {
        this.args = arguments;
    }

    /**
     * Creates a {@code Task} based on user-supplied arguments.
     *
     * @param tasks {@code TaskList} that the {@code MakeCommand} writes to.
     * @param storage {@code Storage} instance that gets updated.
     * @return {@code String} containing results of {@code Task} creation.
     * @throws UnknownCommandException if invalid command input is provided.
     * @throws InvalidActionException if invalid {@code Task} details are provided.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws AuguryException {
        String args = this.args[0];

        // type will be in the first word
        String taskType = args.split(" ")[0];

        String taskDescription;
        if (args.length() == taskType.length()) {
            taskDescription = ""; // no description provided
        } else {
            taskDescription = args.replace(taskType + " ", "");
        }

        return createTask(tasks, storage, taskType, taskDescription);
    }

    private String createTask(TaskList tasks, Storage storage,
                              String taskType, String taskDescription) throws AuguryException {
        try {
            TaskFactory tf = new TaskFactory();
            Task newTask = tf.createTask(taskType, taskDescription);

            if (newTask == null) {
                throw new UnknownCommandException("Invalid command entered when creating task.");
            }
            if (tasks.hasTaskWithDescription(newTask.getDescription())) {
                return "A task matching this description already exists! Task was not created.";
            }

            String result = tasks.addTaskAndAnnounce(taskType, newTask);
            storage.saveTaskListToStorage(tasks);
            return result;
        } catch (AuguryException e) {
            throw new AuguryException(e.getMessage());
        }
    }
}
