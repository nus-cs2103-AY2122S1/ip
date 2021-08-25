package biscuit.commands;

import biscuit.exceptions.BiscuitException;
import biscuit.storage.Storage;
import biscuit.task.Deadline;
import biscuit.task.Event;
import biscuit.task.Task;
import biscuit.task.TaskList;
import biscuit.task.ToDo;
import biscuit.ui.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Add command to add task.
 */
public class AddCommand extends Command {

    /**
     * Constructs AddCommand class.
     *
     * @param userInputs User input array with this structure: [command, details].
     */
    public AddCommand(String[] userInputs) {
        super(CommandType.ADD, userInputs);
    }

    /**
     * Adds specified task into list.
     *
     * @param taskList Task list.
     * @param ui       Ui to display.
     * @param storage  Storage to save to.
     * @throws BiscuitException Invalid input by user.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws BiscuitException {
        if (userInputs.length == 2) {
            Task task;
            switch (userInputs[0]) {
            case "deadline":
                task = getDeadline();
                break;
            case "event":
                task = getEvent();
                break;
            default:
                task = getToDo();
                break;
            }
            taskList.addTask(task);
            storage.save();
            ui.showMessage("Got it. I've added this task:\n\t" + task
                    + "\nNow you have " + taskList.size() + " tasks in the list.");
        } else {
            throw new BiscuitException("໒(◉ᴥ◉)७ OOPS!!! The description of " + userInputs[0] + " cannot be empty.");
        }
    }

    /**
     * Creates deadline command.
     *
     * @return Deadline command.
     * @throws BiscuitException Invalid user input.
     */
    private Deadline getDeadline() throws BiscuitException {
        String[] deadlineData = userInputs[1].split("/by ", 2);
        if (deadlineData.length == 2) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                return new Deadline(deadlineData[0].trim(), LocalDate.parse(deadlineData[1], formatter));
            } catch (DateTimeParseException e) {
                throw new BiscuitException("Woof! Please use the date format of dd-MM-yyy (eg. 02-22-2021).");
            }
        } else {
            throw new BiscuitException("໒(◉ᴥ◉)७ OOPS!!! The date/time for deadline cannot be empty.\n" +
                    "Please use the format: deadline <name> /by dd-MM-yyy");
        }
    }

    /**
     * Creates event command.
     *
     * @return Event command.
     * @throws BiscuitException Invalid user input.
     */
    private Event getEvent() throws BiscuitException {
        String[] eventData = userInputs[1].split("/at ", 2);
        if (eventData.length == 2) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                return new Event(eventData[0].trim(), LocalDateTime.parse(eventData[1], formatter));
            } catch (DateTimeParseException e) {
                throw new BiscuitException("Woof! Please use the date format of dd-MM-yyy HH:mm (eg. 02-22-2021 22:02).");
            }
        } else {
            throw new BiscuitException("໒(◉ᴥ◉)७ OOPS!!! The date/time for event cannot be empty.\n" +
                    "Please use the format: event <name> /at dd-MM-yyy HH:mm");
        }
    }

    /**
     * Creates todo command.
     *
     * @return ToDo command.
     */
    private ToDo getToDo() {
        return new ToDo(userInputs[1]);
    }
}
