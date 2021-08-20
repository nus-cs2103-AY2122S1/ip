import java.time.LocalDate;

public class DateCommand extends Command {
    private String userInput;

    DateCommand(String userInput) {
        this.userInput = userInput;
    }

    private void printTaskAtDate(TaskList tasks, Ui ui) throws DukeException {
        // Initialize counters to track number of tasks, events and deadlines.
        int counter = 0;
        int events = 0;
        int deadlines = 0;

        // Parses user input into LocalDate. User input for date will follow "date" command.
        String dateString = this.userInput.substring(Ui.Commands.DATE.getLength() + 1);
        LocalDate localDate = Parser.toLocalDate(dateString);
        String formattedDateString = Parser.parseLocalDate(localDate);

        // Print to notify users of the date they are searching for.
        ui.showDateListSuccess(formattedDateString);

        // Print Deadlines and Events with LocalDate that matches date input from user.
        for (Task task : tasks.getTasks()) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (localDate.equals(deadline.by)) {
                    counter++;
                    deadlines++;
                    System.out.println(counter + "." + deadline);
                }
            }

            if (task instanceof Event) {
                Event event = (Event) task;
                if (localDate.equals(event.at)) {
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
    void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            // Print tasks that fall on user specified date.
            this.printTaskAtDate(tasks, ui);

            // Saves edited TaskList to save file.
            storage.saveTasksToData(tasks);
        } catch (DukeException dukeException) {
            System.out.println(dukeException);
        }
    }
}
