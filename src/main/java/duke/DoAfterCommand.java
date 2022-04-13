package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A class that handles finding tasks.
 */
public class DoAfterCommand implements Command {
    private String userInput;

    /**
     * A constructor for FindCommand object.
     */
    public DoAfterCommand(String userInput) {
        super();
        this.userInput = userInput;
    }


    @Override
    public String getResponse(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String format = "Correct format do... after... :\n"
                + "To set a task to begin after another task:\n"
                + "  do x after task y (x and y are task numbers)\n"
                + "To set a task to begin after a date and time:\n"
                + "  do x after yyyy-MM-dd HH:mm";

        try {
            String[] words = userInput.split(" ");
            int taskNum = Integer.parseInt(words[1]);
            Task taskToDoAfter = tasks.getTask(taskNum - 1);

            if (words[3].equals("task")) {
                int taskBeforeNum = Integer.parseInt(words[4]);
                Task taskToDoBefore = tasks.getTask(taskBeforeNum - 1);
                taskToDoAfter.setDoAfterTask(taskToDoBefore);
                storage.saveTasks(tasks);
                return ui.getSetAfterTaskMessage(taskToDoAfter, taskToDoBefore);
            } else if (words.length > 4) {
                LocalDateTime refDateTime = LocalDateTime.parse(
                        words[3] + " " + words[4],
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                taskToDoAfter.setDoAfterDateTime(refDateTime);
                storage.saveTasks(tasks);
                return ui.getSetAfterDateTimeMessage(taskToDoAfter, refDateTime);
            } else {
                throw new DukeException("Please enter a date and time behind 'after'\n\n" + format);
            }
        } catch (NumberFormatException nfe) {
            throw new DukeException("Please only enter an integer after command 'do' and 'after task'!\n\n" + format);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("One of the task entered does not exist!");
        } catch (DateTimeParseException e) {
            throw new DukeException("The time must be in this format: yyyy-MM-dd HH:mm\n\n" + format);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
