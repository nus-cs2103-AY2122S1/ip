package botto.command;

import botto.*;
import botto.task.Event;
import botto.task.Task;
import botto.util.Storage;
import botto.util.TaskList;
import botto.util.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddEventCommand implements Command {

    private String command;

    public AddEventCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String detail;

        try {
            detail = command.split(" ", 2)[1];
        } catch (Exception e) {
            throw new DukeException("☹ OOPS!!! The detail of an event cannot be empty.");
        }

        String[] information = detail.split(" /.. ", 2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy h:mm a");

        try {
            Task task =  new Event(information[0], LocalDateTime.parse(information[1], formatter));
            taskList.addTask(task);

            ui.respondAdd(task, taskList.getSize());
            storage.save(taskList.getTasks());
        } catch (Exception e) {
            String message = "☹ OOPS!!! The command is in wrong format.\n"
                    + "    Please enter in this format: event [title] /[at] [d/M/yyyy H:mm a]";
            throw new DukeException(message);
        }
    }
}
