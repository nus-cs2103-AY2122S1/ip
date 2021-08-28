package duke.commands;

import java.io.IOException;
import java.time.LocalDate;

import duke.Storage;
import duke.TaskList;
import duke.tasks.Event;

/**
 * Command that adds event to task list.
 */
public class AddEventCommand extends Command {
    private final String at;
    private LocalDate date;

    /**
     * Constructor for AddEventCommand.
     *
     * @param desc description of event.
     * @param at   when the event is.
     */
    public AddEventCommand(String desc, String at) {
        super(desc);
        this.at = at;
        this.date = null;
    }

    /**
     * Constructor for AddEventCommand.
     *
     * @param desc description of event.
     * @param at   when the event is.
     * @param date date of event.
     */
    public AddEventCommand(String desc, String at, LocalDate date) {
        super(desc);
        this.at = at;
        this.date = date;
    }

    /**
     * returns when the event is.
     *
     * @return when the event is.
     */
    public String getAt() {
        return this.at;
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
     * @param tasks   the task list.
     * @param storage the storage of the programme.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        try {
            Event event;
            if (date == null) {
                event = new Event(super.getDesc(), at, false);
            } else {
                event = new Event(super.getDesc(), at, false, date);
            }
            tasks.add(event);
            storage.add(event);

            System.out.println("Got it. I've added this task:");
            System.out.println(event);

            if (tasks.size() == 1) {
                System.out.println("Now you have 1 task in the list.");
            } else {
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
