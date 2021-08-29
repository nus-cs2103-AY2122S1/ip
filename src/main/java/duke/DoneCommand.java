package duke;

public class DoneCommand extends Command {

    public static final String COMMAND_WORD = "done";
    public static final String USAGE_TEXT = "Usage: done [task number]";

    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            // Mark task as done
            Task t = taskList.getTask(index);
            t.setDone();
            ui.reply("Noice! Pepper Jack marked this task as done:\n\t" + t);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task number does not exist!\n\t" + DoneCommand.USAGE_TEXT);
        }
    }
}
