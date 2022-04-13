package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand implements Command {

    private String keyWords;

    public FindCommand(String keyWords) {
        this.keyWords = keyWords;
    }

    /**
     * Executes a command to find similar tasks that have the same name as the keywords given.
     *
     * @param taskList Tasklist that contains an Arraylist of agendas on the list.
     * @param ui Ui that outputs something based on the command given.
     * @param storage Storage that changes the list stored in data/duke.txt based on the command.
     * @return A messsage that includes all tasks that match the given keywords.
     * @throws DukeException An exception thrown if nothing is found.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        return taskList.findSimilarTasks(keyWords);
    }

    /**
     * A method that causes the program not to exit.
     *
     * @return a boolean that tells the program it is not exiting.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
