package duke.command;

import duke.TaskList;
import duke.Ui;

public class HelpCommand implements Command {
    private String helpMsg = "Here are the supported commands:\n\n"
            + "* help\n"
            + "* todo OR t [todo name]\n"
            + "* event OR e [event name] /at [yyyy-mm-dd HH:MM]\n"
            + "* deadline OR dl [deadline name] /by [yyyy-mm-dd]\n"
            + "* done OR d [task number]\n"
            + "* delete OR del [task number]\n"
            + "* find OR f [keyword]\n"
            + "* archive OR a [task number]\n"
            + "* unarchive OR ua [archived task number]\n"
            + "* list OR ls\n"
            + "* list -archives OR ls -a\n"
            + "* bye\n";

    @Override
    public String execute(TaskList tasks, Ui ui) {
        return helpMsg;
    }

    /**
     * Indicates if the command is an exit command.
     *
     * @return If the command is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
