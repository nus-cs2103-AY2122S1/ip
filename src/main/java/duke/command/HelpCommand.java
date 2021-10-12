package duke.command;

import duke.DukeList;

public class HelpCommand extends DukeCommand {
    private static final String HELP_MESSAGE = "Here are the available commands:\n"
            + "- Use \"help\" to display the list of commands.\n"
            + "- Use \"list\" to display the list of tasks.\n"
            + "- Use \"todo {description}\" to add a todo with a description.\n"
            + "- Use \"event {description} /at {d/M/yyyy HHmm}\" to add an event with description and dateTime.\n"
            + "- Use \"dateline {description} /by {d/M/yyyy HHmm}\" to add a deadline with description and dateTime.\n"
            + "- Use \"done {n}\" to mark task number n done.\n"
            + "- Use \"delete {n}\" to delete task number n.\n"
            + "- Use \"find {keyWord}\" to find tasks with matching keywords.\n"
            + "- Use \"bye\" to exit the program.\n";

    public HelpCommand() {
    }

    @Override
    public String run(DukeList list) {
        return HELP_MESSAGE;
    }
}
