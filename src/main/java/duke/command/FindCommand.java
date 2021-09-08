package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.task.Task;
import duke.ui.Ui;

public class FindCommand implements ICommand {
    private final String keyWord;

    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList == null || ui == null || storage == null) {
            throw new IllegalArgumentException("One of the parameters is null.");
        }
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
