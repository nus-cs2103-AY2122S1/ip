package duke.command;

import duke.TaskList;
import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.*;

import java.time.LocalDate;

public class AddCommand extends Command {

    private String description;
    private String time;
    private String category;

    public AddCommand(String input, String category) {
        super();
        this.category = category;
        String[] info = input.split("/");
        this.description = info[0].strip();
        if (!category.equals("todo")) {
            this.time = info[1].strip();
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showLine();
        if ("todo".equals(category)) {
            tasks.add(new Todo(description));
        } else if ("deadline".equals(category)) {
            LocalDate ld = LocalDate.parse(time);
            tasks.add(new Deadline(description, ld));
        } else if ("event".equals(category)) {
            LocalDate ld = LocalDate.parse(time);
            tasks.add(new Event(description, ld));
        } else {
            throw new DukeException("Wrong format of info");
        }
        ui.showLine();
    }



}