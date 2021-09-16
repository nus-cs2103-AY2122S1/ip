package duke.command;

import java.io.IOException;
import java.util.List;

import duke.exception.DukeException;
import duke.storage.ArchiveStorage;
import duke.storage.MainStorage;
import duke.task.TaskList;

/**
 * This class encapsulates the logic of the commands in the application.
 */
public class Command {
    /**
     * Handles the list command.
     *
     * @param tasks The current task list.
     * @return The output string to be shown to the user.
     */
    public static String list(TaskList tasks) {
        return tasks.list();
    }

    /**
     * Handles the done command.
     *
     * @param tasks The current task list.
     * @param mainStorage The main storage instance.
     * @param command The command passed into the application
     * @return The output string to be shown to the user.
     * @throws DukeException When there is an error marking the task as done.
     */
    public static String done(TaskList tasks, MainStorage mainStorage, String command) throws DukeException {
        String output = tasks.markAsDone(command);
        mainStorage.save(tasks.toSaveFormat());
        return output;
    }

    /**
     * Handles the todo command.
     *
     * @param tasks The current task list.
     * @param mainStorage The main storage instance.
     * @param command The command passed into the application.
     * @return The output string to be shown to the user.
     * @throws DukeException When there is an error creating the todo.
     */
    public static String todo(TaskList tasks, MainStorage mainStorage, String command) throws DukeException {
        String output = tasks.addTodo(command);
        mainStorage.save(tasks.toSaveFormat());
        return output;
    }

    /**
     * Handles the deadline command.
     *
     * @param tasks The current task list.
     * @param mainStorage The main storage instance.
     * @param command The command passed into the application.
     * @return The output string to be shown to the user.
     * @throws DukeException When there is an error creating the deadline.
     */
    public static String deadline(TaskList tasks, MainStorage mainStorage, String command) throws DukeException {
        String output = tasks.addDeadline(command);
        mainStorage.save(tasks.toSaveFormat());
        return output;
    }

    /**
     * Handles the event command.
     *
     * @param tasks The current task list.
     * @param mainStorage The main storage instance.
     * @param command The command passed into the application.
     * @return The output string to be shown to the user.
     * @throws DukeException When there is an error creating the event.
     */
    public static String event(TaskList tasks, MainStorage mainStorage, String command) throws DukeException {
        String output = tasks.addEvent(command);
        mainStorage.save(tasks.toSaveFormat());
        return output;
    }

    /**
     * Handles the delete command.
     *
     * @param tasks The current task list.
     * @param mainStorage The main storage instance.
     * @param command The command passed into the application.
     * @return The output string to be shown to the user.
     * @throws DukeException When there is an error deleting the task.
     */
    public static String delete(TaskList tasks, MainStorage mainStorage, String command) throws DukeException {
        String output = tasks.delete(command);
        mainStorage.save(tasks.toSaveFormat());
        return output;
    }

    /**
     * Handles the find command.
     *
     * @param tasks The current task list.
     * @param command The command passed into the application.
     * @return The output string to be shown to the user.
     * @throws DukeException When there is an error finding the tasks.
     */
    public static String find(TaskList tasks, String command) throws DukeException {
        return tasks.find(command);
    }

    /**
     * Handles the archive command.
     *
     * @param tasks The current task list.
     * @param mainStorage The main storage instance.
     * @param archiveStorage The archive storage instance.
     * @return The output string to be shown to the user.
     */
    public static String archive(TaskList tasks, MainStorage mainStorage, ArchiveStorage archiveStorage) {
        String successMessage = "Successfully archived!";

        archiveStorage.save(tasks.toSaveFormat());
        tasks.clearAllTasks();
        mainStorage.save(tasks.toSaveFormat());
        return successMessage;
    }

    /**
     * Handles the load archive command.
     *
     * @param tasks The current task list.
     * @param mainStorage The main storage instance.
     * @param archiveStorage The archive storage instance.
     * @return The output string to be shown to the user.
     */
    public static String loadArchive(TaskList tasks, MainStorage mainStorage, ArchiveStorage archiveStorage) {
        String successMessage = "Successfully loaded archive!";
        String errorMessage = "Error while loading archive file! Does it exist?";

        List<String> fileContents;
        try {
            fileContents = archiveStorage.load();
        } catch (IOException e) {
            return errorMessage;
        }
        tasks.appendListOfStrings(fileContents);
        mainStorage.save(tasks.toSaveFormat());
        return successMessage;
    }
}
