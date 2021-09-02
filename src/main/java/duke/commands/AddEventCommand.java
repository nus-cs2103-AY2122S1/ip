package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AddEventCommand extends Command {
    private String command;

    public AddEventCommand(String command) {
        this.command = command;
    }

    public String[] execute(Storage storage, TaskList tasks) {
        String[] words = command.split(" ");
        if (words.length <= 3) {
            throw new DukeException("invalidEvent");
        } else if (!command.contains("/at")) {
            throw new DukeException("invalidEvent");
        } else {
            try {
                LocalDate.parse(words[3]);
                int position = command.indexOf("/at");
                String name = command.substring(6, position);
                String date = command.substring(position + 4);
                Task task = new Event(name, date);
                tasks.add(task);
                storage.appendToFile("data/duke.txt", "E - 0 - " + name + "- " + date);
                return Ui.printTaskAdded(task, tasks.size());
            } catch (IOException e) {
                return new String[]{e.toString()};
            } catch (DateTimeParseException e) {
                return new String[]{("Enter valid date format!")};
            }
        }
    }

}
