package duke.commands;

import duke.DateTimeHandler;
import duke.DukeException;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

public class TagCommand extends Command {

    public TagCommand(String arguments) {
        super(arguments);
    }

    @Override
    public Command of(String arguments) {
        return new TagCommand(arguments);
    }

    @Override
    public String execute(TaskList tl, Storage s, Ui ui, DateTimeHandler dth) throws DukeException {
        String args = super.getArguments();
        if (args.equals("")) {
            throw new DukeException("Input which task you want to add tags to, followed by the tags");
        }
        String[] parts = args.split(" ");
        try {
            int index = Integer.parseInt(parts[0]);
            if (index > tl.size()) {
                throw new DukeException("There are only " + tl.size() + " tasks");
            } else if (index == 0) {
                throw new DukeException("There is no task 0");
            } else if (parts.length < 2) {
                throw new DukeException("Input tags after the number");
            }
            Task t = tl.getTask(index - 1);

            for (int i = 1; i < parts.length; i++) {
                t.addTag(parts[i]);
            }
            return "Tags successfully added";

        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a number after tag");
        }
    }

    @Override
    public String getCommandPrefix() {
        return "tag";
    }
}
