package me.yukun99.ip.commands;

import static me.yukun99.ip.tasks.Task.Type;

import me.yukun99.ip.core.DateTimePair;
import me.yukun99.ip.core.Storage;
import me.yukun99.ip.core.TaskList;
import me.yukun99.ip.exceptions.HelpBotDateTimeFormatException;
import me.yukun99.ip.exceptions.HelpBotIllegalArgumentException;
import me.yukun99.ip.exceptions.HelpBotInvalidTaskTypeException;
import me.yukun99.ip.exceptions.HelpBotIoException;
import me.yukun99.ip.tasks.Deadline;
import me.yukun99.ip.tasks.Event;
import me.yukun99.ip.tasks.Task;
import me.yukun99.ip.tasks.ToDo;
import me.yukun99.ip.ui.Message;

public class AddCommand extends Command {
    private final Type type;
    private final Storage storage;

    /**
     * Constructor for an AddCommand instance.
     *
     * @param args Arguments of the command.
     * @param taskList TaskList to add task to.
     * @param type Type of task to be added.
     * @param storage Storage instance to save task info to.
     */
    public AddCommand(String[] args, TaskList taskList, Type type, Storage storage) {
        super(args, taskList);
        this.type = type;
        this.storage = storage;
    }

    @Override
    public String getResponse()
            throws HelpBotDateTimeFormatException, HelpBotIllegalArgumentException, HelpBotIoException,
            HelpBotInvalidTaskTypeException {
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
        DateTimePair dateTimePair = null;
        try {
            dateTimePair = task.getDate();
        } catch (HelpBotInvalidTaskTypeException ignored) {
            // Ignored since addTask method handles null cases for dateTimePair i.e. when we're using ToDo task.
        } finally {
            taskList.addTask(task, dateTimePair);
        }
        storage.saveTask(task);
        return Message.getAddMessage(task, taskList);
    }
}
