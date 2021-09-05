package duke.commands;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;


/**
 * Creates an AddDeadlineCommand to add deadlines to the task list.
 */
public class AddDeadlineCommand extends Command {
    private String command;

    /**
     * Constructs new AddDeadlineCommand object.
     *
     * @param command Command the object carries out.
     */
    public AddDeadlineCommand(String command) {
        assert command.startsWith("deadline");
        this.command = command;
    }

    /**
     * Executes the Command accordingly.
     *
     * @param storage Storage to store changes in text file.
     * @param tasks Tasks compiled in a TaskList.
     * @return A String array containing output.
     */
    public String[] execute(Storage storage, TaskList tasks) {
        String[] words = command.split(" ");
        if (words.length <= 3) {
            throw new DukeException("invalidDeadline");
        } else if (!command.contains("/by")) {
            throw new DukeException("invalidDeadline");
        }
        try {
            LocalDate.parse(words[3]);
            int position = command.indexOf("/by");
            String name = command.substring(9, position);
            String date = command.substring(position + 4);
            Task task = new Deadline(name, date);
            tasks.add(task);
            storage.appendToFile("data/duke.txt", "D - 0 - " + name + "- " + date);
            storage.storeHistory(command);
            return Ui.printTaskAdded(task, tasks.size());
        } catch (IOException e) {
            return new String[]{e.toString()};
        } catch (DateTimeParseException e) {
            return new String[]{("Enter valid date format!")};
        } catch (DukeException e) {
            return new String[]{e.toString()};
        }
    }
}
