package botto.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import botto.BottoException;
import botto.task.Event;
import botto.task.Task;
import botto.util.Dialog;
import botto.util.Storage;
import botto.util.TaskList;

/**
 * Command for adding an event.
 */
public class AddEventCommand implements Command {

    private String command;

    /**
     * Constructor for an AddEventCommand.
     *
     * @param command user command.
     */
    public AddEventCommand(String command) {
        this.command = command;
    }

    /**
     * Add task to the tasklist, update the storage and print relevant messages.
     *
     * @param taskList the task list involved.
     * @param dialog the ui of the Botto bot.
     * @param storage storage of the Botto bot.
     * @throws BottoException when the there is no description inserted or when the command format is wrong.
     */
    @Override
    public void execute(TaskList taskList, Dialog dialog, Storage storage) throws BottoException {
        String detail;

        try {
            detail = command.split(" ", 2)[1];
        } catch (Exception e) {
            throw new BottoException("☹ OOPS!!! The detail of an event cannot be empty.");
        }

        String[] information = detail.split(" /.. ", 2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy h:mm a");

        try {
            Task task = new Event(information[0], LocalDateTime.parse(information[1], formatter));
            taskList.addTask(task);

            dialog.respondAdd(task, taskList.getSize());
            storage.save(taskList.getTasks());
        } catch (Exception e) {
            String message = "☹ OOPS!!! The command is in wrong format.\n"
                    + "    Please enter in this format: event [title] /at [d/M/yyyy H:mm a]";
            throw new BottoException(message);
        }
    }
}
