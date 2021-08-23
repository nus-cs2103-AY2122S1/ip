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
        tasks.add(t);
        storage.append(t.toStringForFile() + System.lineSeparator());
        ui.showResponse("Got it. I've added this task: \n\t\t "
                + t
                + "\n\t Now you have " + tasks.getSize() + " tasks in the list.");
    }
}
