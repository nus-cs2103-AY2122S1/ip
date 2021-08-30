package duke.commands;

import java.io.IOException;
import java.time.LocalDateTime;

import duke.TaskList;
import duke.tasks.Event;

/**
 * Command that adds event to task list.
 */
public class AddEventCommand extends Command {
    private LocalDateTime at;

    /**
     * Constructor for AddEventCommand.
     *
     * @param desc description of event.
     * @param at   when the event is.
     */
    public AddEventCommand(String desc, LocalDateTime at) {
        super(desc);
        this.at = at;
    }

    /**
     * returns when the event is.
     *
     * @return when the event is.
     */
    public String getAt() {
        return this.at.toString();
    }

    /**
     * Returns if the command is the exit command.
     *
     * @return false since this command is not the exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command. Adds deadline to task list. Updates the save file.
     *
     * @param tasks the task list.
     */
    @Override
    public String execute(TaskList tasks) throws IOException {
        Event event = new Event(super.getDesc(), at, false);
        tasks.add(event);

        StringBuilder replyBuilder = new StringBuilder();

        replyBuilder.append("Got it. I've added this task:\n");
        replyBuilder.append(event + "\n");

        if (tasks.size() == 1) {
            replyBuilder.append("Now you have 1 task in the list. \n");
        } else {
            replyBuilder.append("Now you have " + tasks.size() + " tasks in the list. \n");
        }

        return replyBuilder.toString();
    }

}
