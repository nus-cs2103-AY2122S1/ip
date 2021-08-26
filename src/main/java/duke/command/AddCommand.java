package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;

public class AddCommand extends Command {

    private String description;
    private String date;
    private String time;
    private String type;

    // constructor for todo
    public AddCommand(String description, String type) {
        super(false);
        this.description = description;
        this.type = type;
    }

    // constructor for deadline
    public AddCommand(String description, String date, String type) {
        super(false);
        this.description = description;
        this.date = date;
        this.type = type;
    }

    // constructor for event
    public AddCommand(String description, String date, String time, String type) {
        super(false);
        this.description = description;
        this.date = date;
        this.time = time;
        this.type = type;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        switch(this.type) {
            case "todo":
                Todo todo = tasks.createTodo(this.description);
                ui.displaySuccessMessage(todo, tasks.length());
                break;
            case "deadline":
                Deadline dl = tasks.createDeadline(this.description, this.date);
                ui.displaySuccessMessage(dl, tasks.length());
                break;
            case "event":
                Event event = tasks.createEvent(this.description, this.date, this.time);
                ui.displaySuccessMessage(event, tasks.length());
                break;
        }

    }
}
