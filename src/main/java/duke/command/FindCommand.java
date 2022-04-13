package duke.command;

import duke.data.Storage;
import duke.data.InformationList;
import duke.data.Ui;

/**
 * Command that displays a filtered InformationList when executed.
 */
public class FindCommand extends Command {
    /** Keyword that the InformationList will be filtered by. */
    private String keyword;

    /**
     * Constructs FindCommand class.
     *
     * @param keyword String that is used to filter through InformationList.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Displays a user's saved information containing the keyword(s).
     *
     * @param tasks The list of information that a user has.
     * @param ui The ui that sends a filtered InformationList as a string to the user.
     * @param storage Not used for this command.
     * @return The message produced by ui.
     */
    @Override
    public String execute(InformationList tasks, Ui ui, Storage storage) {
        return ui.showTasks(tasks.searchTaskByKeyword(keyword)) + ui.showContacts(tasks.searchContactByKeyword(keyword));
    }
}
