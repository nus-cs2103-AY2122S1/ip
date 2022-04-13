package pika.command;

import java.io.IOException;

import pika.exception.PikaException;
import pika.task.Deadline;
import pika.task.Event;
import pika.task.Task;
import pika.task.TaskList;
import pika.task.Todo;
import pika.ui.Storage;
import pika.ui.Ui;

/**
 * AddCommand class to handle all the adding of events to the list.
 */
public class AddCommand extends Command {
    private final Task inputTask;

    /**
     * Constructor for AddCommand.
     *
     * @param taskType Task type.
     * @param details The name/date/time of the task.
     * @throws PikaException Throws this if there are anything wrong with the inputs.
     */
    public AddCommand(String taskType, String details) throws PikaException {
        super(true);
        String[] split;
        switch (taskType) {
        case "todo":
            if (details == null) {
                throw new PikaException("Pika pi!! The description of a todo task cannot be empty.");
            }
            this.inputTask = new Todo(details);
            break;

        case "deadline":
            if (details == null) {
                throw new PikaException("Pika pi!! The description of a deadline task cannot be empty.");
            }
            split = details.split(" /by ");
            if (split.length <= 1) {
                throw new PikaException("Pika pi!! Your deadline input format is not valid!");
            }
            this.inputTask = new Deadline(split[0].trim(), split[1]);
            break;

        case "event":
            if (details == null) {
                throw new PikaException("Pika pi!!  The description of an event task cannot be empty.");
            }
            split = details.split(" /at ");
            if (split.length <= 1) {
                throw new PikaException("Pika pi!! Your event date input format is not valid!");
            }
            this.inputTask = new Event(split[0].trim(), split[1]);
            break;

        default:
            throw new PikaException("Pika pi!!  I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Executes the AddCommand to update the list, update the txt, and print the returns to the user.
     *
     * @param taskList The current list of tasks.
     * @param storage  The current storage class to handle the txt file.
     * @return The add task string for the Pikabot to say.
     * @throws IOException if the filepath has any issues.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws IOException {
        taskList.add(this.inputTask);
        Storage.updateText(taskList);
        return Ui.addedMessage(taskList);
    }
}
