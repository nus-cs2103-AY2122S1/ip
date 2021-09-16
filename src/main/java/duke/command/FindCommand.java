package duke.command;

import static java.util.Objects.requireNonNull;

import duke.Storage;
import duke.TaskList;
import duke.task.Task;
import duke.ui.Ui;

public class FindCommand extends Command {
    private final String keyWord;

    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    /**
     * Looks for tasks whose description contains the key word, then outputs search result.
     * See {@link duke.ui.Ui#printFoundTasks Ui.printFoundTasks}
     *
     * @param taskList duke's task list
     * @param ui current Ui instance
     * @param storage current storage instance
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        requireNonNull(ui);

        Ui.printFoundTasks(taskList.stream()
                .filter(t -> t.getDescription().contains(keyWord))
                .map(Task::toString)
                .toArray(String[]::new)
        );
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o != null && o.getClass() == this.getClass()) {
            return ((FindCommand) o).keyWord.equals(this.keyWord);
        }
        return false;
    }
}
