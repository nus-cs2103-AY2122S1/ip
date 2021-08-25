import java.io.IOException;

public class AddCommand extends Command { //AddCommand class to handle all the adding of events to the list.
    private final Task inputTask;

    public AddCommand(String eventType, String details) throws DukeException {
        super(true);
        String[] split;
        switch (eventType) {
        case "todo":
            this.inputTask = new Todo(details);
            break;

        case "deadline":
            if (details == null) {
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            split = details.split(" /by ");
            if (split.length <= 1) {
                throw new DukeException("☹ OOPS!!! Your deadline input format is not valid!");
            }
            this.inputTask = new Deadline(split[0].trim(), split[1]);
            break;

        case "event":
            if (details == null) {
                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            }
            split = details.split(" /at ");
            if (split.length <= 1) {
                throw new DukeException("☹ OOPS!!! Your event date input format is not valid!");
            }
            this.inputTask = new Event(split[0].trim(), split[1]);
            break;

        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        taskList.add(this.inputTask);
        ui.showAddedMessage(taskList);
        Storage.updateText(taskList);
    }
}
