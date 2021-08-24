package duke;

public class AddCommand extends Command {
    private String description;
    private String taskType;

    public AddCommand(String taskType, String secondWord) {
        super();
        this.taskType = taskType;
        this.description = secondWord;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = null;

        // Create Todo, Deadline or Event task
        switch (taskType) {
        case "todo":
            if (description.isBlank()) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            } else {
                t = new Todo(description);
            }
            break;
        case "deadline":
            String d = description.split(" /by")[0];
            String by = description.split("/by ")[1];
            t = new Deadline(d, by);
            break;
        case "event":
            String s = description.split(" /at")[0];
            String at = description.split("/at ")[1];
            t = new Event(s, at);
            break;
        default:
            break;
        }

        // Add task to list in Duke, and persist to text file
        tasks.add(t);
        storage.append(t.toStringForFile() + System.lineSeparator());

        // Display response to user
        ui.showResponse("Got it. I've added this task: \n\t\t "
                + t
                + "\n\t Now you have " + tasks.getSize() + " tasks in the list.");
    }
}
