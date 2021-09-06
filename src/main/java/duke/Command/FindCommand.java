package duke.Command;

import duke.Storage;
import duke.Ui;
import duke.TaskList;
import duke.task.Task;

public class FindCommand implements ICommand{
    private String keyWord;

    public FindCommand(String keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
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
        if (o == this) return true;

        if (o != null && o.getClass() == this.getClass()) {
            return ((FindCommand) o).keyWord.equals(this.keyWord);
        }
        return false;
    }
}
