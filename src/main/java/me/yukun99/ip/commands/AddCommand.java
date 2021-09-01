package me.yukun99.ip.commands;

import static me.yukun99.ip.tasks.Task.Type;

import me.yukun99.ip.core.DateTimePair;
import me.yukun99.ip.core.Storage;
import me.yukun99.ip.core.TaskList;
import me.yukun99.ip.core.Ui;
import me.yukun99.ip.exceptions.HelpBotDateTimeFormatException;
import me.yukun99.ip.exceptions.HelpBotIllegalArgumentException;
import me.yukun99.ip.exceptions.HelpBotInvalidTaskTypeException;
import me.yukun99.ip.tasks.Deadline;
import me.yukun99.ip.tasks.Event;
import me.yukun99.ip.tasks.Task;
import me.yukun99.ip.tasks.ToDo;

public class AddCommand extends Command {
    private final Type type;
    private final Storage storage;

    /**
     * Constructor for an AddCommand instance.
     *
     * @param args Arguments of the command.
     * @param taskList TaskList to add task to.
     * @param ui Ui to send feedback through.
     * @param type Type of task to be added.
     * @param storage Storage instance to save task info to.
     */
    public AddCommand(String[] args, TaskList taskList, Ui ui, Type type, Storage storage) {
        super(args, taskList, ui);
        this.type = type;
        this.storage = storage;
    }

    @Override
    public void run() throws HelpBotIllegalArgumentException {
        try {
            Task task;
            switch (type) {
            case TODO:
                task = new ToDo(args[0]);
                break;
            case DEADLINE:
                task = new Deadline(args[0], args[1]);
                break;
            case EVENT:
                task = new Event(args[0], args[1]);
                break;
            default:
                throw new HelpBotIllegalArgumentException(args[0]);
            }
            try {
                DateTimePair dateTimePair = task.getDate();
                taskList.addTask(task, dateTimePair);
            } catch (HelpBotInvalidTaskTypeException e) {
                taskList.addTask(task, null);
            }
            ui.add(task);
            storage.saveTask(task);
        } catch (HelpBotDateTimeFormatException e) {
            ui.error(e);
        }
    }
}
