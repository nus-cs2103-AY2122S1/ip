package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Parser;
import duke.Storable;
import duke.Ui;
import duke.Ui.Commands;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.time.LocalDate;

public class DateCommand extends Command {
    private String userInput;

    public DateCommand(String userInput) {
        this.userInput = userInput;
    }

    private void printTaskAtDate(TaskList tasks, Ui ui) throws DukeException {
        // Initialize counters to track number of tasks, events and deadlines.
        int counter = 0;
        int events = 0;
        int deadlines = 0;

        // Check if anything is provided 1 space after date command.
        if (this.userInput.length() <= (Commands.DATE.getLength() + 1)) {
            // If nothing is provided, date to search for is not provided.
            throw new DukeException(Ui.exceptionMissingDate());
        }

        // Check for space after date command.
        // This prevents wrong date being read by reminding user to add space.
        if (this.userInput.charAt(Commands.DATE.getLength()) != ' ') {
            throw new DukeException(Ui.exceptionMissingSpaceAfterCommand(Commands.DATE.getCommand()));
        }

        // Parses user input into LocalDate. User input for date will follow "date" command.
        String dateString = this.userInput.substring(Commands.DATE.getLength() + 1);

        LocalDate localDate = Parser.toLocalDate(dateString);
        String formattedDateString = Parser.parseLocalDate(localDate);

        // Print to notify users of the date they are searching for.
        ui.showDateListSuccess(formattedDateString);

        // Print Deadlines and Events with LocalDate that matches date input from user.
        for (Task task : tasks.getTasks()) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (localDate.equals(deadline.getTime())) {
                    counter++;
                    deadlines++;
                    System.out.println(counter + "." + deadline);
                }
            }

            if (task instanceof Event) {
                Event event = (Event) task;
                if (localDate.equals(event.getTime())) {
                    counter++;
                    events++;
                    System.out.println(counter + "." + event);
                }
            }
        }

        // Print a summary of matching tasks to the user.
        ui.showDateListSummary(formattedDateString, counter, deadlines, events);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storable storage) {
        try {
            // Print tasks that fall on user specified date.
            this.printTaskAtDate(tasks, ui);

            // Saves edited duke.TaskList to save file.
            storage.saveTasksToData(tasks);
        } catch (DukeException dukeException) {
            System.out.println(dukeException);
        }
    }
}
