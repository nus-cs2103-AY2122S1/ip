package command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.stream.Collectors;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public class DateCommand extends Command {
    /** The date to be queried. **/
    private final LocalDate date;

    /**
     * A public constructor to initialized the DateCommand.
     *
     * @param date The date to be queried.
     */
    public DateCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * A method to execute this DateCommand. When the method is executed,
     * it will scan through the given TaskList to find all the tasks that
     * happen on the given date, and send them to the given Ui.
     *
     * @param taskList The given Duke TaskList.
     * @param ui The given Duke Ui.
     * @param storage The given Duke Storage.
     * @throws DukeException Exception thrown when execute the DateCommand.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String result = taskList.getTaskList()
                .stream()
                .parallel()
                .filter(x -> x.isOnDate(date))
                .map(x -> x.toString())
                .collect(Collectors.joining(
                        "\n        ",
                        "Your schedule on "
                                + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.US))
                                + " is:\n        ",
                        ""
                        ));
        return ui.generateDukeResponse(result);
    }
}
