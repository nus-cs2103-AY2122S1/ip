package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AddDeadlineCommand extends Command {
    private String command;

    public AddDeadlineCommand(String command) {
        this.command = command;
    }

    public String[] execute(Storage storage, TaskList tasks) {
        String[] words = command.split(" ");
        if (words.length <= 3) {
            throw new DukeException("invalidDeadline");
        } else if (!command.contains("/by")) {
            throw new DukeException("invalidDeadline");
        } else {
            try {
                LocalDate.parse(words[3]);
                int position = command.indexOf("/by");
                String name = command.substring(9, position);
                String date = command.substring(position + 4);
                Task task = new Deadline(name, date);
                tasks.add(task);
                storage.appendToFile("data/duke.txt", "D - 0 - " + name + "- " + date);
                return Ui.printTaskAdded(task, tasks.size());
            } catch (IOException e) {
                return new String[]{e.toString()};
            } catch (DateTimeParseException e) {
                return new String[]{("Enter valid date format!")};
            }
        }
    }
}
